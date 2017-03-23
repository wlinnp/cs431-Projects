package edu.cpp.cs431.p3.Memory.Allocation;

import edu.cpp.cs431.p3.Memory.Segment;

/**
 * @author waiphyo
 *         2/9/17.
 */
public class WorstFit extends Allocates {

    /**
     * loop from head to tail of linked list
     * memorize location of holes with the largest size bigger than necessary
     * @param head Head of linked list
     * @param processID new process' ID
     * @param size new process' size
     * @return new segment allocated for this process
     */
    @Override
    public Segment addNewProcess(Segment head, int processID, int size) {
        Segment worstFit = null;
        Segment current = head;
        while (current != null) {
            if (validSegment(current, size)) {
                if (worstFit == null || worstFit.getSize() < current.getSize()) {
                    worstFit = current;
                }
            }
            current = current.getNext();
        }

        if(worstFit == null) {
            return null;
        } else {
            createSegment(worstFit, processID, size);
            return worstFit;
        }
    }
}
