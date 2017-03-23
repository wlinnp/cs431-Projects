package edu.cpp.cs431.ex3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * @author waiphyo
 *         1/28/17.
 * CS431 | EX-3
 * Implementing custom blocking queue
 * <p></p>
 * Accept any arbitrary type.
 * <p></p>
 * Have a normal Linked List
 * <p></p>
 * 2 semaphores: 1 to control the size of the resource. 2 to control critical region
 */
public final class CustomBlockingQueue<T> {
    private static final int INITIAL_MUTEX = 1;
    private Queue<T> queue;
    private Semaphore emptySemaphore;
    private Semaphore fullSemaphore;
    private Semaphore mutex;
    public CustomBlockingQueue(final int availablePermits) {
        if (availablePermits < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }
        queue = new LinkedList<T>();
        emptySemaphore = new Semaphore(availablePermits);
        fullSemaphore = new Semaphore(0);
        mutex = new Semaphore(INITIAL_MUTEX);
    }

    /**
     * Print Error Message for all exceptions within this class
     * <p></p>
     * @param e Exception
     */
    private void showError(Exception e) {
        System.out.println("Exception in Custom Blocking Queue");
        System.out.println("==================================");
        System.out.println(e.getClass());
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

    /**
     * Consumer Method
     * <p></p>
     * Removing first element in the queue
     * <p></p>
     * Infinite loop till it has something to return
     * <p></p>
     * ensure critical region is owned by current call.
     * <p></p>
     * remove first element if any
     * <p></p>
     * quit critical region
     * <p></p>
     * increase shared resource count
     * <p></p>
     * @return ADT
     */
    public T dequeue() {
        T result = null;
        try {
            fullSemaphore.acquire();
            mutex.acquire();
            result = queue.remove();
        } catch (InterruptedException ie) {
            showError(ie);
        } finally {
            mutex.release();
            emptySemaphore.release();
            return result;
        }
    }

    /**
     * Producer method
     * <p></p>
     * Add new resource to the shared resource queue
     * <p></p>
     * check if there is room to add in shared resource
     * <p></p>
     * ensure critical region is owned by this process
     * <p></p>
     * add the resource
     * <p></p>
     * quit the critical region
     * <p></p>
     * @param input ADT
     */
    public void enqueue(T input) {
        try {
            emptySemaphore.acquire();
            mutex.acquire();
            queue.add(input);
        } catch (InterruptedException ie) {
            showError(ie);
        } finally {
            mutex.release();
            fullSemaphore.release();
        }
    }
}
