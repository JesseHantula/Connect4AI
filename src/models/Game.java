package models;

public class Game {
    public int playerColor;
    public int aiColor;

    public Game(Integer playerColor) {
        this.playerColor = playerColor;
        this.aiColor = playerColor == 1 ? 2 : 1;
    }
}
