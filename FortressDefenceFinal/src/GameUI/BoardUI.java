package GameUI;

import GameLogic.Board;
import GameLogic.Cell;
import GameLogic.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * BoardUI class displays a GUI to the user of the current game board depending
 * on the current state of the game. BoardUI can display the user's current knowledge of the board and
 * can reveal the entire board to the user.
 */

public class BoardUI {

	private static final int buttonXSize = 90;
	private static final int buttonYSize = 90;
	private static final char STARTING_ROW_LABEL = 'A';
	private static final int STARTING_COLUMN_LABEL = 1;
	private Board board;
	private static ImageIcon fieldIcon;
	private static ImageIcon hitIcon;
	private static ImageIcon missIcon;
	private static ImageIcon fogIcon;
	private static ImageIcon tankIcon;
	private JPanel playGrid;
	private JLabel hpLabel;
	private JLabel gameOutputLabel;
	private JButton[][] gridButtons;
	private JFrame gameFrame;
	private GameUI gameUI;
	private int health;

	public BoardUI(Board board, JFrame frame, int health) {
		this.board = board;
		this.gameFrame = frame;
		this.playGrid = new JPanel();
		playGrid.setLayout(new GridLayout(this.board.getNumberOfRows(), this.board.getNumberOfColumns()));
		this.gridButtons = new JButton[this.board.getNumberOfRows()][this.board.getNumberOfColumns()];
		this.fieldIcon = new ImageIcon(this.getClass().getResource("/resources/field.jpg"));
		this.hitIcon = new ImageIcon(this.getClass().getResource("/resources/hit.png"));
		this.missIcon = new ImageIcon(this.getClass().getResource("/resources/miss.png"));
		this.fogIcon = new ImageIcon(this.getClass().getResource("/resources/fog.png"));
		this.tankIcon = new ImageIcon(this.getClass().getResource("/resources/tank.png"));
		this.gameUI = new GameUI(this.board);
		this.health = health;
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

	public void createGameBoard(JFrame frame) {
		this.hpLabel = new JLabel();
		this.gameOutputLabel = new JLabel();
		JPanel panel = new JPanel();
		frame.add(this.hpLabel, BorderLayout.NORTH);
		frame.add(createBoardGridUI(this.health), BorderLayout.CENTER);
		frame.add(panel.add(this.gameOutputLabel), BorderLayout.SOUTH);
		setHPLabel(health);
		gameFrame.revalidate();
		gameFrame.pack();
	}

	public JPanel createBoardGridUI(int health) {
		this.health = health;
		for (int row = 0; row < this.board.getNumberOfRows(); row++) {
			for (int col = 0; col < this.board.getNumberOfColumns(); col++) {
				JButton fieldButton = new JButton();
				fieldButton.setIcon(getScaleImageIcon(this.fogIcon, this.buttonXSize, this.buttonYSize));
				fieldButton.setBorder(null);
				fieldButton.setContentAreaFilled(false);
				fieldButton.setOpaque(false);
				fieldButton.setBorderPainted(false);
				fieldButton.addActionListener(gridButtonListener(this.board, row, col));
				playGrid.add(fieldButton);
				this.gridButtons[row][col] = fieldButton;
			}
		}
		return playGrid;
	}

	private void setHPLabel(int health) {
		this.hpLabel.setText(this.gameUI.printFortressHealth(health));
	}

	private void setOutputLabel() {
		String output = "<html>";
		List<Integer> damageList = calculateDamageTaken(getDamageFromTanks(board));
		for (String line : gameUI.printDamageFromTanks(damageList)) {
			output = output + line + "<br>";
		}
		output += "</html>";
		this.gameOutputLabel.setText(output);
	}

	private ActionListener gridButtonListener(Board board, int row, int col) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, Integer> coordinates = new HashMap<>();
				coordinates.put("row", row);
				coordinates.put("column", col);
				boolean isSuccessfulHit = isSuccessfulHit(coordinates, board);
				setCellStatus(coordinates, board, isSuccessfulHit);
				setOutputLabel();
				if (isGameOver(board)) {
					changeIconsToEndGame();
					revalidateGameFrame();
					if (hasWon()) {
						JOptionPane.showMessageDialog(gameFrame, gameUI.printWinMessage());
					} else {
						JOptionPane.showMessageDialog(gameFrame, gameUI.printLoseMessage());
					}
					System.exit(0);
				}
				setHPLabel(health);
				gameFrame.revalidate();
				gameFrame.pack();
			}
		};
	}

	private void revalidateGameFrame() {
		gameFrame.revalidate();
		gameFrame.pack();
	}

	private List<Integer> calculateDamageTaken(java.util.List<Integer> getDamage) {
		for (Integer damage : getDamage) {
			this.health -= damage;
		}
		return getDamage;
	}

	private static List<Integer> getDamageFromTanks(Board board) {
		java.util.List<Integer> damageOfTanks = new ArrayList<>();
		for (Tank tank : board.getTanks()) {
			damageOfTanks.add(tank.currentDamage());
		}
		return damageOfTanks;
	}

	private boolean hasWon() {
		return this.health > 0;
	}

	private  boolean isGameOver(Board board) {
		boolean isDead = this.health <= 0;
		boolean areTanksRemaining = true;
		for (Tank tank : board.getTanks()) {
			if (tank.getNumberOfUndamagedCells() != 0) {
				areTanksRemaining = false;
			}
		}
		return isDead || areTanksRemaining;
	}

	private boolean isSuccessfulHit(Map<String, Integer> coordinates, Board board) {
		Cell cell = board.getCell(coordinates.get("row"), coordinates.get("column"));
		return cell.getIsTank() && !cell.getIsKnownToPlayer();
	}

	private void setCellStatus(Map<String, Integer> coordinates, Board board, boolean isHit) {
		Cell cell = board.getCell(coordinates.get("row"), coordinates.get("column"));
		if (isHit) {
			changeButtonIcon(this.hitIcon, coordinates.get("row"), coordinates.get("column"));
			cell.setIsTank();
			removeTankCell(coordinates, board);
		} else {
			cell.setIsMissed();
			changeButtonIcon(this.missIcon, coordinates.get("row"), coordinates.get("column"));
		}
		removeButtonActionListener(coordinates.get("row"), coordinates.get("column"));
		cell.setIsKnownToPlayer();
	}

	private void removeTankCell(Map<String, Integer> coordinates, Board board) {
		for (Tank tank : board.getTanks()) {
			for (Cell cell : tank.getCells()) {
				if (cell.getRow() == coordinates.get("row") && cell.getColumn() == coordinates.get("column")) {
					tank.decrementUndamagedCells();
				}
			}
		}
	}

	private void changeIconsToEndGame() {
		for (int row=0; row<this.board.getNumberOfRows(); row++) {
			for (int col=0; col<this.board.getNumberOfColumns(); col++) {
				System.out.println(col);
				if (!board.getCell(row, col).getIsKnownToPlayer()) {
					ImageIcon icon = this.fieldIcon;
					if (board.getCell(row, col).getIsTank()) {
						icon = this.tankIcon;
					}
					changeButtonIcon(icon, row, col);
				}
			}
		}
	}

	private void changeButtonIcon(ImageIcon icon, int row, int col) {
		JButton btn = this.gridButtons[row][col];
		btn.setIcon(getScaleImageIcon(icon, this.buttonXSize, this.buttonYSize));
	}

	private void removeButtonActionListener(int row, int col) {
		JButton btn = this.gridButtons[row][col];
		ActionListener[] actionListener = btn.getActionListeners();
		btn.removeActionListener(actionListener[0]);
	}

	static public ImageIcon getScaleImageIcon(ImageIcon icon, int width, int height) {
		return new ImageIcon(getScaledImage(icon.getImage(), width, height));
	}
	static private Image getScaledImage(Image srcImg, int width, int height){
		BufferedImage resizedImg =
				new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, width, height, null);
		g2.dispose();
		return resizedImg;
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
