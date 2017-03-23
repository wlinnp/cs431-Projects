package edu.cpp.cs431.p2.OSProcess.Schedulers;

import edu.cpp.cs431.p2.OSProcess.OSProcess;

import java.util.List;

/**
 * @author waiphyo
 *         2/1/17.
 */
public class RoundRobin extends Scheduler {
    private int machineStartCycle;
    private int delta;
    public RoundRobin(List<OSProcess> allProcess, int delta) {
        super(allProcess);
        this.delta = delta;
        machineStartCycle = 0;
    }

    /**
     * Run each process for delta amount of time
     */
    @Override
    public void run() {
        boolean allDone = false;
        while (!allDone) {
            allDone = true;
            for (OSProcess each : allProcess) {
                if (!each.isCompleted()) {
                    allDone = false;
                    int remainingCycle = each.start(machineStartCycle);
                    machineStartCycle += remainingCycle < delta ? remainingCycle : delta;
                    each.stop(machineStartCycle);
                }
            }
        }
    }

    @Override
    protected StringBuilder getMethodName() {
        return new StringBuilder("Running Round Robin Scheduler with quantum ").append(delta);
    }
}
