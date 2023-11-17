package ui;

import constants.Constants;
import core.GameManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;

public class StartScreen {
    protected JFrame startScreen;
    protected GameManager gameManager;

    public StartScreen(JFrame startScreen, GameManager gameManager) {
        this.startScreen = startScreen;
        this.gameManager = gameManager;
    }

    public void createStartScreen() { //method to initialize the start screen
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

    private void redButtonClicked() { //if player chooses red button (player 1)
        gameManager.setPlayerNumber(1);
        gameManager.setPlayerTurn(true);
        gameManager.setState(1);
        gameManager.update();
    }

    private void blueButtonClicked() { //if player chooses blue button (player 2)
        gameManager.setPlayerNumber(2);
        gameManager.setPlayerTurn(false);
        gameManager.setState(1);
        gameManager.update();
    }
}
