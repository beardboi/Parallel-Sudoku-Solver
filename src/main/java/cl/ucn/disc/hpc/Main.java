package cl.ucn.disc.hpc;

import java.io.File;

public class Main {

    /*
     * The entry point of the app.
     */
    public static void main(String[] args) {
        // The file .txt with the board
        File file = new File("board_test.txt");
        // Instance an InputFile to convert the txt into an array.
        InputFile boardFile = new InputFile(file);
        // Create an array that contains the sudoku board
        int[][] board = boardFile.readBoard();
        // Print the board
        InputFile.printBoard(board);
        // Solve the sudoku board sequentially using backtracking
        SequentialSudoku sequentialSol = new SequentialSudoku(board);
        sequentialSol.SequentialSolve();



    }

}
