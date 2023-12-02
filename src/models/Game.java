package models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final int aiNumber;
    public int[][] gameBoard;
    public final int numRows;
    public final AI ai;

    public Game(Integer playerNumber) {
        this.aiNumber = playerNumber == 1 ? 2 : 1;
        this.numRows = 6;
        this.gameBoard = new int[6][7];
        this.ai = new AI(aiNumber);
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] newGameBoard) { gameBoard = newGameBoard; }

    public AI getAi() {
        return ai;
    }

    public boolean endGame(int[][] gameBoard, Pair lastPlacedPiece, int pieceCount) {
        return (winGame(gameBoard, lastPlacedPiece) || fullGame(pieceCount));
    }

    public boolean winGame(int[][] gameBoard, Pair lastPlacedPiece) {
        return (rowWinner(gameBoard, lastPlacedPiece) != null) ||
                (columnWinner(gameBoard, lastPlacedPiece) != null) ||
                (diagonalWinner(gameBoard) != null);
    }

    public int getWinner(int[][] gameBoard, Pair lastPlacedPiece) {
        if (rowWinner(gameBoard, lastPlacedPiece) != null)
            return rowWinner(gameBoard, lastPlacedPiece);
        else if (columnWinner(gameBoard, lastPlacedPiece) != null)
            return columnWinner(gameBoard, lastPlacedPiece);
        else if (diagonalWinner(gameBoard) != null)
            return diagonalWinner(gameBoard);
        return 0;
    }

    public Integer rowWinner(int[][] gameBoard, Pair lastPlacedPiece) { //check to see if a game has been won via 4 consecutive pieces in a row
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
                return curr;
            }
        }
        return null;
    }

    public Integer columnWinner(int[][] gameBoard, Pair lastPlacedPiece) { //check to see if a game has been won via 4 consecutive pieces in a column
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
                        return curr;
                    }
                } else {
                    streak = 1;
                    curr = ints[col];
                }
            }
        }
        return null;
    }

    public Integer diagonalWinner(int[][] gameBoard) { //check to see if a game has been won via 4 consecutive pieces in a diagonal
        int rows = gameBoard.length;
        int cols = gameBoard[0].length;

        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (gameBoard[row][col] != 0
                        && gameBoard[row][col] == gameBoard[row + 1][col + 1]
                        && gameBoard[row][col] == gameBoard[row + 2][col + 2]
                        && gameBoard[row][col] == gameBoard[row + 3][col + 3]) {
                    return gameBoard[row][col];
                }
            }
        }

        for (int row = 0; row < rows - 3; row++) {
            for (int col = 3; col < cols; col++) {
                if (gameBoard[row][col] != 0
                        && gameBoard[row][col] == gameBoard[row + 1][col - 1]
                        && gameBoard[row][col] == gameBoard[row + 2][col - 2]
                        && gameBoard[row][col] == gameBoard[row + 3][col - 3]) {
                    return gameBoard[row][col];
                }
            }
        }

        return null;
    }

    public int findLowestEmptyRow(int[][] gameBoard, int col) { //find the lowest empty row for a column
        for (int row = numRows - 1; row >= 0; row--) {
            if (gameBoard[row][col] == 0) {
                return row;
            }
        }
        return -1; //return -1 if the column is full
    }

    public List<Integer> getValidColumns(int[][] gameBoard) {
        int[] colOrder = {3, 2, 4, 1, 5, 0, 6}; //start with more important columns, this helps alpha-beta pruning
        List<Integer> validColumns = new ArrayList<>();
        for (int column : colOrder) {
            if (gameBoard[0][column] == 0)
                validColumns.add(column);
        }
        return validColumns;
    }

    public boolean fullGame(int pieceCount) {
        return pieceCount == 42;
    }
}
