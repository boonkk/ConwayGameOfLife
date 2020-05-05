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
        int m=0;
        int n=0;

        for(int i=startY; i<startY + pattern.length; i++) {
            for (int j = startX; j < startX + pattern[0].length; j++) {
                board[i][j] = pattern[m][n];
                n++;
            }
            n=0;
            m++;
        }
        return board;
    }

    private boolean[][] populateRandomPattern() {
        boolean[][] board = new boolean[rows][cols];
        Random random = new Random();
        for(int i=0; i<rows; i++) {
            for (int j=0;j<cols; j++) {
                board[i][j] = random.nextBoolean();
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
