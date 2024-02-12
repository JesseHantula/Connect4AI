package models;

import java.util.List;

/*
Class that is used for the AI that the player plays against
 */
public class AI {
    public static final int rows = 6;
    public static final int cols = 7;
    public static int aiNumber;
    public static int playerNumber;
    public static final int MAX_VALUE = 9999999;
    public static final int MIN_VALUE = -9999999;
    public int depth = 9;

    /*
    Takes in aiNumber as a param, so that the AI knows which number to play as
     */
    public AI(Integer aiNumber) {
        AI.aiNumber = aiNumber;
        playerNumber = aiNumber == 1 ? 2 : 1; //player number always opposite of AI number
    }

    /*
    Sets depth of the minimax algorithm (this method is only used for testing)
    @param newDepth The new depth that is chosen for the algorithm
     */
    public void setDepth(int newDepth) { depth = newDepth; }

    /*
    Function that is called by game manager to calculate the column that the AI chooses
    @param game The current game model
    @param lastPlacedPiece The location of the last placed piece
    @param pieceCount The current number of pieces on the game board
     */
    public int calculate(Game game, Pair lastPlacedPiece, int pieceCount) {
        return minimax(game, game.getGameBoard(), depth, true, MIN_VALUE, MAX_VALUE, lastPlacedPiece, pieceCount)[0];
    }

    /*
    Function used for testing. Instead of returning column int as in calculate function, returns evaluation instead
    @param game The current game model
    @param lastPlacedPiece The location of the last placed piece
    @param pieceCount The current number of pieces on the game board
     */
    public int getEval(Game game, Pair lastPlacedPiece, int pieceCount) {
        return minimax(game, game.getGameBoard(), depth, true, MIN_VALUE, MAX_VALUE, lastPlacedPiece, pieceCount)[1];
    }

    /*
    Minimax function, uses recursion to find the best move for the AI
    @param game The current game model
    @param gameBoard The current game board
    @param depth The current depth
    @param maximizingPlayer The number of the AI (aiNumber)
    @param alpha Integer used for alpha-beta pruning (initial value is -9999999)
    @param beta Integer used for alpha-beta pruning (initial value is 9999999)
    @param lastPlacedPiece The location of the last placed piece
    @param pieceCount The current number of pieces on the game board
     */
    public static int[] minimax(Game game, int[][] gameBoard, int depth, boolean maximizingPlayer, int alpha, int beta, Pair lastPlacedPiece, int pieceCount) {
        List<Integer> valid_columns = game.getValidColumns(gameBoard); //get columns that are not full
        if (depth == 0 || (pieceCount > 6 && game.endGame(gameBoard, lastPlacedPiece, pieceCount))) {
            if (game.endGame(gameBoard, lastPlacedPiece, pieceCount)) {
                if (game.getWinner(gameBoard, lastPlacedPiece) == aiNumber)
                    return new int[]{-1, MAX_VALUE}; //AI winning position
                else if (game.getWinner(gameBoard, lastPlacedPiece) == playerNumber)
                    return new int[]{-1, MIN_VALUE}; //Player winning position
                else {
                    return new int[]{-1, 0}; //Draw
                }
            }
            else {
                return new int[]{-1, evaluateBoard(gameBoard)}; //Evaluate board if depth is 0
            }
        }
        if (maximizingPlayer) { //If AI's turn
            int bestCol = valid_columns.get(0); //Get first valid column (columns are ordered from middle to ends)
            int maxEval = MIN_VALUE;
            pieceCount++;
            for (int col : valid_columns) {
                int[][] newBoard = copyBoard(gameBoard);
                int row = game.findLowestEmptyRow(newBoard, col); //Finds lowest row that is empty for the column
                newBoard[row][col] = aiNumber;
                lastPlacedPiece.update(row, col);
                int eval = minimax(game, newBoard, depth - 1, false, alpha, beta, lastPlacedPiece, pieceCount)[1];
                if (eval > maxEval) { //updates evaluation of the position (if necessary)
                    maxEval = eval;
                    bestCol = col;
                }
                alpha = Math.max(alpha, maxEval);
                if (alpha >= beta) //alpha-beta pruning
                    break;
            }
            return new int[]{bestCol, maxEval};
        } else { //If player's turn
            int bestCol = valid_columns.get(0);
            int minEval = MAX_VALUE;
            pieceCount++;
            for (int col : valid_columns) {
                int[][] newBoard = copyBoard(gameBoard);
                int row = game.findLowestEmptyRow(newBoard, col);
                newBoard[row][col] = playerNumber;
                lastPlacedPiece.update(row, col);
                int eval = minimax(game, newBoard, depth - 1, true, alpha, beta, lastPlacedPiece, pieceCount)[1];
                if (eval < minEval) {
                    minEval = eval;
                    bestCol = col;
                }
                beta = Math.min(beta, minEval);
                if (alpha >= beta)
                    break;
            }
            return new int[]{bestCol, minEval};
        }
    }

    /*
    Method that evaluates game board's position. The higher the number, the more favorable it is for the AI.
    @param gameBoard The current state of the game board
    @param number The AI number
     */
    public static int evaluateBoard(int[][] gameBoard) {
        int score = 0;
        int playerNumber = aiNumber == 1 ? 2 : 1;

        // Evaluate middle column
        for(int i = 0; i < rows; i++) {
            score += gameBoard[i][3] == aiNumber ? 1 : 0;
            score -= gameBoard[i][3] == playerNumber ? 1 : 0;
        }

        // Evaluate vertical score
        score += evaluateDirection(gameBoard, aiNumber, 0, 1);
        score -= evaluateDirection(gameBoard, playerNumber, 0, 1);

        // Evaluate horizontal score
        score += evaluateDirection(gameBoard, aiNumber, 1, 0);
        score -= evaluateDirection(gameBoard, playerNumber, 1, 0);

        // Evaluate diagonal scores
        score += evaluateDirection(gameBoard, aiNumber, 1, 1); // diagonal top-left to bottom-right
        score -= evaluateDirection(gameBoard, playerNumber, 1, 1);
        score += evaluateDirection(gameBoard, aiNumber, 1, -1); // diagonal bottom-left to top-right
        score -= evaluateDirection(gameBoard, playerNumber, 1, -1);

        return score; //returns evaluation
    }


    /*
    Method that evaluates a certain direction (either vertical, horizontal, or diagonal)
    @param gameBoard The current state of the game board
    @param number The number of the AI
    @param rowDir 0 if evaluating vertical score, 1 if evaluating horizontal score / diagonal score
    @param colDir 0 if evaluating horizontal score, -1/1 if evaluating vertical score / diagonal score
     */
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
                        return MAX_VALUE; // Winning position
                    } else {
                        score += Math.pow(10, consecutiveCount); // Add consecutive count as 10^count
                    }
                }
            }
        }

        return score;
    }

    /*
    Method used to copy game board
    @param board Current state of the game board
     */
    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, cols);
        }
        return copy;
    }
}
