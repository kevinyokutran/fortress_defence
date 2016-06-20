import GameLogic.Board;
import GameLogic.Tank;
import GameUI.BoardUI;
import GameUI.GameUI;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final int ROW = 10;
    private static final int COLUMN = 10;
    private static final int NUMBER_OF_TANKS = 5;
    private static final int NUMBER_OF_CELLS = 4;
    private static char STARTING_ROW_VALUE = 'A';
    private static int STARTING_COLUMN_VALUE = 0;

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
                Map<String,Integer> coordinates = gameUI.getUserCoordinates(STARTING_ROW_VALUE, STARTING_COLUMN_VALUE);
                if (board.isSuccessfulHit(coordinates)) {
                    board.setCellStatus(coordinates, true);
                    gameUI.printMoveResults(true);
                }
                else {
                    board.setCellStatus(coordinates, false);
                    gameUI.printMoveResults(false);
                }
                gameUI.printDamageFromTanks(board.getDamageFromTanks());
            } else {
                isGameOver = true; // Stops infinite loop until coordinates.
                boardUI.displayBoardAfterGame();
            }
        }
        //boardUI.displayBoardAfterGame();

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
