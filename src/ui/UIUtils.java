package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

public class UIUtils {

    public UIUtils() {

    }

    public static JButton createButton(String text, Color color, Integer width, Integer height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(color);
        button.setForeground(color);
        return button;
    }

    public static void resetScreen(JFrame screen) {
        screen.getContentPane().removeAll();
        screen.revalidate();
        screen.repaint();
    }
}
