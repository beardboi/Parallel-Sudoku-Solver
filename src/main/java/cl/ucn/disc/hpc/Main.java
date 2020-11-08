package cl.ucn.disc.hpc;

import java.io.File;
import java.util.concurrent.*;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The entry point of the app
 */
public class Main {

    // The maximum number of threads that the program can use
    private static final int maxThreads = Runtime.getRuntime().availableProcessors();
    // The minimum number of threads that the program can use
    private static final int minThreads = 1;
    // The logger
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws InterruptedException {
        // The file .txt with the board
        File file = new File("board_test.txt");
        // Instance an InputFile to convert the txt into an array.
        InputFile boardFile = new InputFile(file);
        // Create an array that contains the sudoku board
        int[][] initialBoard = boardFile.readBoard();
        // Instance of a new board
        Board board = new Board(initialBoard);
        // Print the initial board
        System.out.println("Initial board: ");
        board.printBoard();

        // Calculate sequentially using backtracking
        // TODO: Add the sequential method using backtracking

        // Calculate from the min to the max number of threads
        for (int i = minThreads; i <= maxThreads; i++) {
            solveParallel(i, board);
        }

    }

    /**
     * This function execute
     *
     * @param threads The number of threads to calculate the task
     * @param board   The sudoku board
     * @throws InterruptedException If a thread is waiting, sleeping or occupied
     */
    public static void solveParallel(final int threads, Board board) throws InterruptedException {
        // The executor that manages the threads
        final ExecutorService pool = Executors.newFixedThreadPool(threads);
        // Give to the executor the task to submit
        pool.submit(new ParallelSudoku(board));
        // The executor will not receive anymore task
        pool.shutdown();
        // Take the execution time
        StopWatch stopWatch = StopWatch.createStarted();

        // Execute...
        if (pool.awaitTermination(2, TimeUnit.MINUTES)) {
            board.printBoard();
            logger.info("The board was solved in {} ns using {} threads.", stopWatch.getNanoTime(), threads);
        } else {
            logger.info("The board could not be solved.");
        }

    }

}