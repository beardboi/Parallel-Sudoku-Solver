package cl.ucn.disc.hpc;

import java.util.ArrayList;

public class SequentialSudoku {

    // The board
    private int[][] board;
    // The board size
    private int boardSize;
    //

    public SequentialSudoku(int[][] board) {
        this.board = board;
        this.boardSize = board.length;
    }

    /**
     * This function solve the sudoku board using backtracking
     * @return true if
     */
    public boolean SequentialSolve() {
        // Through the rows
        for (int i = 0; i < boardSize; i++) {
            // Through the columns
            for (int j = 0; j < boardSize; j++) {
                // If the cells contains a 0, it means that is an empty cell
                if (board[i][j] == 0) {
                    // Check from 1 ... 9 to see if the insertion is valid
                    for (int num = 1; num <= boardSize; num++) {
                        if (isValid(board, i, j, num)) {
                            board[i][j] = num;
                            // Try to find out if it leads to any solution (using recursion)
                            if (SequentialSolve()) {
                                // Return a solution
                                return true;
                            }
                            // Undo the insertion
                            board[i][j] = 0;
                        }

                    }
                    // No more cells to fill, move to the next row
                    return false;
                }

            }
        }
        // Print the solution founded
        printSudoku(board);
        return true;
    }


    /**
     * This function return the
     *
     * @param board     The board
     * @param row       The row
     * @param column    The column
     * @param cellValue The value to insert
     * @return TRUE for a valid insertion, FALSE in the other case.
     */
    public static boolean isValid(int[][] board, int row, int column, int cellValue) {
        // If any of the functions to check returns a true, it means that the insertion goes against the constraints
        return !(checkColumn(board, column, cellValue) || checkRow(board, row, cellValue) || checkSubgrid(board, row, column, cellValue));

    }

    /**
     * This function checks the column from a board to make sure that the value to put is a valid one.
     *
     * @param board     The board of sudoku
     * @param column    The column
     * @param cellValue The value to insert
     * @return
     */
    public static boolean checkColumn(int[][] board, int column, int cellValue) {

        for (int[] ints : board) {
            if (ints[column] == cellValue) {
                return true;
            }
        }
        return false;

    }

    /**
     * This function checks the row from a board to make sure that the value to put is a valid one.
     *
     * @param board     The board
     * @param row       The row
     * @param cellValue The value to insert
     * @return
     */
    public static boolean checkRow(int[][] board, int row, int cellValue) {

        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == cellValue) {
                return true;
            }
        }
        return false;

    }

    /**
     * This function checks the subgrid from a board to make sure that the value to put is a valid one.
     *
     * @param board     The board
     * @param column    The column
     * @param row       The row
     * @param cellValue The number that will be insert in the cell
     * @return
     */
    public static boolean checkSubgrid(int[][] board, int row, int column, int cellValue) {

        int subGridRow = row - row % 3;
        int subGridColumn = column - column % 3;

        for (int i = subGridRow; i < subGridRow + 3; i++) {
            for (int j = subGridColumn; j < subGridColumn + 3; j++) {

                if (board[i][j] == cellValue) {
                    return true;
                }

            }
        }
        return false;

    }

    /**
     * This function return the numbers of empty cells from a board
     *
     * @param board The board
     * @return The number of empty cells
     */
    public static int getNumberEmptyCells(int[][] board) {
        // The accumulator
        int acc = 0;

        for (int[] ints : board) {
            for (int j = 0; j < board.length; j++) {
                //  Check if the cell is equal to 0
                if (ints[j] == 0) acc++;
            }
        }
        return acc;
    }

    /**
     * This function show the Sudoku printed
     *
     * @param board The board
     */
    public static void printSudoku(int[][] board) {

        for (int[] bytes : board) {

            for (int column = 0; column < board.length; column++) {
                System.out.print(bytes[column] + " ");
            }
            System.out.print("\n");

        }

    }

    public boolean isSolved(int[][] board) {

        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
