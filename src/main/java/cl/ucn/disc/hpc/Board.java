package cl.ucn.disc.hpc;

public class Board {

    // The length of the board
    private final int size;
    // The initial board
    private int[][] initialBoard;
    // The board that will be modified while the sudoku is being solved
    private int[][] board;

    /**
     * The constructor
     *
     * @param initialBoard The original/initial board
     */
    public Board(int[][] initialBoard) {
        this.board = initialBoard;
        this.size = board.length;
    }

    /**
     * This function checks if a cell is empty
     *
     * @param row    The row of the cell
     * @param column The column of the cell
     * @return True if the cell is empty, false in the other case
     */
    public boolean isEmptyCell(int row, int column) {
        // If a position of the grid contains a 0, it means that is a empty cell
        if (this.board[row][column] == 0) {
            return true;
        }
        // The cell is empty
        return false;
    }

    /**
     * This function change the value of a cell given it position.
     *
     * @param row   The row from the board cell
     * @param col   The column from the board cell
     * @param value The value to insert
     */
    public void insert(int row, int col, int value) {
        // TODO: check if the value is valid in this same method?
        this.board[row][col] = value;
    }

    /**
     * This function makes the specific board position an empty cell
     *
     * @param row The row from the board cell
     * @param col The col from the board cell
     */
    public void delete(int row, int col) {
        this.board[row][col] = 0;
    }

    /**
     * This function reset the board to the initial board
     */
    public void rollback() {
        this.board = initialBoard;
    }


    /**
     * This function return the
     *
     * @param row       The row from the board cell
     * @param col       The column from the board cell
     * @param cellValue The value to insert
     * @return TRUE for a valid insertion, FALSE in the other case.
     */
    public boolean isValid(int row, int col, int cellValue) {
        // If any of the functions to check returns a true, it means that the insertion goes against the constraints
        // Constraint 1: A column can't contain the same value
        if (checkColumn(col, cellValue)) {
            return false;
        }
        // Constraint 2: A row can't contain the same value
        if (checkRow(row, cellValue)) {
            return false;
        }
        // Constrain 3: A subgrid can't contain the same value
        if (checkSubgrid(row, col, cellValue)) {
            return false;
        }
        // To this point, the insertion is valid
        return true;
    }

    /**
     * This function checks the column from a board to make sure that the value to put is a valid one.
     *
     * @param column    The column from the board cell
     * @param cellValue The value to insert
     * @return True if the value doesn't exist in the column, false in the other case
     */
    public boolean checkColumn(int column, int cellValue) {
        // Check the rows
        for (int row = 0; row < size; row++) {
            if (this.board[row][column] == cellValue) {
                return true;
            }
        }

        return false;
    }

    /**
     * This function checks the row from a board to make sure that the value to put is a valid one.
     *
     * @param row       The row from the board cell
     * @param cellValue The value to insert
     * @return True if the value doesn't exist in the row, false in the other case
     */
    public boolean checkRow(int row, int cellValue) {
        for (int col = 0; col < size; col++) {
            if (this.board[row][col] == cellValue) {
                return true;
            }
        }

        return false;
    }

    /**
     * This function checks the subgrid from a board to make sure that the value to put is a valid one.
     *
     * @param column    The column from the board cell
     * @param row       The row from the board cell
     * @param cellValue The number that will be insert in the cell
     * @return True if the value doesn't exist in the subgrid, false in the other case
     */
    public boolean checkSubgrid(int row, int column, int cellValue) {

        int subGridRow = row - row % 3;
        int subGridCol = column - column % 3;

        for (int i = subGridRow; i < subGridRow + 3; i++) {
            for (int j = subGridCol; j < subGridCol + 3; j++) {

                if (this.board[i][j] == cellValue) {
                    return true;
                }

            }
        }

        return false;
    }

    /**
     * This function prints the board given it a format
     */
    public void printBoard() {
        // TODO: Make more "readable" the format
        System.out.println("------------------");
        for (int[] row : this.board) {
            for (int col = 0; col < size; col++) {
                System.out.print(row[col] + " ");
            }

            System.out.print("\n");
        }

        System.out.println("------------------");
    }

}