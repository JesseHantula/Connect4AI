import core.GameManager;

public class MainApp {
    public MainApp() {

    }
    public static void main(String[] args) {
        GameManager gameManager = new GameManager(0);

        startGameLoop(gameManager);
    }

    private static void startGameLoop(GameManager gameManager) {
        gameManager.update();
    }
}
