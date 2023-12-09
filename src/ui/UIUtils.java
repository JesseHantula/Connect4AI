package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

/*
Class that has useful static methods for the UIs
 */
public class UIUtils {

    public UIUtils() {

    }

    /*
    Class that creates a button
    @param text The words that the button will hold
    @param color The color of the button
    @param width The width of the button
    @param height The height of the button
     */
    public static JButton createButton(String text, Color color, Integer width, Integer height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(color);
        button.setForeground(color);
        return button;
    }

    /*
    Resets a JFrame
    @param screen The JFrame that will be reset
     */
    public static void resetScreen(JFrame screen) {
        screen.getContentPane().removeAll();
        screen.revalidate();
        screen.repaint();
    }
}
