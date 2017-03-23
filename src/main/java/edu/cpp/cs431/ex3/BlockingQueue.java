package edu.cpp.cs431.ex3;

/**
 * @author waiphyo
 *         1/27/17.
 * CS431 | EX-3
 * Testing Blocking Queue by implmenting it using semaphores.
 */
public final class BlockingQueue {

    /**
     * Copied from Exercise 3 print-out
     * <p></p>
     * Update accepting dequeue to Integer from int
     * <p></p>
     * Check null value which is correct result if queue is empty
     * <p></p>
     * @param args String array
     */
    public static void main(String[] args) {
        final CustomBlockingQueue<Integer> queue = new CustomBlockingQueue<Integer>(100);
        Runnable r = new Runnable() { // replace lambda if you don’t have access to Java 8
            public void run() {
                for (int i = 0; i < 200; i++) {
                    try {
                        int n = queue.dequeue();
                        System.out.println(n + " removed");
                        Thread.sleep(500);
                    } catch (Exception e) {
                        System.out.println(e.getCause());
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
        for (int i = 0; i < 200; i++) {
            System.out.println("Adding " + i);
            queue.enqueue(i);
        }
    }
}
