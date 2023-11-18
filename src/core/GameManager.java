package core;

import constants.Constants;
import models.AI;
import models.Game;
import ui.GameScreen;
import ui.StartScreen;

import javax.swing.JFrame;

public class GameManager {
    public int state;
    public int playerNumber;
    public JFrame screen;
    public boolean playerTurn;
    public Game game;
    public AI ai;

    public GameManager(Integer startState) {
        this.state = startState;
        this.screen = new JFrame(Constants.gameTitle);
        this.playerNumber = 0;
        this.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setState(Integer newState) {
        state = newState;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getAiNumber() { return playerNumber == 2 ? 1 : 2; }

    public void setPlayerNumber(Integer newPlayerNumber) {
        playerNumber = newPlayerNumber;
    }

    public void setPlayerTurn(Boolean newPlayerTurn) {
        playerTurn = newPlayerTurn;
    }

    public boolean getPlayerTurn() { return playerTurn; }

    public void update() {
        if (state == 0) { //state 0 indicates the start screen should be launched
            StartScreen startScreen = new StartScreen(screen, this);
            startScreen.createStartScreen();
        }
        else if (state == 1) { //state 1 indicates the game has started and the game screen should be launced
            game = new Game(playerNumber); //makes a new game with player number
            GameScreen gameScreen = new GameScreen(screen, 6, 7, this, game);
            ai = game.getAi();

            gameScreen.initBoard(); //initializes the game board
            gameLoop(gameScreen); //calls the main game loop
        }
    }

    public void gameLoop(GameScreen gameScreen) {
        if (game.endGame(game.getGameBoard())) { //check to see if the game has ended via draw or win
            gameScreen.updateBoard(); //update game board before handling the end
            handleEnd(gameScreen);
        }
        else if (playerTurn) { //this method is only called at the beginning of the game
            gameScreen.initBoard();
        }
        else {
            int col = ai.calculate(game); //get the column that the AI chose
            if (!gameScreen.piecePlaced(col, getPlayerTurn())) //this method will not be needed once AI is good
                gameLoop(gameScreen);
            else {
                if (game.endGame(game.getGameBoard()))
                    gameLoop(gameScreen);
                gameScreen.updateBoard();
            }
        }
    }

    public void handleEnd(GameScreen gameScreen) {
        if (game.fullGame(game.getGameBoard())) //check to see if the game ended in a draw (the game board is full)
            gameScreen.drawPopup();
        else { //if the game did not end in a draw, there was a winner
            gameScreen.winnerPopup(game.getWinner() == getPlayerNumber()); //check if player or AI won
        }
    }
}
