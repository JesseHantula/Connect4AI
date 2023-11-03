package models;

import java.util.Random;

public class AI {

    public int aiNumber;
    public AI(Integer aiNumber) {
        this.aiNumber = aiNumber;
    }

    public int calculate(int[][] gameBoard) {
        Random random = new Random();
        return random.nextInt(gameBoard[0].length);
    }
}
