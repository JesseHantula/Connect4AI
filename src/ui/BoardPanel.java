package ui;

import javax.swing.JPanel;
import java.awt.Graphics;

public class BoardPanel extends JPanel {
    private int[][] gameBoard;
    private int numRows;
    private int numCols;
    private int cellSize;

    public BoardPanel(int numRows, int numCols, int cellSize) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.cellSize = cellSize;
        this.gameBoard = new int[numRows][numCols];
    }

    public void paint(Graphics g) {
        super.paintComponent(g);


    }
}
