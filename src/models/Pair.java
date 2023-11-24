package models;

public class Pair {
    //Used to imitate a slot location for where a piece was placed
    public int row;
    public int column;

    public Pair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void update(int newRow, int newCol) {
        row = newRow;
        column = newCol;
    }
}
