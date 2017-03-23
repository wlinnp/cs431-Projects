package edu.cpp.cs431.p2;

import edu.cpp.cs431.p2.OSProcess.ProcessLoader;
import edu.cpp.cs431.p2.OSProcess.Schedulers.*;


/**
 * @author waiphyo
 *         2/1/17.
 * CS431  |  Project-2
 */
public class ProcessScheduler {

    /**
     * Get File name
     * <p></p>
     * Load all processes
     * <p></p>
     * Run each scheduler
     * <p></p>
     * @param args filename
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Please attach the file name.");
        }
        ProcessLoader processLoader = new ProcessLoader(args[0]);
        processLoader.extractProcessList();

        runSchedulers(new FirstInFirstOut(processLoader.refresh()));
        runSchedulers(new ShortestFirst(processLoader.refresh()));
        runSchedulers(new RoundRobin(processLoader.refresh(), 50));
        runSchedulers(new RoundRobin(processLoader.refresh(), 100));
        runSchedulers(new Random(processLoader.refresh(), 50));
        runSchedulers(new Random(processLoader.refresh(), 100));
    }

    private static void runSchedulers(Scheduler scheduler) {
        scheduler.run();
        System.out.println(scheduler.toString());
    }
}
