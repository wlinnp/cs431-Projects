package edu.cpp.cs431.p1.Process;

/**
 * @author Wai Phyo
 * CS-431   |   Project-1
 * <p></p>
 * enumeration for process status
 */
public enum ProcessStatus {
    RUNNING(0),
    READY(1),
    BLOCKED(2);

    private int status;

    private ProcessStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
