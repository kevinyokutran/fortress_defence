package GameLogic;

import java.util.ArrayList;

public class Tank {

	private int parts;
	private ArrayList<String> occupiedCoordinates;

	Tank(int health, ArrayList<String> coordinates) {
		this.parts = health;
		this.occupiedCoordinates = coordinates;
	}

}
