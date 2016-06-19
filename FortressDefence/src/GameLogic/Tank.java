package GameLogic;

/**
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Tank {

    private static final int DAMAGE_OF_4_CELLS = 20;
    private static final int DAMAGE_OF_3_CELLS = 5;
    private static final int DAMAGE_OF_2_CELLS = 2;
    private static final int DAMAGE_OF_1_CELLS = 1;
    private static final int DAMAGE_OF_0_CELLS = 0;

    private int numberOfCells;
    private List<Cell> cells;
    private int numberOfUndamagedCells;

    public Tank(int numberOfCells) {
        this.numberOfCells = numberOfCells;
        numberOfUndamagedCells = numberOfCells;
        cells = new ArrayList<>();
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public Cell getLastCellPlaced() {
        return getCells().get(getCells().size() - 1);
    }

    public int getNumberOfUndamagedCells() {
        return numberOfUndamagedCells;
    }

    public void decrementUndamagedCells() {
        numberOfUndamagedCells--;
    }

    public int currentDamage() {
        int damage = 0;
        switch (numberOfUndamagedCells) {
            case 4:
                damage = DAMAGE_OF_4_CELLS;
                break;
            case 3:
                damage = DAMAGE_OF_3_CELLS;
                break;
            case 2:
                damage = DAMAGE_OF_2_CELLS;
                break;
            case 1:
                damage = DAMAGE_OF_1_CELLS;
                break;
            case 0:
                damage = DAMAGE_OF_0_CELLS;
                break;
        }

        return damage;
    }

}
