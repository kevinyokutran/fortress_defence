// Generates required game objects and handles their interaction
public class Board {
	private Cell[][] cells;
	private Tank[] tanks;
	private Fortress fortress;
	private int rows;
	private int columns;
	private int numberTanks
	private int tankParts;
	private int fortressHealth

	public Board (int rows, int columns, int fortressHealth, int numberTanks) {

	}

	private void createTanks()  {

	}

	private void placeTank(Tank tank) {
		// Check if cell is tank
		// Place tank
	}

	private void checkNeighbourCell(Tank tank) {
		// Randomly places tank around tank cell and checks
	}

	public int hasWon {

	}



}
//
public class Tank {

	private int tankPartsDamaged
	private int numTankParts
	private int damageOf4Parts
	private int damageOf3Parts
	private int damageOf2Parts
	private int damageOf1Part
	private int damageOf0Parts

	public int currentDamage() {
		
	}

	public int fire (Fortress fortress) {

	}

	
}

// Contains information to store the state of objects in the game.
public class Cell {
	private boolean isTank;
	private boolean isTankHit;
	private boolean isHit;

	public boolean isTankHit
}

// Keeps track of its health points and fires at game cell
public class Fortress {
	private int healthPoints;

	public Fortress(int healthPoints) {

	}

	public int getHealthPoints() {

	}

	public int fireArtillery (Cell cell)
	
}