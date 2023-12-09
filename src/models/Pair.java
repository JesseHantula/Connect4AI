package models;

/*
Class that imitates a slot location for where a piece was placed
 */
public class Pair {
    public int row;
    public int column;

    /*
    Constructor takes in row number and column number (0, 0 is top left of the board)
     */
    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /*
    Gets row of a pair
     */
    public int getRow() {
        return row;
    }

    /*
    Gets column of a pair
     */
    public int getColumn() {
        return column;
    }

    /*
    Updates a pair
     */
    public void update(int newRow, int newCol) {
        row = newRow;
        column = newCol;
    }
}
