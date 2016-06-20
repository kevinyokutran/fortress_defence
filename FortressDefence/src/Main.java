import GameLogic.Board;
import GameLogic.Tank;
import GameUI.BoardUI;
import GameUI.GameUI;

public class Main {

    private static final int ROW = 10;
    private static final int COLUMN = 10;
    private static final int NUMBER_OF_TANKS = 5;
    private static final int NUMBER_OF_CELLS = 4;

    private static int health = 1500;

    public static void main(String[] args) {
        // Testing functions here for game.
        Board board = new Board(ROW, COLUMN, NUMBER_OF_TANKS, NUMBER_OF_CELLS);
        BoardUI boardUI = new BoardUI(board);
        GameUI gameUI = new GameUI(board);
        boolean isGameOver = false;

        while (!isGameOver) {

            boardUI.displayBoard();
            gameUI.printFortressHealth(health);

            if (!hasWon(board)) {
                gameUI.getUserCoordinates();
                gameUI.printDamageFromTanks(board.getDamageFromTanks());
                isGameOver = true; // Stops infinite loop until coordinates.
            } else {
                isGameOver = true;
                boardUI.displayBoardAfterGame();
            }
        }
        boardUI.displayBoardAfterGame();

    }

    private static boolean hasWon(Board board) {
        if (health <= 0) {
            return false;
        } else {
            for (Tank tank : board.getTanks()) {
                if (tank.getNumberOfUndamagedCells() != 0) {
                    return false;
                }
            }
        }

        return true;
    }

}