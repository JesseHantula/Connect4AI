package modelstest;

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
                {2, 0, 0, 0, 0, 0, 0},
                {2, 1, 1, 1, 1, 2, 0}
        };
        assertTrue(game.rowWinner(rowWinner));
    }

    @Test
    public void testColumnWinner() {
        int[][] columnWinner = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {2, 1, 0, 0, 0, 0, 0},
                {1, 2, 2, 2, 0, 0, 0}
        };
        assertTrue(game.columnWinner(columnWinner));
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
        assertTrue(game.diagonalWinner(diagonalWinner));

        int[][] diagonalWinner2 = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0}
        };
        assertTrue(game.diagonalWinner(diagonalWinner2));
    }

    @Test
    public void testFullGame() {
        int[][] fullGame = {
                {2, 2, 1, 1, 2, 2, 1},
                {1, 1, 2, 2, 2, 1, 1},
                {1, 1, 1, 2, 1, 2, 1},
                {2, 2, 2, 1, 1, 1, 2},
                {1, 2, 1, 1, 2, 2, 2},
                {1, 1, 2, 2, 1, 1, 1}
        };
        assertTrue(game.fullGame(fullGame));
    }

    @Test
    public void testWonGame() {
        int[][] winner = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0}
        };
        assertTrue(game.winGame(winner));
    }

    @Test
    public void testEndGame() {
        int[][] gameEnder = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0}
        };
        assertTrue(game.endGame(gameEnder));
    }

    @Test
    public void testGetWinner() {
        int[][] winner = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0}
        };
        game.winGame(winner);
        assertEquals(1, game.getWinner());
    }

    @Test
    public void testLowestRow() {
        int[][] board = {
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 0}
        };
        game.setGameBoard(board);
        int col1 = game.findLowestEmptyRow(game.getGameBoard(), 0);
        assertEquals(col1, 5);

        int col2 = game.findLowestEmptyRow(game.getGameBoard(), 1);
        assertEquals(col2, -1);

        int col3 = game.findLowestEmptyRow(game.getGameBoard(), 3);
        assertEquals(col3, 4);
    }
}
