package modelstest;

import core.GameManager;
import models.AI;
import models.Game;
import models.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    private GameManager gameManager;

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
        Pair lastPlacedPiece = new Pair(5, 1);
        int winner = game.rowWinner(rowWinner, lastPlacedPiece);
        assertEquals(winner, 1);
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
        Pair lastPlacedPiece = new Pair(1, 1);
        int winner = game.columnWinner(columnWinner, lastPlacedPiece);
        assertEquals(winner, 1);
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
        int winner = game.diagonalWinner(diagonalWinner);
        assertEquals(winner, 1);

        int[][] diagonalWinner2 = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0}
        };
        int winner2 = game.diagonalWinner(diagonalWinner2);
        assertEquals(winner2, 1);
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
        Pair lastPlacedPiece = new Pair(5, 1);
        assertTrue(game.winGame(winner, lastPlacedPiece));
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
        Pair lastPlacedPiece = new Pair(5, 1);
        assertTrue(game.endGame(gameEnder, lastPlacedPiece, 5));
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
        Pair lastPlacedPiece = new Pair(5, 1);
        game.winGame(winner, lastPlacedPiece);
        game.setGameBoard(winner);
        assertEquals(1, game.getWinner(game.getGameBoard(), lastPlacedPiece));
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

    @Test
    public void testGetValidColumns() {
        int[][] board = {
                {0, 1, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 0}
        };

        List<Integer> validColumns = new ArrayList<>(Arrays.asList(0, 2, 4, 5, 6));
        List<Integer> methodTester = game.getValidColumns(board);
        Collections.sort(methodTester);
        assertEquals(validColumns, methodTester);
    }
}
