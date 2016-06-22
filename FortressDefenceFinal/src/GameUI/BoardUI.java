package GameUI;

import GameLogic.Board;

/**
 * BoardUI class displays a text interface to the user of the current game board depending
 * on the current state of the game. BoardUI can display the user's current knowledge of the board and
 * can reveal the entire board to the user.
 */

public class BoardUI {

	private static char STARTING_ROW_LABEL = 'A';
	private static int STARTING_COLUMN_LABEL = 1;
	private Board board;

	public BoardUI(Board board) {
		this.board = board;
	}

	public void displayBoard() {
		displayBoardColumnLabels();
		// Prints the row labels + the cell.
		char rowLabel = STARTING_ROW_LABEL;
		for (int row = 0; row < board.getNumberOfRows(); row++) {
			System.out.printf("%-3c", rowLabel++ );
			for (int col = 0; col < board.getNumberOfColumns(); col++) {
				System.out.printf("%-3s", board.showCurrentCellStatus(row, col));
			}
			System.out.println();
		}
	}

	public void displayBoardAfterGame() {
		displayBoardColumnLabels();
		// Prints the row labels + the cell.
		char rowLabel = STARTING_ROW_LABEL;
		for (int row = 0; row < board.getNumberOfRows(); row++) {
			System.out.printf("%-3c", rowLabel++ );
			for (int col = 0; col < board.getNumberOfColumns(); col++) {
				System.out.printf("%-3s", board.showCellStatusAfterGame(row, col));
			}
			System.out.println();
		}
	}

	private void displayBoardColumnLabels() {
		System.out.println("Game Board:");
		// Prints the column labels first.
		System.out.printf("%-3s", "");
		for (int col = STARTING_COLUMN_LABEL; col <= board.getNumberOfColumns(); col++) {
			System.out.printf("%-3d", col);
		}
		System.out.println();
	}

}
