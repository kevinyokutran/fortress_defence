package GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board {

    private enum DIRECTION {
        NORTH, EAST, SOUTH, WEST;

        private static final DIRECTION[] DIRECTIONS = values();
        private static final int NUMBER_OF_DIRECTIONS = DIRECTIONS.length;
        private static final Random RAND = new Random();

        public static DIRECTION getRandomDirection() {
            return DIRECTIONS[RAND.nextInt(NUMBER_OF_DIRECTIONS)];
        }
    }

    public static final int MINIMUM_CELL_INDEX = 0;
    private static final String UNKNOWN_TO_PLAYER = "~";
    private static final String PLAYER_HAS_MISSED = ".";
    private static final String PLAYER_HAS_HIT_TANK = "X";
    private static final String EMPTY_CELL = " ";

    private Cell[][] cells;
    private Tank[] tanks;
    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfTanks;
    private int numberOfTankCells;

    public Board(int numberOfRows, int numberOfColumns, int numberOfTanks, int numberOfTankCells) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.numberOfTanks = numberOfTanks;
        this.numberOfTankCells = numberOfTankCells;

        cells = new Cell[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }

        tanks = new Tank[numberOfTanks];
        for (int i = 0; i < numberOfTanks; i++) {
            tanks[i] = new Tank(numberOfTankCells);
        }
        generateTanks();
    }

    private void generateTanks() {
        for (Tank tank : tanks) {
            placeNextTankCell(tank);
        }
    }

    private void placeNextTankCell(Tank tank) {
        Random rand = new Random();

        int randRow = rand.nextInt(numberOfRows);
        int randCol = rand.nextInt(numberOfColumns);
        int numberOfTries = 0;

        if (!getCell(randRow, randCol).getIsTank()) {
            tank.addCell(getCell(randRow, randCol));
            getCell(randRow, randCol).setIsTank();

            while (tank.getCells().size() != tank.getNumberOfCells()) {
                int currentRow = tank.getLastCellPlaced().getRow();
                int currentCol = tank.getLastCellPlaced().getColumn();
                numberOfTries++;

                if (numberOfTries > 10) {
                    while (tank.getCells().size() > 0) {
                        tank.getLastCellPlaced().setIsEmtpy();
                        tank.getCells().remove(tank.getLastCellPlaced());
                    }
                    placeNextTankCell(tank);
                }

                switch (DIRECTION.getRandomDirection()) {
                    case NORTH:
                        int northFromCurrentRow = currentRow - 1;
                        if (northFromCurrentRow >= MINIMUM_CELL_INDEX && !getCell(northFromCurrentRow, currentCol).getIsTank()) {
                            tank.addCell(getCell(northFromCurrentRow, currentCol));
                            getCell(northFromCurrentRow, currentCol).setIsTank();
                        }
                        break;
                    case EAST:
                        int eastFromCurrentCol = currentCol + 1;
                        if (eastFromCurrentCol < numberOfColumns && !getCell(currentRow, eastFromCurrentCol).getIsTank()) {
                            tank.addCell(getCell(currentRow, eastFromCurrentCol));
                            getCell(currentRow, eastFromCurrentCol).setIsTank();
                        }
                        break;
                    case SOUTH:
                        int southFromCurrentRow = currentRow + 1;
                        if (southFromCurrentRow < numberOfRows && !getCell(southFromCurrentRow, currentCol).getIsTank()) {
                            tank.addCell(getCell(southFromCurrentRow, currentCol));
                            getCell(southFromCurrentRow, currentCol).setIsTank();
                        }
                        break;
                    case WEST:
                        int westFromCurrentCol = currentCol - 1;
                        if (westFromCurrentCol >= MINIMUM_CELL_INDEX && !getCell(currentRow, westFromCurrentCol).getIsTank()) {
                            tank.addCell(getCell(currentRow, westFromCurrentCol));
                            getCell(currentRow, westFromCurrentCol).setIsTank();
                        }
                        break;
                    default: {
                        assert false;
                        break;
                    }
                }
            }
        } else {
            placeNextTankCell(tank);
        }
    }

    public List<Integer> getDamageFromTanks() {
        List<Integer> damageOfTanks = new ArrayList<>();
        for (Tank tank : tanks) {
            damageOfTanks.add(tank.currentDamage());
        }
        return damageOfTanks;
    }

    public String showCurrentCellStatus(int row, int col) {
        String status = UNKNOWN_TO_PLAYER;
        if (getCell(row, col).getIsKnownToPlayer()) {
            if (getCell(row, col).getIsTank()) {
                status = PLAYER_HAS_HIT_TANK;
            } else {
                status = PLAYER_HAS_MISSED;
            }
        }
        return status;
    }

    public String showCellStatusAfterGame(int row, int col) {
        String status = EMPTY_CELL;
            if (getCell(row, col).getIsMissed()) {
                status = PLAYER_HAS_MISSED;
            } else if (getCell(row, col).getIsTank()){
                status = PLAYER_HAS_HIT_TANK;
        }
        return status;
    }

    public boolean isSuccessfulHit(Map<String, Integer> coordinates) {
        Cell cell = getCell(coordinates.get("row"), coordinates.get("column"));
        return cell.getIsTank() && !cell.getIsKnownToPlayer();
    }

    public void setCellStatus(Map<String, Integer> coordinates, boolean hit) {
        Cell cell = getCell(coordinates.get("row"), coordinates.get("column"));
        if (hit) {
            cell.setIsTank();
            removeTankPart(coordinates);
        }
        else {
            cell.setIsMissed();
            System.out.println(tanks[0].getCells());
        }
        cell.setIsKnownToPlayer();
    }

    private void removeTankPart(Map<String, Integer> coordinates) {
        for (int i=0; i<tanks.length; i++) {
            List<Cell> cells = tanks[i].getCells();
            for (int j=0; j<cells.size(); j++) {
                if (cells.get(j).getRow() == coordinates.get("row")
                    && cells.get(j).getColumn()==coordinates.get("column")) {
                    tanks[i].decrementUndamagedCells();
                    cells.remove(j);
                }
            }


        }
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public Tank[] getTanks() {
        return tanks;
    }

}
