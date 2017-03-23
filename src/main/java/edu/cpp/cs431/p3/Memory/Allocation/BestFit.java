package edu.cpp.cs431.p3.Memory.Allocation;

import edu.cpp.cs431.p3.Memory.Segment;

/**
 * @author waiphyo
 *         2/9/17.
 */
public class BestFit extends Allocates {
    /**
     * loop from head to tail of linked list
     * memorize location of holes with the smallest size bigger than necessary
     * @param head Head of linked list
     * @param processID new process' ID
     * @param size new process' size
     * @return new segment allocated for this process
     */
    @Override
    public Segment addNewProcess(Segment head, int processID, int size) {
        Segment bestFit = null;
        Segment current = head;
        while (current != null) {
            if (validSegment(current, size)) {
                if (bestFit == null || bestFit.getSize() > current.getSize()) {
                    bestFit = current;
                }
            }
            current = current.getNext();
        }

        if(bestFit == null) {
            return null;
        } else {
            createSegment(bestFit, processID, size);
            return bestFit;
        }
    }
}
