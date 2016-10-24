import GameLogic.Board;
import GameLogic.Cell;
import GameLogic.Tank;
import GameUI.BoardUI;
import GameUI.GameUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * Main class runs and performs calculations of the game Fortress Defence.
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
	private static final String frameTitle = "Tank Battlefield";
	private static int health = 1500;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Board board = new Board(ROW, COLUMN, NUMBER_OF_TANKS, NUMBER_OF_CELLS);
		BoardUI boardUI = new BoardUI(board, frame, health);
		GameUI gameUI = new GameUI(board);
		gameUI.printIntroduction();
		frame.setTitle(frameTitle);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		boardUI.createGameBoard(frame);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}