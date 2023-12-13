package modelstest;

import models.AI;
import models.Game;
import models.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
Class that tests AI model (performance tests)
 */
public class AITest {
    private AI ai;
    private Game game;

    @Before
    public void setUp() { // Set up the GameManager object before each test
        game = new Game(2);
        ai = new AI(1);
        ai.setDepth(8);
    }

    /*
    Tests to see if AI can spot basic, one move wins at normal depth
     */
    @Test
    public void testAI_RowWin() {
        int[][] gameBoard =
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 2, 0}
            };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(3, 0);
        int col = ai.calculate(game, lastPlacedPiece, 6);
        assertEquals(col, 3);
    }

    @Test
    public void testAI_ColWin() {
        int[][] gameBoard =
                {
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {2, 0, 1, 0, 0, 0, 0},
                        {2, 2, 1, 0, 0, 0, 0},
                        {1, 1, 1, 2, 0, 2, 0}
                };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(3, 0);
        int col = ai.calculate(game, lastPlacedPiece, 10);
        assertEquals(col, 2);
    }

    @Test
    public void testAI_DiagWin() {
        int[][] gameBoard =
                {
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {2, 0, 1, 1, 0, 0, 0},
                        {2, 1, 1, 2, 0, 0, 0},
                        {1, 2, 2, 1, 2, 0, 0}
                };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(3, 0);
        int col = ai.calculate(game, lastPlacedPiece, 12);
        assertEquals(col, 3);
    }

    /*
    Tests to see if AI can spot basic defensive moves to prevent a loss
     */
    @Test
    public void testAI_ColDefense() {
        int[][] gameBoard =
                {
                        {0, 1, 0, 1, 0, 0, 0},
                        {0, 2, 2, 1, 0, 0, 0},
                        {0, 1, 2, 2, 0, 0, 0},
                        {0, 1, 2, 2, 2, 0, 0},
                        {0, 1, 1, 2, 1, 0, 0},
                        {0, 2, 1, 1, 1, 2, 0}
                };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(0, 1);
        int col = ai.calculate(game, lastPlacedPiece, 20);
        assertEquals(col, 2);
    }

    @Test
    public void testAI_RowDefense() {
        int[][] gameBoard =
                {
                        {0, 1, 1, 1, 0, 0, 0},
                        {0, 2, 2, 1, 0, 0, 0},
                        {0, 1, 2, 2, 0, 0, 0},
                        {0, 1, 2, 2, 2, 0, 0},
                        {0, 1, 1, 2, 1, 1, 0},
                        {0, 2, 1, 1, 1, 2, 2}
                };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(0, 1);
        int col = ai.calculate(game, lastPlacedPiece, 24);
        assertEquals(col, 5);
    }

    @Test
    public void testAI_DiagDefense() {
        int[][] gameBoard =
                {
                        {0, 1, 0, 1, 0, 0, 0},
                        {0, 2, 2, 1, 0, 0, 0},
                        {0, 1, 2, 2, 0, 0, 0},
                        {0, 1, 2, 2, 2, 0, 0},
                        {0, 1, 1, 2, 1, 0, 0},
                        {0, 2, 1, 1, 1, 2, 0}
                };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(0, 1);
        int col = ai.calculate(game, lastPlacedPiece, 21);
        assertEquals(col, 2);
    }

    /*
    Tests to make sure AI can find wins at certain depths
     */
    @Test
    public void testAI_VictoryAtDepth0() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 2, 2, 2}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(5, 6);
        ai.setDepth(1);
        int col = ai.calculate(game, lastPlacedPiece, 6);
        assertEquals(col, 3);
    }

    @Test
    public void testAI_VictoryAtDepth5() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 2, 2, 2}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(5, 6);
        ai.setDepth(5);
        int col = ai.calculate(game, lastPlacedPiece, 6);
        assertEquals(col, 3);
    }

    /*
    Tests to make sure AI can find wins that take several moves to accomplish
     */
    @Test
    public void testAI_VictoryAfter2Moves() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0},
                {0, 2, 0, 2, 0, 0, 0},
                {0, 2, 0, 1, 1, 1, 0},
                {2, 2, 0, 1, 1, 2, 2}
        };
        game.setGameBoard(gameBoard);
        ai.setDepth(3);
        Pair lastPlacedPiece = new Pair(5, 6);
        int col = ai.calculate(game, lastPlacedPiece, 14);
        assertEquals(col, 2);
    }

    /*
    Tests to check speed of algorithm at given depths
     */
    @Test
    public void testAI_SpeedAtDepth2() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(-1, -1);
        ai.setDepth(2);
        long startTime = System.currentTimeMillis();
        ai.calculate(game, lastPlacedPiece, 6);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        assertTrue(executionTime >= 0);
        System.out.println("Time taken for depth 2 (ms): " + executionTime);
    }

    @Test
    public void testAI_SpeedAtDepth5() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(-1, -1);
        ai.setDepth(5);
        long startTime = System.currentTimeMillis();
        ai.calculate(game, lastPlacedPiece, 6);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        assertTrue(executionTime >= 0);
        System.out.println("Time taken for depth 5 (ms): " + executionTime);
    }

    @Test
    public void testAI_SpeedAtDepth8() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(-1, -1);
        ai.setDepth(8);
        long startTime = System.currentTimeMillis();
        ai.calculate(game, lastPlacedPiece, 6);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        assertTrue(executionTime >= 0);
        System.out.println("Time taken for depth 8 (ms): " + executionTime);
    }

    @Test
    public void testAI_SpeedAtDepth10() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(-1, -1);
        ai.setDepth(10);
        long startTime = System.currentTimeMillis();
        ai.calculate(game, lastPlacedPiece, 6);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        assertTrue(executionTime >= 0);
        System.out.println("Time taken for depth 10 (ms): " + executionTime);
    }

    @Test
    public void testAI_SpeedAtDepth12() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(-1, -1);
        ai.setDepth(12);
        long startTime = System.currentTimeMillis();
        ai.calculate(game, lastPlacedPiece, 6);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        assertTrue(executionTime >= 0);
        System.out.println("Time taken for depth 12 (ms): " + executionTime);
    }

    /*
    Tests to make sure board evaluation is correct for positions
     */

    @Test
    public void testAI_EvalWinIn3Moves() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0},
                {0, 2, 0, 2, 0, 0, 0},
                {0, 2, 0, 1, 1, 1, 0},
                {2, 2, 0, 1, 1, 2, 2}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(5, 6);
        int eval = ai.getEval(game, lastPlacedPiece, 14);
        assertEquals(eval, AI.MAX_VALUE);
    }

    @Test
    public void testAI_EvalWinIn7Moves() {
        int[][] gameBoard = {
                {2, 2, 0, 2, 1, 1, 0},
                {1, 1, 0, 1, 2, 2, 0},
                {1, 1, 0, 2, 2, 1, 2},
                {2, 2, 0, 1, 1, 2, 2},
                {1, 1, 0, 2, 1, 2, 2},
                {1, 2, 0, 1, 1, 2, 1}
        };
        game.setGameBoard(gameBoard);
        ai.setDepth(7);
        Pair lastPlacedPiece = new Pair(2, 6);
        //Let's simulate the game to see that the moves and evaluations are correct.
        int eval = ai.getEval(game, lastPlacedPiece, 34);
        int col = ai.calculate(game, lastPlacedPiece, 34);
        assertEquals(eval, AI.MAX_VALUE);
        assertEquals(col, 6);
        //Make a move as the player, update gameboard
        gameBoard = new int[][]{
                {2, 2, 0, 2, 1, 1, 2},
                {1, 1, 0, 1, 2, 2, 1},
                {1, 1, 0, 2, 2, 1, 2},
                {2, 2, 0, 1, 1, 2, 2},
                {1, 1, 0, 2, 1, 2, 2},
                {1, 2, 0, 1, 1, 2, 1}
        };
        game.setGameBoard(gameBoard);
        lastPlacedPiece.update(0, 6);
        //Make sure evaluation is still winning (column is not needed, since only 1 available)
        eval = ai.getEval(game, lastPlacedPiece, 36);
        assertEquals(eval, AI.MAX_VALUE);
        //Do this again
        gameBoard = new int[][]{
                {2, 2, 0, 2, 1, 1, 2},
                {1, 1, 0, 1, 2, 2, 1},
                {1, 1, 0, 2, 2, 1, 2},
                {2, 2, 0, 1, 1, 2, 2},
                {1, 1, 2, 2, 1, 2, 2},
                {1, 2, 1, 1, 1, 2, 1}
        };
        game.setGameBoard(gameBoard);
        lastPlacedPiece.update(4, 2);
        eval = ai.getEval(game, lastPlacedPiece, 38);
        assertEquals(eval, AI.MAX_VALUE);
        //One more time
        gameBoard = new int[][]{
                {2, 2, 0, 2, 1, 1, 2},
                {1, 1, 0, 1, 2, 2, 1},
                {1, 1, 2, 2, 2, 1, 2},
                {2, 2, 1, 1, 1, 2, 2},
                {1, 1, 2, 2, 1, 2, 2},
                {1, 2, 1, 1, 1, 2, 1}
        };
        game.setGameBoard(gameBoard);
        lastPlacedPiece.update(2, 2);
        eval = ai.getEval(game, lastPlacedPiece, 40);
        assertEquals(eval, AI.MAX_VALUE);
    }
}
