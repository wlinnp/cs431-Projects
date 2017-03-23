package edu.cpp.cs431.p3.Memory;

import edu.cpp.cs431.p3.Memory.Allocation.Allocates;
import edu.cpp.cs431.p3.Misc.General;

import java.util.HashMap;
import java.util.Map;

/**
 * @author waiphyo
 *         2/9/17.
 */
public class MemoryList {
    private Segment head;
    private Map<Integer, Job> allJobs;

    public MemoryList(int totalSize) {
        if (totalSize <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        head = new Segment(Segment.HOLE_ID, 0, totalSize);
        allJobs = new HashMap<Integer, Job>();
    }

    public void addJobs(int id, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        allJobs.put(id, new Job(id, size));
    }

    /**
     * check linked list is single list.
     *   If so, removing only one element
     *   If not, check the head to remove the head.
     *     If not, check the remaining.
     * @param processID removing process' ID
     * @return success or failure
     */
    public boolean deAllocate(int processID) {
        if (isSingleList()) {
            return deAllocateSingle(processID);
        } else {
            return deAllocateHead(processID) || deAllocateMiddle(processID);
        }
    }

    /**
     * Update the head's ID to hole.
     * If head's next is also a hole, merge them.
     * @param processID removing process' ID
     * @return success or failure
     */
    private boolean deAllocateHead(final int processID) {
        if (head.getProcessID() == processID) {
            Segment next = head.getNext();
            head.setProcessID(Segment.HOLE_ID);
            if (next.getProcessID() == Segment.HOLE_ID) { // Next is Hole, combine them.
                deAllocateProcessHole(null, head, next);
            }
            return true;
        }
        return false;
    }

    public Segment allocate(final int processID, Allocates allocateMethods) {
        Job addingJob = allJobs.get(processID);
        if (addingJob == null) {
            return null;
        } else {
            return allocateMethods.addNewProcess(head, addingJob.getProcessID(), addingJob.getSize());
        }
    }

    private void deAllocateTail(Segment prev, Segment current) {
        if (prev.getProcessID() == Segment.HOLE_ID) { // Prev is Hole, combine them.
            prev.setSize(prev.getSize() + current.getSize());
            prev.setNext(null);
        } else {
            current.setProcessID(Segment.HOLE_ID);
        }
    }

    private boolean deAllocateMiddle(final int processID) {
        Segment prev = head;
        Segment current = prev.getNext();
        while (current != null) {
            if (current.getProcessID() == processID) {
                if (current.getNext() == null) { // tail
                    deAllocateTail(prev, current);
                } else { // middle segment
                    Segment next = current.getNext();
                    if (prev.getProcessID() == Segment.HOLE_ID && next.getProcessID() == Segment.HOLE_ID) {
                        deAllocateHoleHole(prev, current, next);
                    } else if (prev.getProcessID() != Segment.HOLE_ID && next.getProcessID() != Segment.HOLE_ID) {
                        deAllocateProcessProcess(prev, current, next);
                    } else if (prev.getProcessID() == Segment.HOLE_ID && next.getProcessID() != Segment.HOLE_ID) {
                        deAllocateHoleProcess(prev, current, next);
                    } else if (prev.getProcessID() != Segment.HOLE_ID && next.getProcessID() == Segment.HOLE_ID) {
                        deAllocateProcessHole(prev, current, next);
                    }
                }
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false;
    }

    private void deAllocateHoleHole(Segment prev, Segment current, Segment next) {
        prev.setSize(prev.getSize() + current.getSize() + next.getSize());
        prev.setNext(next.getNext());
        current.setNext(null);
        next.setNext(null);
    }

    private void deAllocateProcessProcess(Segment prev, Segment current, Segment next) {
        current.setProcessID(Segment.HOLE_ID);
    }

    private void deAllocateHoleProcess(Segment prev, Segment current, Segment next) {
        prev.setSize(prev.getSize() + current.getSize());
        prev.setNext(current.getNext());
        current.setNext(null);
    }

    private void deAllocateProcessHole(Segment prev, Segment current, Segment next) {
        current.setSize(current.getSize() + next.getSize());
        current.setNext(next.getNext());
        current.setProcessID(Segment.HOLE_ID);
        next.setNext(null);
    }

    private boolean deAllocateSingle(final int processID) {
        if (head.getProcessID() == processID) {
            head.setProcessID(Segment.HOLE_ID);
            return true;
        }
        return false;
    }

    private boolean isSingleList() {
        return head.getNext() == null;
    }

    public String getJobs() {
        return allJobs.toString();
    }

    public Segment getHead() {
        return head;
    }

    public String toString() {
        Segment tempHead = head;
        StringBuilder result = new StringBuilder();
        while (tempHead != null) {
            result.append(tempHead).append(General.TAB);
            tempHead = tempHead.getNext();
        }
        return result.toString();
    }
}
