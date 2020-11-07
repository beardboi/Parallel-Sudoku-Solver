package cl.ucn.disc.hpc;

public class ParallelSudoku {

    // The board (final?)
    public Board board;

    /**
     * The constructor
     *
     * @param board The sudoku board
     */
    public ParallelSudoku(Board board) {
        this.board = board;
    }

    /*
     *
     */
    public boolean solve() {
        // TODO: The program should be equally working with sudoku boards bigger than 9x9
        return solveRows(0, 8);
    }

    /**
     * This function checks for every value
     *
     * @param initialRow The initial row to check
     * @param finalRow   The final row to check
     * @return True if the board is solved
     */
    public boolean solveRows(int initialRow, int finalRow) {
        int row = -1;
        int col = -1;
        boolean solved = true;

        for (int r = initialRow; r <= finalRow; r++) {
            for (int c = 0; c <= 8; c++) {
                if (board.isEmptyCell(r, c)) {
                    row = r;
                    col = c;
                    solved = false;
                    break;
                }
            }

            if (!solved) {
                break;
            }

        }
        // 1 If the board is solved
        if (solved) {
            return true;
        }

        // Check from 1 to 9 if value satisfy the constraint
        for (int value = 1; value <= 9; value++) {
            if (board.isValid(row, col, value)) {
                // Insert the current value
                board.insert(row, col, value);

                if (solveRows(initialRow, finalRow)) {
                    // Leads to a solution
                    return true;

                } else {
                    // Try with another value
                    board.delete(row, col);
                }
            }
        }

        return false;
    }

}
