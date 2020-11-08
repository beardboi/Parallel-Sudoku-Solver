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

public class Board {

    // The board that will be solved
    private int[][] board;
    // The initial/original board
    private final int[][] initialBoard;
    // The size of the number
    private final int size;

    /**
     * The constructor
     *
     * @param inputBoard The sudoku board represented as an 2d array
     */
    public Board(int[][] inputBoard) {
        this.initialBoard = inputBoard;
        this.board = initialBoard;
        this.size = initialBoard.length;
    }

    /**
     * This function checks if the board is completed
     *
     * @param board The sudoku board
     * @return True if the board is completed, false in the other case
     */
    public static boolean isCompleted(Board board) {
        // Accumulator of empty cells
        int acc = 0;

        // Run over all the board
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.isEmptyCell(i, j)) {
                    acc++;
                }
            }
        }
        // If there is no empty cell, the board is solved
        if (acc == 0) {
            return true;
        }
        // Incomplete board
        return false;
    }

    /**
     * This function checks if a cell is empty
     *
     * @param row    The row of the cell
     * @param column The column of the cell
     * @return True if the cell is empty, false in the other case
     */
    public boolean isEmptyCell(int row, int column) {
        if (board[row][column] == 0) {
            return true;
        }

        return false;
    }

    /**
     * This function return the length of the board
     */
    public int getSize() {
        return size;
    }

    /**
     * This function checks the row from a board to make sure that the value to put is a valid one.
     *
     * @param row   The row from the board cell
     * @param value The value to insert
     * @return True if the value doesn't exist in the row, false in the other case
     */
    public boolean checkRow(int row, int value) {

        for (int col = 0; col < size; col++) {
            if (board[row][col] == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * This function checks the column from a board to make sure that the value to put is a valid one.
     *
     * @param col   The column from the board cell
     * @param value The value to insert
     * @return True if the value doesn't exist in the column, false in the other case
     */
    public boolean checkColumn(int col, int value) {

        for (int row = 0; row < size; row++) {
            if (board[row][col] == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * This function checks the subgrid from a board to make sure that the value to put is a valid one.
     *
     * @param col   The column from the board cell
     * @param row   The row from the board cell
     * @param value The number that will be insert in the cell
     * @return True if the value doesn't exist in the subgrid, false in the other case
     */
    public boolean checkSubGrid(int row, int col, int value) {
        // TODO: Fix this to solve calculate NxN boards
        int subgridRow = (row / 3) * 3;
        int subgridCol = (col / 3) * 3;

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[subgridRow + i][subgridCol + j] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This function check the validity of a movement
     *
     * @param row   The row from the board cell
     * @param col   The column from the board cell
     * @param value The value to insert
     * @return TRUE for a valid insertion, FALSE in the other case.
     */
    public boolean isValid(int row, int col, int value) {

        if (checkRow(row, value)) {
            return false;
        }

        if (checkColumn(col, value)) {
            return false;
        }

        if (checkSubGrid(row, col, value)) {
            return false;
        }

        return true;
    }

    /**
     * This function change the value of a cell given it position.
     *
     * @param row   The row from the board cell
     * @param col   The column from the board cell
     * @param value The value to insert
     */
    public void insertValue(int row, int col, int value) {

        board[row][col] = value;

    }

    /**
     * This function makes the specific board position an empty cell
     *
     * @param row The row from the board cell
     * @param col The col from the board cell
     */
    public void undoValue(int row, int col) {
        board[row][col] = 0;
    }

    /**
     * This function reset the board to the initial board
     */
    public void resetBoard() {
        this.board = initialBoard;
    }

    /**
     * This function prints the board given it a format
     */
    public void printBoard() {
        // count for the rows
        int countRow = 0;

        System.out.println("/-----------------------------\\");
        for (int[] row : board) {
            // count for the columns
            int countCol = 0;

            System.out.print("|");
            for (int value : row) {
                System.out.print(" " + value + " ");
                if (countCol == 2 || countCol == 5 || countCol == 8) {
                    System.out.print("|");
                }
                countCol++;
            }

            System.out.println();
            if (countRow == 2 || countRow == 5) {
                System.out.println("|---------+---------+---------|");
            }
            countRow++;
        }
        System.out.println("\\-----------------------------/");
    }
}