package olech.marcin.model;



import java.util.Random;

public class PatternInitializer {
    private int rows = Model.BOARD_HEIGHT/Model.CELL_SIZE;
    private int cols = Model.BOARD_WIDTH/Model.CELL_SIZE;

    /**
     * Populates board with given Pattern value from Pattern enum.
     * Supports Pattern.Random, Pattern.QueenBeeShuttle, Pattern.Tumbler
     * @param pattern desired pattern from Pattern enum
     * @return boolean[][] array with desired pattern sequence in the middle,
     *         empty boolean[][] array for not supported patterns
     */
    public boolean[][] populate(Pattern pattern) {
        switch (pattern) {
            case Random: return populateRandomPattern();
            case QueenBeeShuttle: return populateQueenBeeShuttlePattern();
            case Tumbler: return populateTumblerPattern();
            default: return new boolean[rows][cols];
        }
    }

    private boolean[][] populateQueenBeeShuttlePattern() {
        boolean[][] queenBee = getQueenBeeShuttlePattern();
        boolean[][] board = new boolean[rows][cols];

        return insertPatternIntoBoard(board,queenBee);
    }

    private boolean[][] populateTumblerPattern() {
        boolean[][] tumbler = getTumblerPattern();
        boolean[][] board = new boolean[rows][cols];

        return insertPatternIntoBoard(board,tumbler);
    }

    private boolean[][] insertPatternIntoBoard(boolean[][] board, boolean[][] pattern) {
        int startY = (board.length - pattern.length)/2;
        int startX = (board[0].length - pattern[0].length)/2;
        int smallPatternY=0;
        int smallPatternX=0;

        for(int y=startY; y<startY + pattern.length; y++) {
            for (int x = startX; x < startX + pattern[0].length; x++) {
                board[y][x] = pattern[smallPatternY][smallPatternX];
                smallPatternX++;
            }
            smallPatternX=0;
            smallPatternY++;
        }
        return board;
    }

    private boolean[][] populateRandomPattern() {
        boolean[][] board = new boolean[rows][cols];
        Random random = new Random();
        for(int y=0; y<rows; y++) {
            for (int x=0;x<cols; x++) {
                board[y][x] = random.nextBoolean();
            }
        }
        return board;
    }

    private boolean[][] getQueenBeeShuttlePattern() {
       return new boolean[][]{
                {true, true, false, false},
                {false, false, true, false},
                {false, false, false, true},
                {false, false, false, true},
                {false, false, false, true},
                {false, false, true, false},
                {true, true, false, false}
        };
    }

    private boolean[][] getTumblerPattern() {
        return new boolean[][]{
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, false, true, false, true, false, false},
                {true, false, true, false, true, false, true},
                {true, false, true, false, true, false, true},
                {true, true, false, false, false, true, true},
        };
    }
}
