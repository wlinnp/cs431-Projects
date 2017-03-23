package edu.cpp.cs431.ex2.Threads;

import java.util.concurrent.BlockingQueue;

/**
 * @author waiphyo
 *         1/21/17.
 * CS-431 | EX-2
 */
public final class CountThread implements Runnable {
    private final BlockingQueue<String> queueTextContent;
    private final BlockingQueue<CharFrequency> queueFrequency;
    private CharFrequency charFrequency;
    public CountThread(BlockingQueue<String> queueTextContent, BlockingQueue<CharFrequency> queueFrequency) {
        this.queueTextContent = queueTextContent;
        this.queueFrequency = queueFrequency;
        charFrequency = new CharFrequency();
    }

    /**
     * If content queue is not empty, get the 1st value
     * Check for end of program
     * store count result to end of frequency queue
     */
    public void run() {
        while (true) {
            try {
                String content = queueTextContent.take();
                if (content.equals(General.END_OF_PROGRAM_STRING)) {
                    break;
                }
                if (content.equals(General.END_OF_FILE_STRING)) {
                    queueFrequency.put(charFrequency);
                    charFrequency = new CharFrequency();
                } else {
                    performCounts(content);
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        System.out.println(getClass().getName() + General.QUIT_NOTIFICATION);
    }

    /**
     * For each char, try to increase their respective values.
     * @param input String
     */
    private void performCounts(final String input) {
        char[] inputArray = input.toCharArray();
        for (char each : inputArray) {
            charFrequency.increaseCount(each);
        }
    }
}
