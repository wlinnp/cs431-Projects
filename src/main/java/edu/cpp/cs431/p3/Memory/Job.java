package edu.cpp.cs431.p3.Memory;

/**
 * @author waiphyo
 *         2/14/17.
 */
public class Job {
    private int processID;
    private int size;

    public Job(int processID, int size) {
        this.processID = processID;
        this.size = size;
    }

    public int getProcessID() {
        return processID;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return String.format("[%d %d]", processID, size);
    }
}
