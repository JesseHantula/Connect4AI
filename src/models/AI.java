package models;

import java.util.Random;

public class AI {
    public static final int rows = 6;
    public static final int cols = 7;
    public static int aiNumber;
    public static int playerNumber;
    public static final int MAX_VALUE = 999999;
    public static final int MIN_VALUE = -999999;
    public static Random random = new Random();

    public AI(Integer aiNumber) {
        AI.aiNumber = aiNumber;
        playerNumber = aiNumber == 1 ? 2 : 1;
    }

    public int calculate(Game game) {
        return minimax(game, game.getGameBoard(), 4, true)[0];
    }

    public static int[] minimax(Game game, int[][] gameboard, int depth, boolean maximizingPlayer) {
        int[] valid_columns = game.getValidColumns(gameboard);

        if (depth == 0 || game.endGame(gameboard)) {
            if (game.endGame(gameboard)) {
                if (game.getWinner() == aiNumber)
                    return new int[]{-1, MAX_VALUE};
                else if (game.getWinner() == playerNumber)
                    return new int[]{-1, MIN_VALUE};
                else {
                    return new int[]{-1, 0};
                }
            }
            else {
                return new int[]{-1, evaluateBoard(gameboard, aiNumber)};
            }
        }
        if (maximizingPlayer) {
            int bestCol = valid_columns[random.nextInt(valid_columns.length)];
            int maxEval = MIN_VALUE;
            for (int col = 0; col < cols; col++) {
                int[][] newBoard = copyBoard(gameboard);
                int row = game.findLowestEmptyRow(newBoard, col);
                if (row != -1) {
                    newBoard[row][col] = aiNumber;
                    int eval = minimax(game, newBoard, depth - 1, false)[1];
                    if (eval > maxEval) {
                        maxEval = eval;
                        bestCol = col;
                    }
                }
            }
            return new int[]{bestCol, maxEval};
        } else {
            int bestCol = valid_columns[random.nextInt(valid_columns.length)];
            int minEval = MAX_VALUE;
            for (int col = 0; col < cols; col++) {
                int[][] newBoard = copyBoard(gameboard);
                int row = game.findLowestEmptyRow(newBoard, col);
                if (row != -1) {
                    newBoard[row][col] = playerNumber;
                    int eval = minimax(game, newBoard, depth - 1, true)[1];
                    if (eval < minEval) {
                        minEval = eval;
                        bestCol = col;
                    }
                }
            }
            return new int[]{bestCol, minEval};
        }
    }

    public static int evaluateBoard(int[][] gameboard, int number) {
        int score = 0;

        // Evaluate middle column
        for(int i = 0; i < rows; i++)
            score += gameboard[i][3] == number ? 1 : 0;

        // Evaluate horizontal score
        score += evaluateDirection(gameboard, number, 0, 1);

        // Evaluate vertical score
        score += evaluateDirection(gameboard, number, 1, 0);

        // Evaluate diagonal scores
        score += evaluateDirection(gameboard, number, 1, 1); // diagonal top-left to bottom-right
        score += evaluateDirection(gameboard, number, 1, -1); // diagonal bottom-left to top-right

        return score;
    }

    public static int evaluateDirection(int[][] gameboard, int number, int rowDir, int colDir) {
        int score = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (gameboard[row][col] == number) {
                    // Check for consecutive pieces in the specified direction
                    int consecutiveCount = 0;
                    for (int k = 0; k < 4; k++) {
                        int newRow = row + k * rowDir;
                        int newCol = col + k * colDir;

                        // Check if the position is within bounds
                        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                            if (gameboard[newRow][newCol] == number) {
                                consecutiveCount++;
                            } else {
                                break;
                            }
                        }
                    }

                    // Update the score based on consecutive count
                    if (consecutiveCount >= 4) {
                        // Winning position
                        return MAX_VALUE;
                    } else {
                        score += Math.pow(10, consecutiveCount);
                    }
                }
            }
        }

        return score;
    }

    // Copy the game board to create a new instance
    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, cols);
        }
        return copy;
    }
}
