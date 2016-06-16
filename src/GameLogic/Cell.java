package GameLogic;

public class Cell {

	private String coordinate;
	private boolean isTankCell;
	private boolean isKnownToPlayer;

	public Cell(String coordinate, boolean isTankCell, boolean isKnownToPlayer) {
		this.coordinate = coordinate;
		this.isTankCell = isTankCell;
		this.isKnownToPlayer = isKnownToPlayer;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public boolean isTank() {
		return isTankCell;
	}

}
