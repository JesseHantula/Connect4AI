package models;

public class Game {
    public int playerNumber;
    public int aiNumber;
    public int[][] gameBoard;
    public int numRows;
    public int numCols;
    public AI ai;
    public int winner;

    public Game(Integer playerNumber) {
        this.playerNumber = playerNumber;
        this.aiNumber = playerNumber == 1 ? 2 : 1;
        this.numRows = 6;
        this.numCols = 7;
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

    public boolean endGame() {
        return winGame() || fullGame();
    }

    public boolean winGame() {
        return rowWinner() || columnWinner() || diagonalWinner();
    }

    public int getWinner() { return winner; }

    public boolean rowWinner() {
        for (int[] row : gameBoard) {
            int streak = 0;
            int curr = 0;
            for (int square : row) {
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
                    winner = curr;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean columnWinner() {
        int cols = gameBoard[0].length;
        for (int col = 0; col < cols; col++) {
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
                            winner = curr;
                            return true;
                        }
                    } else {
                        streak = 1;
                        curr = ints[col];
                    }
                }
            }
        }
        return false;
    }

    public boolean diagonalWinner() {
        int rows = gameBoard.length;
        int cols = gameBoard[0].length;

        for (int row = 0; row < rows - 3; row++) {
            for (int col = 0; col < cols - 3; col++) {
                if (gameBoard[row][col] != 0
                        && gameBoard[row][col] == gameBoard[row + 1][col + 1]
                        && gameBoard[row][col] == gameBoard[row + 2][col + 2]
                        && gameBoard[row][col] == gameBoard[row + 3][col + 3]) {
                    winner = gameBoard[row][col];
                    return true;
                }
            }
        }

        for (int row = 0; row < rows - 3; row++) {
            for (int col = 3; col < cols; col++) {
                if (gameBoard[row][col] != 0
                        && gameBoard[row][col] == gameBoard[row + 1][col - 1]
                        && gameBoard[row][col] == gameBoard[row + 2][col - 2]
                        && gameBoard[row][col] == gameBoard[row + 3][col - 3]) {
                    winner = gameBoard[row][col];
                    return true;
                }
            }
        }

        return false;
    }

    public boolean fullGame() {
        for (int[] row : gameBoard) {
            for (int square : row) {
                if (square == 0)
                    return false;
            }
        }
        return true;
    }

    public int findLowestEmptyRow(int col) {
        for (int row = numRows - 1; row >= 0; row--) {
            if (gameBoard[row][col] == 0) {
                return row;
            }
        }
        return -1;
    }
}
