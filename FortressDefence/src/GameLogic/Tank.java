package GameLogic;

/**
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Tank {

    private int numberOfParts;
    private List<Cell> parts;

    public Tank(int numberOfParts) {
        this.numberOfParts = numberOfParts;
        parts = new ArrayList<Cell>();
    }

    public int getNumberOfParts() {
        return numberOfParts;
    }

    public List<Cell> getParts() {
        return parts;
    }

    public void addPart(Cell part) {
        parts.add(part);
    }

    public Cell getLastPart() {
        return getParts().get(getParts().size() - 1);
    }

}
