import GameLogic.Board;
import GameUI.BoardUI;

public class Main {

    private static final int ROW = 10;
    private static final int COLUMN = 10;
    private static final int NUMBER_OF_TANKS = 5;
    private static final int NUMBER_OF_CELLS = 4;

    public static void main(String[] args) {
        Board board = new Board(ROW, COLUMN, NUMBER_OF_TANKS, NUMBER_OF_CELLS);
        BoardUI ui = new BoardUI(board);
        ui.displayBoard();
        System.out.println();

        board.getCell(1, 2).setIsMissed();
        ui.displayBoardAfterGame();
    }

}
