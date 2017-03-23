package edu.cpp.cs431.p3;

import edu.cpp.cs431.p3.Memory.Allocation.BestFit;
import edu.cpp.cs431.p3.Memory.Allocation.FirstFit;
import edu.cpp.cs431.p3.Memory.Allocation.NextFit;
import edu.cpp.cs431.p3.Memory.Allocation.WorstFit;
import edu.cpp.cs431.p3.Memory.MemoryList;
import edu.cpp.cs431.p3.Memory.Segment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author waiphyo
 *         2/15/17.
 */
public class MemoryListTest1 {
    private MemoryList memoryList;
    private Segment head;
    @Before
    public void Init() {
        memoryList = new MemoryList(100);
        head = memoryList.getHead();
        memoryList.addJobs(1, 5);
        memoryList.addJobs(2, 17);
        memoryList.addJobs(3, 100);
        memoryList.addJobs(4, 4);
        memoryList.addJobs(5, 5);
        memoryList.addJobs(6, 90);
        memoryList.addJobs(7, 95);
    }

    /**
     * Add 1 process
     * Remove that process
     */
    @Test
    public void Test1() {
        memoryList.allocate(1, new FirstFit());
        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == Segment.HOLE_ID &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 95);

        Assert.assertEquals(true, memoryList.deAllocate(1));
        Assert.assertTrue(head.getProcessID() == Segment.HOLE_ID &&
                head.getStartIndex() == 0 &&
                head.getSize() == 100 &&
                head.getNext() == null);
    }

    /**
     * Add 2 processes
     */
    @Test
    public void Test3() {
        memoryList.allocate(1, new FirstFit());
        memoryList.allocate(2, new FirstFit());

        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == 2 &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 17 &&
                head.getNext().getNext().getProcessID() == Segment.HOLE_ID &&
                head.getNext().getNext().getStartIndex() == 22 &&
                head.getNext().getNext().getSize() == 78 &&
                head.getNext().getNext().getNext() == null);
    }

    /**
     * Add 3 processes
     * Remove middle process
     */
    @Test
    public void Test4() {
        memoryList.allocate(1, new FirstFit());
        memoryList.allocate(2, new FirstFit());
        memoryList.allocate(4, new WorstFit());
        memoryList.deAllocate(2);
        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == Segment.HOLE_ID &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 17 &&
                head.getNext().getNext().getProcessID() == 4 &&
                head.getNext().getNext().getStartIndex() == 22 &&
                head.getNext().getNext().getSize() == 4 &&
                head.getNext().getNext().getNext() != null);
    }

    /**
     * Add 3 processes
     * Remove middle process
     * Add it back with Best Fit
     */
    @Test
    public void Test5() {
        memoryList.allocate(1, new FirstFit());
        memoryList.allocate(2, new FirstFit());
        memoryList.allocate(4, new BestFit());
        memoryList.deAllocate(2);
        memoryList.allocate(2, new BestFit());
        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == 2 &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 17 &&
                head.getNext().getNext().getProcessID() == 4 &&
                head.getNext().getNext().getStartIndex() == 22 &&
                head.getNext().getNext().getSize() == 4 &&
                head.getNext().getNext().getNext() != null);
    }

    /**
     * Add 3 processes
     * Remove middle process
     * Add it back with Worst Fit
     */
    @Test
    public void Test6() {
        memoryList.allocate(1, new FirstFit());
        memoryList.allocate(2, new FirstFit());
        memoryList.allocate(4, new WorstFit());
        memoryList.deAllocate(2);
        memoryList.allocate(2, new WorstFit());
        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == Segment.HOLE_ID &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 17 &&
                head.getNext().getNext().getProcessID() == 4 &&
                head.getNext().getNext().getStartIndex() == 22 &&
                head.getNext().getNext().getSize() == 4 &&
                head.getNext().getNext().getNext().getProcessID() == 2 &&
                head.getNext().getNext().getNext().getStartIndex() == 26 &&
                head.getNext().getNext().getNext().getSize() == 17 &&
                head.getNext().getNext().getNext().getNext().getProcessID() == Segment.HOLE_ID);

    }

    /**
     * Add 3 process. which will fill the queue
     * Remove last process
     * Add it back
     * Remove middle process
     * Remove last process
     * Add them back
     * Remove middle process
     * Remove first process
     */
    @Test
    public void Test7() {
        memoryList.allocate(1, new FirstFit());
        memoryList.allocate(5, new FirstFit());
        memoryList.allocate(6, new WorstFit());

        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == 5 &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 5 &&
                head.getNext().getNext().getProcessID() == 6 &&
                head.getNext().getNext().getStartIndex() == 10 &&
                head.getNext().getNext().getSize() == 90 &&
                head.getNext().getNext().getNext() == null);

        memoryList.deAllocate(6);

        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == 5 &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 5 &&
                head.getNext().getNext().getProcessID() == Segment.HOLE_ID &&
                head.getNext().getNext().getStartIndex() == 10 &&
                head.getNext().getNext().getSize() == 90 &&
                head.getNext().getNext().getNext() == null);

        memoryList.allocate(6, new WorstFit());
        memoryList.deAllocate(5);
        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == Segment.HOLE_ID &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 5 &&
                head.getNext().getNext().getProcessID() == 6 &&
                head.getNext().getNext().getStartIndex() == 10 &&
                head.getNext().getNext().getSize() == 90 &&
                head.getNext().getNext().getNext() == null);

        memoryList.deAllocate(6);
        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == Segment.HOLE_ID &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 95 &&
                head.getNext().getNext() == null);

        memoryList.allocate(5, new FirstFit());
        memoryList.allocate(6, new WorstFit());
        memoryList.deAllocate(5);
        memoryList.deAllocate(1);
        Assert.assertTrue(head.getProcessID() == 0 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 10 &&
                head.getNext().getProcessID() == 6 &&
                head.getNext().getStartIndex() == 10 &&
                head.getNext().getSize() == 90 &&
                head.getNext().getNext() == null);

    }

    @Test
    public void Test8() {
        memoryList.allocate(1, new BestFit());
        memoryList.allocate(7, new BestFit());
        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == 7 &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 95 &&
                head.getNext().getNext() == null);
        Assert.assertEquals(true, memoryList.deAllocate(1));
        Assert.assertTrue(head.getProcessID() == Segment.HOLE_ID &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == 7 &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 95 &&
                head.getNext().getNext() == null);

        memoryList.allocate(1, new NextFit());
        Assert.assertEquals(true, memoryList.deAllocate(7));
        Assert.assertTrue(head.getProcessID() == 1 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 5 &&
                head.getNext().getProcessID() == Segment.HOLE_ID &&
                head.getNext().getStartIndex() == 5 &&
                head.getNext().getSize() == 95 &&
                head.getNext().getNext() == null);

        Assert.assertEquals(true, memoryList.deAllocate(1));
        Assert.assertTrue(head.getProcessID() == Segment.HOLE_ID &&
                head.getStartIndex() == 0 &&
                head.getSize() == 100 &&
                head.getNext() == null);
    }

    /**
     * Add 1 process with size 100
     * Add another process
     * Remove invalid process
     * Remove valid process
     */
    @Test
    public void Test11() {
        memoryList.allocate(3, new BestFit());
        Segment head = memoryList.getHead();
        Assert.assertTrue(head.getProcessID() == 3 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 100 &&
                head.getNext() == null);

        Segment segment = memoryList.allocate(1, new BestFit());
        Assert.assertTrue(head.getProcessID() == 3 &&
                head.getStartIndex() == 0 &&
                head.getSize() == 100 &&
                head.getNext() == null);

        Assert.assertEquals(false, memoryList.deAllocate(1));
        Assert.assertEquals(true, memoryList.deAllocate(3));
        Assert.assertTrue(head.getProcessID() == Segment.HOLE_ID &&
                head.getStartIndex() == 0 &&
                head.getSize() == 100 &&
                head.getNext() == null);
    }
}
