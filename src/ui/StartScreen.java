package ui;

import constants.Constants;

import javax.swing.JFrame;

public class StartScreen {
    protected JFrame startScreen;

    public StartScreen() {
        this.startScreen = new JFrame("Connect 4");
    }

    public void createStartScreen() {
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setSize(Constants.startScreenWidth, Constants.startScreenHeight);
        startScreen.setLocationRelativeTo(null);
        startScreen.setVisible(true);
    }
}
