package edu.cpp.cs431.p2.OSProcess.Schedulers;

import edu.cpp.cs431.p2.OSProcess.OSProcess;
import java.util.Collections;
import java.util.List;

/**
 * @author waiphyo
 *         2/1/17.
 */
public class ShortestFirst extends Scheduler {
    private int machineStartCycle;
    public ShortestFirst(List<OSProcess> allProcess) {
        super(allProcess);
        machineStartCycle = 0;
    }

    /**
     * Sort the process list based on smallest cycle.
     * Run each process
     */
    @Override
    public void run() {
        Collections.sort(allProcess, (o1, o2) -> o1.getCycle() < o2.getCycle() ? -1 : 1);
        for (OSProcess each : allProcess) {
            each.start(machineStartCycle);
            machineStartCycle += each.getCycle();
            each.stop(machineStartCycle);
        }
    }

    @Override
    protected StringBuilder getMethodName() {
        return new StringBuilder("Running Shortest First Scheduler");
    }
}
