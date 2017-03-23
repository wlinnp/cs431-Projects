package edu.cpp.cs431.p2.OSProcess.Schedulers;

import edu.cpp.cs431.p2.OSProcess.OSProcess;

import java.util.List;

/**
 * @author waiphyo
 *         2/1/17.
 */
public class FirstInFirstOut extends Scheduler {
    private int machineStartCycle;
    public FirstInFirstOut(List<OSProcess> allProcess) {
        super(allProcess);
        machineStartCycle = 0;
    }

    /**
     * Since the list is added sequentially,
     * Just run each process
     */
    @Override
    public void run() {
        for (OSProcess each : allProcess) {
            each.start(machineStartCycle);
            machineStartCycle += each.getCycle();
            each.stop(machineStartCycle);
        }
    }

    @Override
    protected StringBuilder getMethodName() {
        return new StringBuilder("Running First-come, First-served Scheduler");
    }
}
