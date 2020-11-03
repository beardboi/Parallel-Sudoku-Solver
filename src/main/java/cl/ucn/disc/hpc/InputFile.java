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
     * @param file
     */
    public InputFile(File file) {
        this.file = file;
    }

    /**
     * This function takes the file passed by the constructor and convert
     * @return
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
