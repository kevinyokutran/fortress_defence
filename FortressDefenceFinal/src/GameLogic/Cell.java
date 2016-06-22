package GameLogic;

/**
 * Cell class contains coordinates and states about what is currently inside. Each cell knows if
 * it contains a tank, if it is known to the player and if it is empty. The coordinates of each
 * cell is based on rows and columns.
 */

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

	public void setIsEmpty() {
		isTank = false;
		isKnownToPlayer = false;
		isMissed = false;
	}

}
