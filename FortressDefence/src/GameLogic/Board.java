package GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private enum DIRECTION {
        NORTH, EAST, SOUTH, WEST;

        private static final DIRECTION[] DIRECTIONS = values();
        private static final int NUMBER_OF_DIRECTIONS = DIRECTIONS.length;
        private static final Random RANDOM = new Random();

        public static DIRECTION getRandomDirection() {
            return DIRECTIONS[RANDOM.nextInt(NUMBER_OF_DIRECTIONS)];
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

        if (!getCell(randRow, randCol).getIsTank()) {
            tank.addCell(getCell(randRow, randCol));
            getCell(randRow, randCol).setIsTank();

            while (tank.getCells().size() != tank.getNumberOfCells()) {
                int currentRow = tank.getLastCellPlaced().getRow();
                int currentCol = tank.getLastCellPlaced().getColumn();

                switch (DIRECTION.getRandomDirection()) {
                    case NORTH:
                        int north = currentRow - 1;
                        if (north >= MINIMUM_CELL_INDEX && !getCell(north, currentCol).getIsTank()) {
                            tank.addCell(getCell(north, currentCol));
                            getCell(north, currentCol).setIsTank();
                        }
                        break;
                    case EAST:
                        int east = currentCol + 1;
                        if (east < numberOfColumns && !getCell(currentRow, east).getIsTank()) {
                            tank.addCell(getCell(currentRow, east));
                            getCell(currentRow, east).setIsTank();
                        }
                        break;
                    case SOUTH:
                        int south = currentRow + 1;
                        if (south < numberOfRows && !getCell(south, currentCol).getIsTank()) {
                            tank.addCell(getCell(south, currentCol));
                            getCell(south, currentCol).setIsTank();
                        }
                        break;
                    case WEST:
                        int west = currentCol - 1;
                        if (west >= MINIMUM_CELL_INDEX && !getCell(currentRow, west).getIsTank()) {
                            tank.addCell(getCell(currentRow, west));
                            getCell(currentRow, west).setIsTank();
                        }
                        break;
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
