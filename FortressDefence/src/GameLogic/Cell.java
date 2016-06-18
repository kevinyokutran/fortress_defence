package GameLogic;

public class Cell {

    private boolean isTank;
    private boolean isKnownToPlayer;
    private int row;
    private int column;
    private String coordinate;


    public Cell(int row, int col) {
        this.row = row;
        this.column = col;
        isTank = false;
        isKnownToPlayer = false;
    }

    public void setRow (int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void getColumn (int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public void setIsTank (boolean isTank) {
        this.isTank = isTank;
    }

    public boolean getIsTank() {
        return isTank;
    }

    public void setIsKnownToPlayer(boolean isKnownToPlayer) {
        this.isKnownToPlayer = isKnownToPlayer;
    }

    public boolean getIsKnownToPlayer() {
        return isKnownToPlayer;
    }

    public String setCoordinate(String coordinate) {
        return "a";
    }
}
