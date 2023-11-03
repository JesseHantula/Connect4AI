package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomBlueButton extends JButton {
    private boolean circlePainted = false;

    public CustomBlueButton() {
        super();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circlePainted = true;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (circlePainted) {
            g.setColor(Color.BLUE);
            int diameter = Math.min(getWidth(), getHeight());
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;
            g.fillOval(x, y, diameter, diameter);
        }
    }
}



