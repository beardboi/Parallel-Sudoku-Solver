/*
 * Copyright 2020 Diego Bravo B. diego.bravo@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.hpc;


public class SequentialSudoku {

    // The board
    private Board board;

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
                            board.insertValue(i, j, num);
                            // Try to find out if it leads to any solution (using recursion)
                            if (solve()) {
                                // Return a solution
                                return true;
                            }
                            // Undo the insertion
                            board.undoValue(i, j);
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
