package edu.cpp.cs431.p3.Memory.Allocation;

import edu.cpp.cs431.p3.Memory.Segment;

/**
 * @author waiphyo
 *         2/9/17.
 */
public abstract class Allocates {
    public abstract Segment addNewProcess(Segment head, int processID, int size);

    /**
     * change current hole's id to process id.
     * If the hole is bigger than necessary,
     * Reduce the size, create a new hole with the remaining size
     * @param current current hole segment
     * @param processID new process' ID
     * @param size new process' size
     */
    protected void createSegment(Segment current, int processID, int size) {
        current.setProcessID(processID);
        if (current.getSize() > size) {
            int totalSize = current.getSize();
            current.setSize(size);
            Segment hole = new Segment(Segment.HOLE_ID, current.getStartIndex() + size, totalSize - size);
            hole.setNext(current.getNext());
            current.setNext(hole);
        }
    }

    protected boolean validSegment(Segment current, int size) {
        return current.getProcessID() == Segment.HOLE_ID && current.getSize() >= size;
    }
}
