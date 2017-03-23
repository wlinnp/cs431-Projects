package edu.cpp.cs431.p2.OSProcess.Schedulers;

import edu.cpp.cs431.p2.Misc.General;
import edu.cpp.cs431.p2.OSProcess.OSProcess;

import java.util.Collections;
import java.util.List;

/**
 * @author waiphyo
 *         2/1/17.
 */
public abstract class Scheduler {
    List<OSProcess> allProcess;

    public Scheduler(List<OSProcess> allProcess) {
        this.allProcess = allProcess;
    }

    /**
     * @return double, calculate average for all processes
     */
    private double getAvgTurnAround() {
        double totalEndCycle = 0;
        for (OSProcess each : allProcess) {
            totalEndCycle += each.getEndCycle();
        }
        return totalEndCycle / allProcess.size();
    }

    public abstract void run();

    protected abstract StringBuilder getMethodName();

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getMethodName()).append(General.NEXT_LINE);
        Collections.sort(allProcess, ((o1, o2) -> o1.getEndCycle() < o2.getEndCycle() ? -1 : 1));
        for (OSProcess each : allProcess) {
            result.append(each.toString());
        }
        result.append("Average turnaround time: ").append(getAvgTurnAround()).append(General.LINE_SEPERATOR);
        return result.toString();
    }
}
