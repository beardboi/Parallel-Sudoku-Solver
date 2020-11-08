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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class InputFile {

    // The file
    private final File file;

    /**
     * The constructor
     *
     * @param file The file .txt that contains the sudoku board.
     */
    public InputFile(File file) {
        this.file = file;
    }

    /**
     * This function takes the file passed by the constructor and returned it as an 2d array.
     *
     * @return The array NxN that contains the sudoku board
     */
    public int[][] readBoard() {

        try {
            FileReader fr = new FileReader(this.file);
            BufferedReader br = new BufferedReader(fr);

            String currentLine;
            String size = br.readLine();

            int sizeBoard = Integer.parseInt(size);

            ArrayList<String> entries = new ArrayList<>();

            // TODO: Check this part of the code
            while ((currentLine = br.readLine()) != null) {
                String[] rowNumbers = currentLine.split(" ");
                entries.addAll(Arrays.asList(rowNumbers));

            }

            // Transform it into a matrix
            int[][] outputBoard = new int[sizeBoard][sizeBoard];

            int acc = 0;
            for (int i = 0; i < sizeBoard; i++) {
                for (int j = 0; j < sizeBoard; j++) {
                    outputBoard[i][j] = Integer.parseInt(entries.get(acc++));
                }
            }

            return outputBoard;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
