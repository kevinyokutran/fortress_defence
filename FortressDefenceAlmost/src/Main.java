import GameLogic.Board;
import GameLogic.Cell;
import GameLogic.Tank;
import GameUI.BoardUI;
import GameUI.GameUI;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * Main class displays a runs and performs calculations of the game Fortress Defence.
 * Each turn, the user will be prompt to enter a coordinate to fire at a tank and tanks will fire at the user,
 * reducing their health. If the coordinate is invalid, they will be notified to enter a valid coordinate.
 * Fortress Defence is won if the user's health is greater than 0 and they have destroyed all the tanks.
 * If the user fails to destroy all the tanks and their health is less than 0, they lose. After the game,
 * the board will be revealed.
 */

public class Main {

	private static final int ROW = 10;
	private static final int COLUMN = 10;
	private static final int NUMBER_OF_TANKS = 5;
	private static final int NUMBER_OF_CELLS = 4;

	private static int health = 1500;

	public static void main(String[] args) {

		Board board = new Board(ROW, COLUMN, NUMBER_OF_TANKS, NUMBER_OF_CELLS);
		BoardUI boardUI = new BoardUI(board);
		GameUI gameUI = new GameUI(board);

		gameUI.printIntroduction();

		while (!isGameOver(board)) {

			boardUI.displayBoard();
			gameUI.printFortressHealth(health);
			Map<String, Integer> coordinates = gameUI.getUserCoordinates();
			boolean isSuccessfulHit = isSuccessfulHit(coordinates, board);

			setCellStatus(coordinates, board, isSuccessfulHit);
			gameUI.printMoveResults(isSuccessfulHit);

			gameUI.printDamageFromTanks(calculateDamageTaken(getDamageFromTanks(board)));

		}

		if (hasWon()) {
			gameUI.printWinMessage();
		} else {
			gameUI.printLoseMessage();
			boardUI.displayBoardAfterGame();
		}
	}

	private static boolean hasWon() {
		return health > 0;
	}

	private static boolean isGameOver(Board board) {
		boolean isDead = health <= 0;
		boolean areTanksRemaining = true;
		for (Tank tank : board.getTanks()) {
			if (tank.getNumberOfUndamagedCells() != 0) {
				areTanksRemaining = false;
			}
		}
		return isDead || areTanksRemaining;
	}

	private static List<Integer> calculateDamageTaken(List<Integer> getDamage) {
		for (Integer damage : getDamage) {
			health -= damage;
		}
		return getDamage;
	}

	private static List<Integer> getDamageFromTanks(Board board) {
		List<Integer> damageOfTanks = new ArrayList<>();
		for (Tank tank : board.getTanks()) {
			damageOfTanks.add(tank.currentDamage());
		}
		return damageOfTanks;
	}

	private static boolean isSuccessfulHit(Map<String, Integer> coordinates, Board board) {
		Cell cell = board.getCell(coordinates.get("row"), coordinates.get("column"));
		return cell.getIsTank() && !cell.getIsKnownToPlayer();
	}

	private static void setCellStatus(Map<String, Integer> coordinates, Board board, boolean isHit) {
		Cell cell = board.getCell(coordinates.get("row"), coordinates.get("column"));
		if (isHit) {
			cell.setIsTank();
			removeTankCell(coordinates, board);
		} else {
			cell.setIsMissed();
			//System.out.println(tanks[0].getCells());
		}
		cell.setIsKnownToPlayer();
	}

	private static void removeTankCell(Map<String, Integer> coordinates, Board board) {
//        for (int i = 0; i < tanks.length; i++) {
//            List<Cell> cells = tanks[i].getCells();
//            for (int j = 0; j < cells.size(); j++) {
//                if (cells.get(j).getRow() == coordinates.get("row")
//                    && cells.get(j).getColumn() == coordinates.get("column")) {
//                    tanks[i].decrementUndamagedCells();
//                    cells.remove(j);
//                }
//            }
//        }

		for (Tank tank : board.getTanks()) {
			for (Cell cell : tank.getCells()) {
				if (cell.getRow() == coordinates.get("row") && cell.getColumn() == coordinates.get("column")) {
					tank.decrementUndamagedCells();
				}
			}
		}
	}

}