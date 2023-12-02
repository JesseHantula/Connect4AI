package modelstest;

import models.AI;
import models.Game;
import models.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AITest {
    private AI ai;
    private Game game;

    @Before
    public void setUp() {
        // Set up the GameManager object before each test
        game = new Game(2);
        ai = new AI(1);
    }

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
                        {1, 0, 0, 0, 0, 0, 0},
                        {2, 0, 0, 0, 0, 0, 0},
                        {2, 2, 1, 0, 0, 0, 0},
                        {2, 2, 1, 1, 0, 0, 0},
                        {1, 1, 1, 2, 2, 2, 1}
                };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(5, 5);
        int col = ai.calculate(game, lastPlacedPiece, 16);
        assertEquals(col, 1);
    }

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
        int col = ai.calculate(game, lastPlacedPiece, 21);
        assertEquals(col, 2);
    }

    @Test
    public void testAI_ColDefense2() {
        int[][] gameBoard =
                {
                        {0, 1, 1, 1, 2, 0, 0},
                        {0, 1, 1, 1, 2, 2, 1},
                        {0, 2, 2, 2, 1, 2, 2},
                        {0, 2, 2, 2, 1, 2, 1},
                        {0, 1, 1, 1, 2, 1, 2},
                        {0, 2, 2, 1, 1, 1, 2}
                };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(1, 5);
        int col = ai.calculate(game, lastPlacedPiece, 34);
        assertEquals(col, 5);
    }

    @Test
    public void testAI_Random() {
        int[][] gameBoard = {
                {0, 0, 1, 1, 2, 1, 0},
                {0, 0, 2, 1, 1, 2, 0},
                {0, 0, 2, 2, 2, 1, 0},
                {0, 0, 1, 2, 2, 2, 0},
                {2, 1, 1, 1, 2, 1, 0},
                {2, 1, 2, 2, 1, 1, 2}
        };
        game.setGameBoard(gameBoard);
        Pair lastPlacedPiece = new Pair(4, 0);
        int col = ai.calculate(game, lastPlacedPiece, 29);
        assertEquals(col, 1);
    }

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
        ai.setDepth(0);
        int col = ai.calculate(game, lastPlacedPiece, 6);
        assertEquals(col, 3);
    }
}
