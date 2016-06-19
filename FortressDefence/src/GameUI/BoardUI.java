package GameUI;

import GameLogic.Board;

public class BoardUI {

    private Board board;

    public BoardUI(Board board) {
        this.board = board;
    }

    public void displayBoard() {
        for (int row = 0; row < board.getNumberOfRows(); row++) {
            for (int col = 0; col < board.getNumberOfColumns(); col++) {
                System.out.printf("%s ", board.showCurrentCellStatus(row, col));
            }
            System.out.println();
        }
    }

    public void displayBoardAfterGame() {
        for (int row = 0; row < board.getNumberOfRows(); row++) {
            for (int col = 0; col < board.getNumberOfColumns(); col++) {
                System.out.printf("%s ", board.showCellStatusAfterGame(row, col));
            }
            System.out.println();
        }
    }

}
