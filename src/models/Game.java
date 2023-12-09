package models;

import java.util.ArrayList;
import java.util.List;

/*
Class that acts as a game model
 */
public class Game {
    public final int aiNumber;
    public int[][] gameBoard;
    public final int numRows;
    public final AI ai;

    /*
    Takes in value of player number (either 1 or 2)
     */
    public Game(Integer playerNumber) {
        this.aiNumber = playerNumber == 1 ? 2 : 1;
        this.numRows = 6;
        this.gameBoard = new int[6][7];
        this.ai = new AI(aiNumber);
    }

    /*
    Method used to get current state of the game board
     */
    public int[][] getGameBoard() {
        return gameBoard;
    }

    /*
    Method used to set state of the game board
    @param newGameBoard New state of the game board
     */
    public void setGameBoard(int[][] newGameBoard) { gameBoard = newGameBoard; }

    /*
    Returns the AI model
     */
    public AI getAi() {
        return ai;
    }

    /*
    Method that checks if a game has ended
    @param gameBoard Current state of the game board
    @param lastPlacedPiece Location of the last placed piece (row, col)
    @param pieceCount Current number of pieces on the board
     */
    public boolean endGame(int[][] gameBoard, Pair lastPlacedPiece, int pieceCount) {
        return (winGame(gameBoard, lastPlacedPiece) || fullGame(pieceCount));
    }

    /*
    Method that checks if a game has been won
    @param gameBoard Current state of the game board
    @param lastPlacedPiece Location of the last placed piece (row, col)
    @param pieceCount Current number of pieces on the board
     */
    public boolean winGame(int[][] gameBoard, Pair lastPlacedPiece) {
        return (rowWinner(gameBoard, lastPlacedPiece) != null) ||
                (columnWinner(gameBoard, lastPlacedPiece) != null) ||
                (diagonalWinner(gameBoard) != null);
    }

    /*
    Method that gets the player number of the winner (either 1 or 2). Returns 0 if no winner.
    @param gameBoard Current state of the game board
    @param lastPlacedPiece Location of the last placed piece (row, col)
     */
    public int getWinner(int[][] gameBoard, Pair lastPlacedPiece) {
        if (rowWinner(gameBoard, lastPlacedPiece) != null) //check for win by row
            return rowWinner(gameBoard, lastPlacedPiece);
        else if (columnWinner(gameBoard, lastPlacedPiece) != null) //check for win by column
            return columnWinner(gameBoard, lastPlacedPiece);
        else if (diagonalWinner(gameBoard) != null) //check for win by diagonal
            return diagonalWinner(gameBoard);
        return 0; //no winner, return 0
    }

    /*
    Method that checks for win by row
    @param gameBoard Current state of the game board
    @param lastPlacedPiece Location of the last placed piece (row, col)
     */
    public Integer rowWinner(int[][] gameBoard, Pair lastPlacedPiece) {
        int row = lastPlacedPiece.getRow();
        int streak = 0;
        int curr = 0;
        for (int square : gameBoard[row]) {
            if (square == 0) {
                streak = 0;
                curr = 0;
            } else if (curr == 0) {
                curr = square;
                streak = 1;
            } else {
                if (square == curr)
                    streak++;
                else {
                    streak = 1;
                    curr = square;
                }
            }
            if (streak == 4) {
                return curr; //winner has been found, return the number of winning player
            }
        }
        return null; //no winner found, return null
    }

    /*
    Method that checks for win by row
    @param gameBoard Current state of the game board
    @param lastPlacedPiece Location of the last placed piece (row, col)
     */
    public Integer columnWinner(int[][] gameBoard, Pair lastPlacedPiece) {
        int col = lastPlacedPiece.getColumn();
        int streak = 0;
        int curr = 0;
        for (int[] ints : gameBoard) {
            if (ints[col] == 0) {
                streak = 0;
                curr = 0;
            } else if (curr == 0) {
                curr = ints[col];
                streak = 1;
            } else {
                if (ints[col] == curr) {
                    streak++;
                    if (streak == 4) {
                        return curr; //winner has been found, return the number of the winning player
                    }
                } else {
                    streak = 1;
                    curr = ints[col];
                }
            }
        }
        return null; //no winner found, return null
    }

    /*
    Method that checks for win by row
    @param gameBoard Current state of the game board
     */
    public Integer diagonalWinner(int[][] gameBoard) {
        int rows = gameBoard.length;
        int cols = gameBoard[0].length;

        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (gameBoard[row][col] != 0
                        && gameBoard[row][col] == gameBoard[row + 1][col + 1]
                        && gameBoard[row][col] == gameBoard[row + 2][col + 2]
                        && gameBoard[row][col] == gameBoard[row + 3][col + 3]) {
                    return gameBoard[row][col]; //winner has been found, return the number of the winning player
                }
            }
        }

        for (int row = 0; row < rows - 3; row++) {
            for (int col = 3; col < cols; col++) {
                if (gameBoard[row][col] != 0
                        && gameBoard[row][col] == gameBoard[row + 1][col - 1]
                        && gameBoard[row][col] == gameBoard[row + 2][col - 2]
                        && gameBoard[row][col] == gameBoard[row + 3][col - 3]) {
                    return gameBoard[row][col]; //winner has been found, return the number of the winning player
                }
            }
        }

        return null; //no winner found, return null
    }

    /*
    Method that finds lowest empty row for a column
    @param gameBoard Current state of the game board
    @param col Column that is being checked
     */
    public int findLowestEmptyRow(int[][] gameBoard, int col) {
        for (int row = numRows - 1; row >= 0; row--) {
            if (gameBoard[row][col] == 0) {
                return row;
            }
        }
        return -1; //return -1 if the column is full
    }

    /*
    Method that returns a list of non-full columns
    @param gameBoard Current state of the game board
     */
    public List<Integer> getValidColumns(int[][] gameBoard) {
        int[] colOrder = {3, 2, 4, 1, 5, 0, 6}; //start with more important columns, this helps alpha-beta pruning
        List<Integer> validColumns = new ArrayList<>();
        for (int column : colOrder) {
            if (gameBoard[0][column] == 0)
                validColumns.add(column);
        }
        return validColumns;
    }

    /*
    Method that checks if the game board is full
    @param pieceCount Current number of pieces on the game board
     */
    public boolean fullGame(int pieceCount) {
        return pieceCount == 42; //game board is full if there are 42 pieces on it
    }
}
