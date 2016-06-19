package GameUI;

import GameLogic.Board;
import java.util.List;

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

    /* Todo */
    public void getUserCoordinates() {
        System.out.print("Enter your move: ");
        System.out.println();
    }
}
