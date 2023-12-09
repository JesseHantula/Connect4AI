package coretest;

import core.GameManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*
Class that tests GameManager
 */
public class GameManagerTest {
    private GameManager gameManager;

    @Before
    public void setUp() {
        // Set up the GameManager object before each test
        gameManager = new GameManager(0);
    }

    @Test
    public void testInitialState() {
        assertEquals(0, gameManager.state);
    }

    @Test
    public void testSetState() {
        gameManager.setState(1);
        assertEquals(1, gameManager.state);
    }

    @Test
    public void testPlayerNumber() {
        gameManager.setPlayerNumber(1);
        assertEquals(1, gameManager.getPlayerNumber());
    }

    @Test
    public void testAINumber() {
        gameManager.setPlayerNumber(1);
        assertEquals(2, gameManager.getAiNumber());
    }

    @Test
    public void testPlayerTurn() {
        gameManager.setPlayerTurn(true);
        assertTrue(gameManager.getPlayerTurn());
    }

    @Test
    public void testUpdateMethod() {
        gameManager.setState(0);
        gameManager.update();
        assertNotNull(gameManager.screen);

        gameManager.setState(1);
        gameManager.update();
        assertNotNull(gameManager.screen);
    }
}
