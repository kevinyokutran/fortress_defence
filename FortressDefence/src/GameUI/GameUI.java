package GameUI;

import GameLogic.Board;
import java.util.List;
import java.util.Scanner;

public class GameUI {

    private Board board;

    public GameUI(Board board) {
        this.board = board;
    }

    public void printDamageFromTanks(List<Integer> damageOfTanks) {
        for (Integer damage : damageOfTanks) {
            if (damage > 0) {
                System.out.printf("You were shot for %d!%n", damage);
            }
        }
    }

    public void printFortressHealth(int health) {
        System.out.printf("Fortress Structure Left: %d.%n", health);
    }

    public void getUserCoordinates(char startingRowLetter, int startingColNum) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String coordinate = "";
            System.out.print("Enter your move: ");
            try {
                coordinate = scanner.next().toUpperCase();
                int row = coordinate.charAt(0);
                int column = Integer.parseInt(coordinate.substring(1));
                if (row < startingRowLetter
                        || row > startingRowLetter + board.getNumberOfRows()
                        || column < startingColNum
                        || column > startingColNum + board.getNumberOfColumns()) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.printf("Invalid coordinate: %s%nPlease try again%n", coordinate);
            }
        }
    }

    /* Testing */
    public static void main(String[] args) {
        // Testing functions here for game.
        Board board = new Board(10, 10, 5, 4);
        GameUI ui = new GameUI(board);
        ui.getUserCoordinates('A',0);
    }
}
