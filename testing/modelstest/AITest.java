package modelstest;

import models.AI;
import models.Game;
import org.junit.Before;

public class AITest {
    private AI ai;

    @Before
    public void setUp() {
        // Set up the GameManager object before each test
        ai = new AI(1);
    }
}
