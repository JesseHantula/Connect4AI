package ui;

import constants.Constants;
import core.GameManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;

/*
Class that creates the start screen UI
 */
public class StartScreen {
    protected final JFrame startScreen;
    protected final GameManager gameManager;

    /*
    Takes in startScreen JFrame and game manager as params
     */
    public StartScreen(JFrame startScreen, GameManager gameManager) {
        this.startScreen = startScreen;
        this.gameManager = gameManager;
    }

    /*
    Method to initialize the start screen
     */
    public void createStartScreen() {
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setSize(Constants.startScreenWidth, Constants.startScreenHeight);
        startScreen.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titleLabel = new JLabel("CONNECT 4");
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);

        panel.add(titlePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton redButton = UIUtils.createButton("Player 1", Color.RED, 150, 50);
        buttonPanel.add(redButton);

        JButton blueButton = UIUtils.createButton("Player 2", Color.BLUE, 150, 50);
        buttonPanel.add(blueButton);

        redButton.addActionListener(ev -> redButtonClicked());
        blueButton.addActionListener(ev -> blueButtonClicked());

        panel.add(buttonPanel);

        startScreen.add(panel);
        startScreen.setVisible(true);
    }

    /*
    Method that is called if player chooses red button (player 1)
     */
    private void redButtonClicked() {
        gameManager.setPlayerNumber(1);
        gameManager.setPlayerTurn(true);
        gameManager.setState(1);
        gameManager.update();
    }

    /*
    Method that is called if player chooses blue button (player 2)
     */
    private void blueButtonClicked() {
        gameManager.setPlayerNumber(2);
        gameManager.setPlayerTurn(false);
        gameManager.setState(1);
        gameManager.update();
    }
}
