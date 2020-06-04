package olech.marcin.model;

public class Model {
    public static final int BOARD_HEIGHT = 500;
    public static final int BOARD_WIDTH = 500;
    public static final int CELL_SIZE = 10;

    private PatternInitializer patternInitializer = new PatternInitializer();

    private final int rows;
    private final int cols;
    private boolean[][] cellArray;
    private Pattern[] patterns = Pattern.values();
    private int currentPatternID = -1;


    /**
     * Constructor which initializes board dimensions using Model class constants and creates empty board
     */
    public Model() {
        this.rows = (int) Math.floor(BOARD_HEIGHT / CELL_SIZE);
        this.cols = (int) Math.floor(BOARD_WIDTH / CELL_SIZE);
        cellArray = new boolean[rows][cols];
    }

    /**
     * Clears, initializes and draws board (with pattern from Pattern enum)
     */
    public void nextPattern() {
        cellArray = new boolean[rows][cols];
        updateCurrentPatternID();
        cellArray = patternInitializer.populate(patterns[currentPatternID]);
    }

    private void updateCurrentPatternID() {
        if( ++currentPatternID >= patterns.length )
            currentPatternID = 0;
    }

    /**
     * Proceeds to the next generation (a tick).
     * Checking game requirements:
     * 1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * 2. Any live cell with two or three live neighbors lives on to the next generation.
     * 3. Any live cell with more than three live neighbors dies, as if by overcrowding.
     * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * Updates and draws board.
     */
    public void tick() {
        boolean[][] next = new boolean[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int neighbors = countAliveNeighbors(y, x);
                if( neighbors == 3 ) {
                    next[y][x] = true;
                } else if( neighbors < 2 || neighbors > 3 ) {
                    next[y][x] = false;
                } else {
                    next[y][x] = cellArray[y][x];
                }
            }
        }
        cellArray = next;
    }

    private int countAliveNeighbors(int cellY, int cellX) {
        int alives = 0;
        int startY = cellY == 0 ? 0 : -1;
        int endY = cellY == cellArray.length - 1 ? 0 : 1;
        int startX = cellX == 0 ? 0 : -1;
        int endX = cellX == cellArray[0].length - 1 ? 0 : 1;

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                alives += cellArray[cellY + y][x + cellX] ? 1 : 0;
            }
        }

        alives -= cellArray[cellY][cellX] ? 1 : 0;
        return alives;
    }

    public Pattern getPattern() {
        return patterns[currentPatternID];
    }

    public boolean[][] getCellArray() {
        return cellArray;
    }
}
