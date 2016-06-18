package GameLogic;

import java.util.Random;

public class Board {

    private static final int DIRECTION = 4;
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;
    private static final int MINIMUM_INDEX = 0;

    private Cell[][] cells;
    private Tank[] tanks;
    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfTanks;
    private int numberOfTankParts;

    public Board(int numberOfRows, int numberOfColumns, int numberOfTanks, int numberOfTankParts) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.numberOfTanks = numberOfTanks;
        this.numberOfTankParts = numberOfTankParts;

        cells = new Cell[numberOfRows][numberOfColumns];
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }

        tanks = new Tank[numberOfTanks];
        for (int i = 0; i < numberOfTanks; i++) {
            tanks[i] = new Tank(numberOfTankParts);
        }
        generateTanks();
    }

    public void generateTanks() {
        for (Tank tank : tanks) {
            placeNextTankPiece(tank);
        }
    }

    private void placeNextTankPiece(Tank tank) {
        Random rand = new Random();

        int randRow = rand.nextInt(numberOfRows);
        int randCol = rand.nextInt(numberOfColumns);

        if (cells[randRow][randCol].getIsTank() == false) {
            tank.addPart(cells[randRow][randCol]);
            cells[randRow][randCol].setIsTank(true);

            while (tank.getParts().size() != tank.getNumberOfParts()) {
                int currentRow = tank.getLastPart().getRow();
                int currentCol = tank.getLastPart().getColumn();
                int direction = rand.nextInt(DIRECTION);

                switch (direction) {
                    case 0:
                        int north = currentRow - 1;
                        if (north >= 0 && cells[north][currentCol].getIsTank() == false) {
                            tank.addPart(cells[north][currentCol]);
                            cells[north][currentCol].setIsTank(true);
                        }
                        break;
                    case 1:
                        int east = currentCol + 1;
                        if (east < numberOfColumns && cells[currentRow][east].getIsTank() == false) {
                            tank.addPart(cells[currentRow][east]);
                            cells[currentRow][east].setIsTank(true);
                        }
                        break;
                    case 2:
                        int south = currentRow + 1;
                        if (south < numberOfRows && cells[south][currentCol].getIsTank() == false) {
                            tank.addPart(cells[south][currentCol]);
                            cells[south][currentCol].setIsTank(true);
                        }
                        break;
                    case 3:
                        int west = currentCol - 1;
                        if (west >= 0 && cells[currentRow][west].getIsTank() == false) {
                            tank.addPart(cells[currentRow][west]);
                            cells[currentRow][west].setIsTank(true);
                        }
                        break;
                }
            }
        } else {
            placeNextTankPiece(tank);
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

    public static void main(String[] args) {
        Board testBoard = new Board(10, 10, 4, 4);
        String placeholder = "_";
        for (int row = 0; row < testBoard.getNumberOfRows(); row++) {
            for (int col = 0; col < testBoard.getNumberOfColumns(); col++) {
                if (testBoard.getCell(row, col).getIsTank() == false) {
                    System.out.printf("%3d", row);
                } else {
                    System.out.printf("%3s", placeholder);
                }
            }
            System.out.println();
        }
    }
}
