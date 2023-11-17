package coretest;

import models.AI;
import models.Game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        // Set up the GameManager object before each test
        game = new Game(1);
    }

    @Test
    public void testGetGameBoard() {
        int[][] gameBoard = game.getGameBoard();
        assertEquals(gameBoard, new int[6][7]);
    }

    @Test
    public void testSetGameBoard() {
        int[][] newGameBoard = new int[7][7];
        game.setGameBoard(newGameBoard);
        assertEquals(newGameBoard, game.getGameBoard());
    }

    @Test
    public void testGetAI() {
        AI ai = game.getAi();
        assertNotNull(ai);
    }

    @Test
    public void testRowWinner() {
        int[][] rowWinner = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0}
        };
        game.setGameBoard(rowWinner);
        assertTrue(game.rowWinner());
    }

    @Test
    public void testColumnWinner() {
        int[][] columnWinner = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0}
        };
        game.setGameBoard(columnWinner);
        assertTrue(game.columnWinner());
    }

    @Test
    public void testDiagonalWinner() {
        int[][] diagonalWinner = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0}
        };
        game.setGameBoard(diagonalWinner);
        assertTrue(game.diagonalWinner());
    }
}
