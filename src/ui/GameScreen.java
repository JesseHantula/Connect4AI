package ui;

import core.GameManager;
import models.Game;
import models.Pair;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;

/*
Class that creates the game screen UI
 */
public class GameScreen extends JPanel {
    public final int[][] gameBoard;
    public final int numRows;
    public final int numCols;
    public final JFrame screen;
    public final GameManager gameManager;
    public JLabel[][] slots;
    public JButton[] buttons;
    public final Game game;
    public boolean started;

    /*
    Takes in screen (reuses the start screen), number of rows, number of columns, game manager, and game
     */
    public GameScreen(JFrame screen, int numRows, int numCols, GameManager gameManager, Game game) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.gameBoard = new int[numRows][numCols];
        this.screen = screen;
        this.gameManager = gameManager;
        this.game = game;
        this.started = false;
    }

    /*
    Method to initialize the game screen
     */
    public void initBoard() {
        UIUtils.resetScreen(screen); //resets screen (makes it blank)

        JPanel panel = (JPanel) screen.getContentPane();
        panel.setLayout(new GridLayout(numRows + 1, numCols)); //make grid layout

        slots = new JLabel[numRows][numCols];
        buttons = new JButton[numCols];
        started = true;

        for (int i = 0; i < numCols; i++) { //sets up buttons for placing pieces
            buttons[i] = new JButton(String.valueOf(i + 1));
            buttons[i].setActionCommand(String.valueOf(i));
            buttons[i].addActionListener(
                    e -> {
                        int col = Integer.parseInt(e.getActionCommand());
                        Pair piecePlaced = piecePlaced(col, gameManager.getPlayerTurn());
                        if (piecePlaced != null) { //checks that column is not full
                            gameManager.setLastPlacedPiece(piecePlaced);
                            gameManager.gameLoop(GameScreen.this);
                        }
                    });
            panel.add(buttons[i]); //adds buttons that player can use to choose column for placing a piece
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

    /*
    method for updating game board (array) when a piece is placed
     */
    public Pair piecePlaced(Integer column, boolean isPlayer) {
        int row = game.findLowestEmptyRow(gameBoard, column);
        if (row != -1) {
            gameBoard[row][column] = isPlayer ? gameManager.getPlayerNumber() : gameManager.getAiNumber();
            game.setGameBoard(gameBoard);
            gameManager.setPlayerTurn(!isPlayer);
            gameManager.updatePieceCount();
            return new Pair(row, column); //returns the pair to indicate a piece was successfully placed
        }
        return null; //returns null to indicate that a piece was not placed due to column being full
    }

    /*
    method that updates game board (UI) when a piece is placed
     */
    public void updateBoard() {
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

    /*
    Method that creates popup window for a tie game
     */
    public void drawPopup() {
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

    /*
    Method that creates popup window for a won game
     */
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
