package edu.cpp.cs431.p3.Memory.Allocation;

import edu.cpp.cs431.p3.Memory.Segment;

/**
 * @author waiphyo
 *         2/9/17.
 */
public class NextFit extends Allocates {
    private static Segment startNode;

    /**
     * If starting node is empty, start from head.
     * If not, start from starting node.
     *   In this case, if there is no suitable till tail,
     *   loop again from head to starting node.
     * Allocate the first large enough hole segment.
     * @param head Head of linked list
     * @param processID new process' ID
     * @param size new process' size
     * @return new segment allocated for this process
     */
    @Override
    public Segment addNewProcess(Segment head, int processID, int size) {
        if (startNode == null) {
            startNode = head;
        }
        Segment current = startNode;
        while (current != null) {
            if (validSegment(current, size)) {
                createSegment(current, processID, size);
                startNode = current;
                return current;
            }
            current = current.getNext();
        }

        current = head;
        while (current != null || current != startNode) {
            if (validSegment(current, size)) {
                createSegment(current, processID, size);
                startNode = current;
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

}
