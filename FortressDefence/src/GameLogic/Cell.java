package GameLogic;

public class Cell {

    private boolean isTank;
    private boolean isKnownToPlayer;
    private boolean isMissed;
    private int row;
    private int column;


    public Cell(int row, int col) {
        this.row = row;
        this.column = col;
        isTank = false;
        isKnownToPlayer = false;
        isMissed = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setIsTank () {
        isTank = true;
    }

    public boolean getIsTank() {
        return isTank;
    }

    public void setIsKnownToPlayer() {
        isKnownToPlayer = true;
    }

    public boolean getIsKnownToPlayer() {
        return isKnownToPlayer;
    }

    public void setIsMissed() {
        isMissed = true;
    }

    public boolean getIsMissed() {
        return isMissed;
    }

    public void setIsEmtpy() {
        isTank = false;
        isKnownToPlayer = false;
        isMissed = false;
    }

}
