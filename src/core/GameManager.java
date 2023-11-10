package core;

import models.AI;
import models.Game;
import ui.GameScreen;
import ui.StartScreen;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameManager {
    public int state;
    public int playerNumber;
    public JFrame screen;
    public boolean playerTurn;
    public Game game;
    public AI ai;

    public GameManager(Integer startState) {
        this.state = startState;
        this.screen = new JFrame("Connect 4");
        this.playerNumber = 0;
        this.screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.screen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public int getState() {
        return state;
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
        if (state == 0) {
            StartScreen startScreen = new StartScreen(screen, this);
            startScreen.createStartScreen();
        }
        else if (state == 1) {
            game = new Game(playerNumber);
            GameScreen gameScreen = new GameScreen(screen, 6, 7, 150, this, game);
            ai = game.getAi();

            gameScreen.initBoard();
            gameLoop(gameScreen);
        }
    }

    public void gameLoop(GameScreen gameScreen) {
        if (game.endGame()) {
            gameScreen.updateBoard();
            handleEnd(gameScreen);
        }
        else if (playerTurn) {
            gameScreen.initBoard();
        }
        else {
            int col = ai.calculate(game.getGameBoard());
            if (!gameScreen.piecePlaced(col, getPlayerTurn()))
                gameLoop(gameScreen);
            else {
                if (game.endGame())
                    gameLoop(gameScreen);
                gameScreen.updateBoard();
            }
        }
    }

    public void handleEnd(GameScreen gameScreen) {
        if (game.fullGame())
            gameScreen.drawPopup();
        else {
            gameScreen.winnerPopup(game.getWinner() == getPlayerNumber());
        }
    }
}
