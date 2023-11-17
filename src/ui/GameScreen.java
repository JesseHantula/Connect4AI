package ui;

import core.GameManager;
import models.Game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;

public class GameScreen extends JPanel {
    public int[][] gameBoard;
    public int numRows;
    public int numCols;
    public JFrame screen;
    public GameManager gameManager;
    public JLabel[][] slots;
    public JButton[] buttons;
    public Game game;
    public boolean started;

    public GameScreen(JFrame screen, int numRows, int numCols, GameManager gameManager, Game game) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.gameBoard = new int[numRows][numCols];
        this.screen = screen;
        this.gameManager = gameManager;
        this.game = game;
        this.started = false;
    }

    public void initBoard() { //method to initalize the game screen
        UIUtils.resetScreen(screen);

        JPanel panel = (JPanel) screen.getContentPane();
        panel.setLayout(new GridLayout(numRows + 1, numCols));

        slots = new JLabel[numRows][numCols];
        buttons = new JButton[numCols];
        started = true;

        for (int i = 0; i < numCols; i++) { //sets up buttons for placing pieces
            buttons[i] = new JButton(String.valueOf(i + 1));
            buttons[i].setActionCommand(String.valueOf(i));
            buttons[i].addActionListener(
                    e -> {
                        int col = Integer.parseInt(e.getActionCommand());
                        if (piecePlaced(col, gameManager.getPlayerTurn()))
                            gameManager.gameLoop(GameScreen.this);
                    });
            panel.add(buttons[i]);
        }

        for (int row = 0; row < numRows; row++) { //sets up the game board grid
            for (int col = 0; col < numCols; col++) {
                slots[row][col] = new JLabel();
                slots[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                slots[row][col].setBorder(new LineBorder(Color.black));
                panel.add(slots[row][col]);
            }
        }

        screen.setVisible(true);
    }

    public boolean piecePlaced(Integer column, boolean isPlayer) { //method for updating board when a piece is placed
        int row = game.findLowestEmptyRow(column);
        if (row != -1) {
            gameBoard[row][column] = isPlayer ? gameManager.getPlayerNumber() : gameManager.getAiNumber();
            game.setGameBoard(gameBoard);
            gameManager.setPlayerTurn(!isPlayer);
            return true; //returns true to indicate a piece was successfully placed
        }
        return false; //returns false to indicate that a piece was not placed due to column being full
    }

    public void updateBoard() { //method that updates game board whenever a piece is placed
        if (started) {
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    if (gameBoard[row][col] == 1) {
                        slots[row][col].setOpaque(true);
                        slots[row][col].setBackground(Color.red);
                    }
                    if (gameBoard[row][col] == 2) {
                        slots[row][col].setOpaque(true);
                        slots[row][col].setBackground(Color.blue);
                    }
                }
            }
        }
        else { //call initBoard if game screen has not been initialized yet
            initBoard();
        }
    }

    public void drawPopup() { //creates popup window for a tie game
        int choice = JOptionPane.showConfirmDialog(screen, "Play again?", "Tie game!", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) { //starts new game
            screen.dispose();
            GameManager manager = new GameManager(0);
            manager.update();
        }
        else { //exits game
            screen.dispose();
        }
    }


    public void winnerPopup(boolean playerWinner) { //creates popup window for a won game
        int choice;
        if (playerWinner) //window for if player won
            choice = JOptionPane.showConfirmDialog(screen, "Play again?", "Player wins!", JOptionPane.YES_NO_OPTION);
        else //window for if AI won
            choice = JOptionPane.showConfirmDialog(screen, "Play again?", "AI wins!", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) { //starts new game
            screen.dispose();
            GameManager manager = new GameManager(0);
            manager.update();
        }
        else { //exits game
            screen.dispose();
        }
    }
}
