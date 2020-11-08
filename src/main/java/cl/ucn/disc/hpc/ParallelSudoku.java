package cl.ucn.disc.hpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParallelSudoku implements Runnable {

    // The sudoku board
    private final Board board;
    // The board size
    private final int size;
    // The logger
    private final static Logger logger = LoggerFactory.getLogger(ParallelSudoku.class);

    /**
     * The constructor
     *
     * @param board The sudoku board
     */
    public ParallelSudoku(Board board) {
        this.board = board;
        this.size = board.getSize();
    }

    public boolean solve(Board board, int row, int col) {

        // Check if the board is completed
        if (Board.isCompleted(board)) {
            return true;
        }
        // If the col reached to the end
        if (col == size) {
            row++;
            col = 0;
        }
        // Check if the cell is empty
        if (!board.isEmptyCell(row, col)) {

            return solve(board, row, col + 1);
        }
        // Check from 1 to 9 if the value
        for (int num = 1; num <= size; num++) {
            // Check validity
            if (board.isValid(row, col, num)) {

                // Given that the value is valid, try with it
                board.insertValue(row, col, num);

                // Using backtracking, see if the value leads to a solution
                if (solve(board, row, col + 1))
                    return true;
            }

            // Try with another number, so make the cell empty
            board.undoValue(row, col);
        }
        // Solution not found
        return false;
    }

    /**
     * This function will be execute by the instance of the ExecutorService.
     */
    @Override
    public void run() {
        // Try to solve the board and tell if you find a solution
        if (solve(board, 0, 0)) {
            logger.info("Solution was found");
        } else {
            logger.info("Solution was not found");
        }
    }

}
