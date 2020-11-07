package cl.ucn.disc.hpc;

public class SequentialSudoku {

    // The board
    private final Board board;

    /**
     * The constructor
     *
     * @param board The sudoku board
     */
    public SequentialSudoku(Board board) {
        this.board = board;
    }

    /**
     * This function solve the sudoku board using backtracking
     *
     * @return True if the board was solved and false if it not
     */
    public boolean solve() {
        // Through the rows
        for (int i = 0; i < board.getSize(); i++) {
            // Through the columns
            for (int j = 0; j < board.getSize(); j++) {
                // If the cells contains a 0, it means that is an empty cell
                if (board.isEmptyCell(i, j)) {
                    // Check from 1 to 9 to see if the insertion is valid
                    for (int num = 1; num <= board.getSize(); num++) {
                        if (board.isValid(i, j, num)) {
                            // Insert the value
                            board.insert(i, j, num);
                            // Try to find out if it leads to any solution (using recursion)
                            if (solve()) {
                                // Return a solution
                                return true;
                            }
                            // Undo the insertion
                            board.delete(i, j);
                        }

                    }
                    // No more cells to fill, move to the next row
                    return false;
                }

            }
        }
        // Print the solution founded

        return true;
    }


}
