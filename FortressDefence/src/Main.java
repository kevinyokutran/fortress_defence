import GameLogic.Board;
import GameLogic.Tank;
import GameUI.BoardUI;
import GameUI.GameUI;
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
        Board board = new Board(ROW, COLUMN, NUMBER_OF_TANKS, NUMBER_OF_CELLS);
        BoardUI boardUI = new BoardUI(board);
        GameUI gameUI = new GameUI(board);
        while (!isGameOver(board)) {
            boardUI.displayBoard();
            gameUI.printFortressHealth(health);
            Map<String, Integer> coordinates = gameUI.getUserCoordinates(STARTING_ROW_VALUE, STARTING_COLUMN_VALUE);
            if (board.isSuccessfulHit(coordinates)) {
                board.setCellStatus(coordinates, true);
                gameUI.printMoveResults(true);
            } else {
                board.setCellStatus(coordinates, false);
                gameUI.printMoveResults(false);
            }
            for (Integer damage : board.getDamageFromTanks()) {
                health -= damage;
            }
            gameUI.printDamageFromTanks(board.getDamageFromTanks());
        }
        if (hasWon()) {
            gameUI.printWinMessage();
        }
        else {
            gameUI.printLoseMessage();
            boardUI.displayBoardAfterGame();
        }
    }

    private static boolean hasWon() {
        return health > 0;
    }

    private static boolean isGameOver(Board board) {
        boolean alive = health < 0;
        boolean tanksLeft = true;
        for (Tank tank : board.getTanks()) {
            if (tank.getNumberOfUndamagedCells() != 0) {
                tanksLeft = false;
            }
        }
        return alive || tanksLeft;
    }

}