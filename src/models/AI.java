package models;

import java.util.List;

public class AI {
    public static final int rows = 6;
    public static final int cols = 7;
    public static int aiNumber;
    public static int playerNumber;
    public static final int MAX_VALUE = 9999999;
    public static final int MIN_VALUE = -9999999;
    public int depth = 8;

    public AI(Integer aiNumber) {
        AI.aiNumber = aiNumber;
        playerNumber = aiNumber == 1 ? 2 : 1;
    }

    public void setDepth(int newDepth) { depth = newDepth; }

    public int calculate(Game game, Pair lastPlacedPiece, int pieceCount) {
        return minimax(game, game.getGameBoard(), depth, true, MIN_VALUE, MAX_VALUE, lastPlacedPiece, pieceCount)[0];
    }

    public static int[] minimax(Game game, int[][] gameBoard, int depth, boolean maximizingPlayer, int alpha, int beta, Pair lastPlacedPiece, int pieceCount) {
        List<Integer> valid_columns = game.getValidColumns(gameBoard);
        if (depth == 0 || (pieceCount > 6 && game.endGame(gameBoard, lastPlacedPiece, pieceCount))) {
            if (game.endGame(gameBoard, lastPlacedPiece, pieceCount)) {
                if (game.getWinner(gameBoard, lastPlacedPiece) == aiNumber)
                    return new int[]{-1, MAX_VALUE};
                else if (game.getWinner(gameBoard, lastPlacedPiece) == playerNumber)
                    return new int[]{-1, MIN_VALUE};
                else {
                    return new int[]{-1, 0};
                }
            }
            else {
                return new int[]{-1, evaluateBoard(gameBoard, aiNumber)};
            }
        }
        if (maximizingPlayer) {
            int bestCol = valid_columns.get(0);
            int maxEval = MIN_VALUE;
            for (int col : valid_columns) {
                int[][] newBoard = copyBoard(gameBoard);
                int row = game.findLowestEmptyRow(newBoard, col);
                newBoard[row][col] = aiNumber;
                pieceCount++;
                lastPlacedPiece.update(row, col);
                int eval = minimax(game, newBoard, depth - 1, false, alpha, beta, lastPlacedPiece, pieceCount)[1];
                if (eval > maxEval) {
                    maxEval = eval;
                    bestCol = col;
                }
                alpha = Math.max(alpha, maxEval);
                if (alpha >= beta)
                    break;
            }
            return new int[]{bestCol, maxEval};
        } else {
            int bestCol = valid_columns.get(0);
            int minEval = MAX_VALUE;
            for (int col : valid_columns) {
                int[][] newBoard = copyBoard(gameBoard);
                int row = game.findLowestEmptyRow(newBoard, col);
                newBoard[row][col] = playerNumber;
                pieceCount++;
                lastPlacedPiece.update(row, col);
                int eval = minimax(game, newBoard, depth - 1, true, alpha, beta, lastPlacedPiece, pieceCount)[1];
                if (eval < minEval) {
                    minEval = eval;
                    bestCol = col;
                }
                beta = Math.min(beta, minEval);
                if (beta < alpha)
                    break;
            }
            return new int[]{bestCol, minEval};
        }
    }

    public static int evaluateBoard(int[][] gameBoard, int number) {
        int score = 0;

        // Evaluate middle column
        for(int i = 0; i < rows; i++)
            score += gameBoard[i][3] == number ? 1 : 0;

        // Evaluate horizontal score
        score += evaluateDirection(gameBoard, number, 0, 1);

        // Evaluate vertical score
        score += evaluateDirection(gameBoard, number, 1, 0);

        // Evaluate diagonal scores
        score += evaluateDirection(gameBoard, number, 1, 1); // diagonal top-left to bottom-right
        score += evaluateDirection(gameBoard, number, 1, -1); // diagonal bottom-left to top-right

        return score;
    }

    public static int evaluateDirection(int[][] gameBoard, int number, int rowDir, int colDir) {
        int score = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (gameBoard[row][col] == number) {
                    // Check for consecutive pieces in the specified direction
                    int consecutiveCount = 0;
                    for (int k = 0; k < 4; k++) {
                        int newRow = row + k * rowDir;
                        int newCol = col + k * colDir;

                        // Check if the position is within bounds
                        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                            if (gameBoard[newRow][newCol] == number) {
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
