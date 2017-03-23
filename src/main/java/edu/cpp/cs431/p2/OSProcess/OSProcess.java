package edu.cpp.cs431.p2.OSProcess;

import edu.cpp.cs431.p2.Misc.General;

/**
 * @author waiphyo
 *         2/1/17.
 */
public class OSProcess {
    private int pid;
    private int cycle;
    private int completed;
    private int startCycle;
    private int endCycle;
    private boolean running;

    /**
     * When process is created, it is not running.
     * <p></p>
     * @param pid process id
     * @param cycle number of cycles for each process
     */
    public OSProcess(int pid, int cycle) {
        this.pid = pid;
        this.cycle = cycle;
        running = false;
    }

    /**
     * Validate if the process is already running
     * <p></p>
     * set the flag to running
     * <p></p>
     * update the starting cycle
     * <p></p>
     * @param startCycle current number of cycle from scheduler which will start the process
     * @return how many cycles left for this process
     */
    public int start(int startCycle) {
        if (running) {
            throw new IllegalArgumentException("Process is already running.");
        }
        running = true;
        this.startCycle = startCycle;
        return cycle - completed;
    }

    /**
     * validate stop cycle is correct
     * <p></p>
     * validate process is still running
     * <p></p>
     * update flags and compute
     * <p></p>
     * @param stopCycle current number of cycle from scheduler which will stop the process
     */
    public void stop(int stopCycle) {
        if (stopCycle < startCycle) {
            throw new IllegalArgumentException("Stop cycle must not be less than start cycle." + pid + " " + startCycle + " " + stopCycle);
        }
        if (!running) {
            throw new IllegalArgumentException("edu.cpp.cs431.Misc.OSProcess.edu.cpp.cs431.Misc.OSProcess is already stopped.");
        }
        running = false;
        endCycle = stopCycle;
        completed += endCycle - startCycle;
    }

    public int getCycle() {
        return cycle;
    }

    public int getEndCycle() {
        return endCycle;
    }

    public int getPid() {
        return pid;
    }

    public int getRemainingCycle() {
        return cycle - completed;
    }

    /**
     * @return flag saying whether the process is completed
     */
    public boolean isCompleted() {
        return completed - cycle == 0;
    }

    /**
     * @return Printing status of process
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Process ").append(pid);
        if (completed == cycle) {
            result.append(" finishes on cycle ").append(endCycle);
        } else {
            result.append(" has completed up to ").append(completed);
        }
        result.append(General.NEXT_LINE);
        return result.toString();
    }

    /**
     * Resetting all data.
     * <p></p>
     *
     */
    public void reset() {
        completed = 0;
        startCycle = 0;
        endCycle = 0;
        running = false;
    }
}
