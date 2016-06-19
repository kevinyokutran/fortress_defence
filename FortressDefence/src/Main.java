import GameLogic.Board;
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
        boardUI.displayBoard();
        gameUI.printFortressHealth(health);
        gameUI.getUserCoordinates();
        gameUI.printDamageFromTanks(board.getDamageFromTanks());
        System.out.println();

        board.getCell(9, 9).setIsMissed();
        boardUI.displayBoardAfterGame();

    }

}
