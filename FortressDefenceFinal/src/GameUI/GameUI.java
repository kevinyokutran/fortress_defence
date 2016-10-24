package GameUI;

import GameLogic.Board;

import javax.swing.*;
import java.util.*;

/**
 * GameUI class displays a text interface to the user of information and states about the game.
 * GameUI will notify the user to enter a move as well as the game's actions. If the user's move
 * is not valid, GameUI will prompt the user to enter a valid move.
 */

public class GameUI {

	private Board board;

	public GameUI(Board board) {
		this.board = board;
	}

	public void printIntroduction() {
		System.out.println("---------------------------------");
		System.out.println("Welcome to Fortress Defense");
		System.out.println("By Josh Vocal and Kevin Tran");
		System.out.println("---------------------------------");
		System.out.println();
	}

	public List<String> printDamageFromTanks(List<Integer> damageOfTanks) {
		List<String> output = new ArrayList<>();
		for (Integer damage : damageOfTanks) {
			if (damage > 0) {
				output.add(String.format("You were shot for %d%n",damage));
			}
		}
		return output;
	}

	public String printFortressHealth(int health) {
		return "Fortress Health: " + health;
	}

	public String printWinMessage() {
		return "Congratulations! You won!";
	}

	public String printLoseMessage() {
		return "I'm sorry, your fortress has been smashed!";
	}

	public void printMoveResults(boolean isHit) {
		if (isHit) {
			System.out.println("HIT!");
		} else {
			System.out.println("Miss.");
		}
	}

	public Map<String, Integer> getUserCoordinates() {
		Map<String, Integer> coordinates = new HashMap<>();
		char startingRowLetter = 'A';
		int startingColNum = 0;

		while (true) {

			Scanner scanner = new Scanner(System.in);
			String coordinate = "";
			System.out.print("Enter your move: ");

			try {
				coordinate = scanner.nextLine().toUpperCase();
				int row = coordinate.charAt(0);
				int column = Integer.parseInt(coordinate.substring(1));
				if (row < startingRowLetter
						|| row > startingRowLetter + board.getNumberOfRows()
						|| column <= startingColNum
						|| column > startingColNum + board.getNumberOfColumns()) {
					throw new Exception();
				}

				coordinates.put("row", row - startingRowLetter);
				coordinates.put("column", column - 1);

				return coordinates;

			} catch (Exception e) {
				System.out.printf("Invalid coordinate: %s%nPlease try again%n", coordinate);
			}
		}
	}

}
