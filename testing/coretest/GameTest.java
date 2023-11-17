package modelstest;

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
}
