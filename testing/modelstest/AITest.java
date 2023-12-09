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

    @Test
    public void testAI_VictoryAfter3Moves() {
        int[][] gameBoard = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0},
                {0, 2, 0, 2, 0, 0, 0},
                {0, 2, 0, 1, 1, 0, 0},
                {1, 2, 0, 1, 2, 2, 0}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(5, 6);
        int col = ai.calculate(game, lastPlacedPiece, 14);
        assertEquals(col, 5);
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
}
