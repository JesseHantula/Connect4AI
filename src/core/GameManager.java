package core;

import constants.Constants;
import models.AI;
import models.Game;
import models.Pair;
import ui.GameScreen;
import ui.StartScreen;

import javax.swing.JFrame;

/*
Class that manages the game. Starts the game and handles the game loop.
 */
public class GameManager {
    public int state;
    public int playerNumber;
    public final JFrame screen;
    public boolean playerTurn;
    public Game game;
    public AI ai;
    public Pair lastPlacedPiece;
    public int pieceCount;

    /*
    Takes in initial game state as a param (always 0)
     */
    public GameManager(Integer startState) {
        this.state = startState;
        this.screen = new JFrame(Constants.gameTitle);
        this.playerNumber = 0;
        this.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pieceCount = 0;
        this.lastPlacedPiece = new Pair(-1, -1);
    }

    /*
    Sets the state of the game, which determines which UI screen to display
    @param state The state of the game (0 = start, 1 = game)
     */
    public void setState(Integer newState) {
        state = newState;
    }

    /*
    Gets player number (options = 1, 2)
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /*
    Gets AI number (opposite of player number, options = 1, 2)
     */
    public int getAiNumber() { return playerNumber == 2 ? 1 : 2; }

    /*
    Sets player number, used after player chooses their number on start screen
    @param newPlayerNumber The number chosen by the player
     */
    public void setPlayerNumber(Integer newPlayerNumber) {
        playerNumber = newPlayerNumber;
    }

    /*
    Sets player turn, either true or false
    @param newPlayerTurn If true, then it is player's turn
     */
    public void setPlayerTurn(Boolean newPlayerTurn) {
        playerTurn = newPlayerTurn;
    }

    /*
    Gets player turn, either true or false
     */
    public boolean getPlayerTurn() { return playerTurn; }

    /*
    Sets the last placed piece, used for minimax algorithm
    @param newLastPlacedPiece Location of last placed piece as a pair (row, col)
     */
    public void setLastPlacedPiece(Pair newLastPlacedPiece) { lastPlacedPiece = newLastPlacedPiece; }

    /*
    Gets the last placed piece as a pair (row, col)
     */
    public Pair getLastPlacedPiece() { return lastPlacedPiece; }

    /*
    Updates the piece count by adding 1 to it (necessary for minimax algorithm and to check if the game board is full)
     */
    public void updatePieceCount() { pieceCount++; }

    /*
    Gets the piece count on the current game board
     */
    public int getPieceCount() { return pieceCount; }

    /*
    Method used to update the gameboard, depending on the state of the game
     */
    public void update() {
        if (state == 0) { //state 0 indicates the start screen should be launched
            StartScreen startScreen = new StartScreen(screen, this);
            startScreen.createStartScreen();
        }
        else if (state == 1) { //state 1 indicates the game has started and the game screen should be launched
            game = new Game(playerNumber); //makes a new game with player number
            GameScreen gameScreen = new GameScreen(screen, 6, 7, this, game);
            ai = game.getAi();

            gameScreen.initBoard(); //initializes the game board
            gameLoop(gameScreen); //calls the main game loop
        }
    }

    /*
    Method that handles the main game loop, checks for a win/draw after every move
    @param gameScreen Game screen UI
     */
    public void gameLoop(GameScreen gameScreen) {
        if (pieceCount > 6 && game.endGame(game.getGameBoard(), getLastPlacedPiece(), getPieceCount())) { //check to see if the game has ended via draw or win
            gameScreen.updateBoard(); //update game board before handling the end
            handleEnd(gameScreen);
        }
        else if (playerTurn) { //this method is only called at the beginning of the game
            gameScreen.initBoard();
        }
        else {
            int col = ai.calculate(game, getLastPlacedPiece(), getPieceCount()); //get the column that the AI chose
            Pair slot = gameScreen.piecePlaced(col, false);
            setLastPlacedPiece(slot);
            if (game.endGame(game.getGameBoard(), slot, getPieceCount()))
                gameLoop(gameScreen);
            gameScreen.updateBoard();
        }
    }

    /*
    Method that handles the end of a game
    @param gameScreen Game screen UI
     */
    public void handleEnd(GameScreen gameScreen) {
        if (game.winGame(game.getGameBoard(), getLastPlacedPiece())) //check to see if the game ended with a winner
            gameScreen.winnerPopup(game.getWinner(game.getGameBoard(), getLastPlacedPiece()) == getPlayerNumber()); //check if player or AI won
        else  //if the game did not end with a winner, then it was a draw
            gameScreen.drawPopup();
    }
}
