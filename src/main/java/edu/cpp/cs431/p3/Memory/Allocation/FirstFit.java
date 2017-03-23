package edu.cpp.cs431.p3.Memory.Allocation;

import edu.cpp.cs431.p3.Memory.Segment;

/**
 * @author waiphyo
 *         2/9/17.
 */
public class FirstFit extends Allocates {
    /**
     * Loop from head to tail.
     * Allocate the first large enough hole segment.
     * @param head Head of linked list
     * @param processID new process' ID
     * @param size new process' size
     * @return new segment allocated for this process
     */
    @Override
    public Segment addNewProcess(Segment head, int processID, int size) {
        Segment current = head;
        while (current != null) {
            if (validSegment(current, size)) {
                createSegment(current, processID, size);
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}
