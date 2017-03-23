package edu.cpp.cs431.p3.Memory;

/**
 * @author waiphyo
 *         2/9/17.
 */
public class Segment {
    public static final int HOLE_ID = 0;
    private int processID;
    private int startIndex;
    private int size;
    private Segment next;

    public Segment(int processID, int startIndex, int size) {
        this.processID = processID;
        this.startIndex = startIndex;
        this.size = size;
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Segment getNext() {
        return next;
    }

    public void setNext(Segment next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("(%d %d %d)", processID, startIndex, size);
    }
}
