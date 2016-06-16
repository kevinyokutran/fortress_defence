package GameLogic;

import java.util.ArrayList;
import java.util.Random;

public class Board {

	private static final int MAX_ROW_SIZE = 26;
	private static final int MAX_COL_SIZE = 26;
	private static final int VALUE_OF_LETTER_A = 65;
	private static final String[] tetrominoes = {"S", "Z", "T", "L", "Line", "MirroredL","Square"};
	private ArrayList<ArrayList<Cell>> board;
	private ArrayList<Tank> tanks;
	private int length;
	private int width;

	public Board(int length, int width) {
		this.length = length;
		this.width = width;
		this.board = generateBoard(length, width);
	}

	public boolean isSuccessfulHit(String coordinate) {
		return getCellFromBoard(coordinate).isTank();
	}

	/* TODO */
	public int damageTankCell(String coordinates) {
		return 1;
	}

	/* TODO */
	public int getDamageFromTanks() {
		return 1;
	}

	private ArrayList<ArrayList<Cell>> generateBoard(int length, int width) {
		char rowLetter = 'A';
		ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();
		for (int i=0; i<length; i++) {
			ArrayList<Cell> row = new ArrayList<Cell>();
			for (int j=1; j<width+1; j++) {
				row.add(new Cell(String.valueOf(rowLetter)+j, false, false));
			}
			board.add(row);
			rowLetter++;
		}
		return board;
	}

	/* TODO */
	private void generateTanksOnBoard(Board board, int numberOfTanks) {
		for (int i=0; i<numberOfTanks; i++) {
			String topLeftCoordinate = getRandomValidCoordinate();
			String shape = getRandomShape(); // Apply rotation to shape?
			if (isValidPositionForTank(topLeftCoordinate, shape)) {
				// Create tank then place on board
			}
		}
	}

	private String getRandomValidCoordinate() {
		Random rand = new Random();
		int letter = VALUE_OF_LETTER_A+rand.nextInt(this.length);
		String row = String.valueOf((char)letter);
		String col = String.valueOf(rand.nextInt(this.width)+1);
		return row+col;
	}

	private String getRandomShape() {
		int position = new Random().nextInt(this.tetrominoes.length);
		return this.tetrominoes[position];
	}

	/* TODO: Check whether tetris piece can be placed. Not sure how to handle rotations of pieces */
	private Boolean isValidPositionForTank(String topLeftCoordinate, String shape) {
		switch(shape) {
			case "S": break;
			case "Z": break;
			case "T": break;
			case "L": break;
			case "Line": break;
			case "MirroredL": break;
			case "Square": break;
			default: break;
		}
		return true; // take this out afterwards
	}

	public ArrayList<ArrayList<Cell>> getBoard() {
		return this.board;
	}

	public Cell getCellFromBoard(String coordinate){
		for ( ArrayList<Cell> row : this.board) {
			for (Cell cell : row) {
				if (cell.getCoordinate().equals(coordinate)) {
					return cell;
				}
			}
			System.out.println();
		}
		return new Cell("Invalid", false, false); // Should not reach this line
	}

	/* Used for testing */
	private void printCoordinatesInBoard() {
		for ( ArrayList<Cell> row : this.board) {
			for (Cell cell : row) {
				System.out.printf("%5s", cell.getCoordinate());
			}
			System.out.println();
		}
	}

	/* Used for testing */
	public static void main(String[] args) {
		Board testBoard = new Board(5,5);
		System.out.println("Random Position: " + testBoard.getRandomValidCoordinate());
		testBoard.printCoordinatesInBoard();
	}
}
