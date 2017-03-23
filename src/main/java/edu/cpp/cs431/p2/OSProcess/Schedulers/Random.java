package edu.cpp.cs431.p2.OSProcess.Schedulers;

import edu.cpp.cs431.p2.Misc.General;
import edu.cpp.cs431.p2.OSProcess.OSProcess;

import java.util.List;

/**
 * @author waiphyo
 *         2/1/17.
 */
public class Random extends Scheduler {
    private int machineStartCycle;
    private int delta;
    public Random(List<OSProcess> allProcess, int delta) {
        super(allProcess);
        this.delta = delta;
        machineStartCycle = 0;
    }

    /**
     * Similar to round robin. But randomly select new process.
     * <p></p>
     * Use probability to increase chance which require longer cycles.
     */
    @Override
    public void run() {
        double totalCycles = getTotalCycle();
        boolean allDone = false;
        while (!allDone) {
            allDone = true;
            for (OSProcess each : allProcess) {
                if (!each.isCompleted()) {
                    allDone = false;
                    double probability = each.getRemainingCycle() / totalCycles;
                    if (General.RANDOM.nextDouble() >= probability) {
                        int remainingCycle = each.start(machineStartCycle);
                        machineStartCycle += remainingCycle < delta ? remainingCycle : delta;
                        each.stop(machineStartCycle);
                    }
                }
            }
        }
    }

    private double getTotalCycle() {
        double result = 0;
        for (OSProcess each : allProcess) {
            result += each.getCycle();
        }
        return result;
    }

    @Override
    protected StringBuilder getMethodName() {
        return new StringBuilder("Running Random Scheduler with quantum ").append(delta);
    }
}
