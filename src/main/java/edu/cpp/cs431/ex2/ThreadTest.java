package edu.cpp.cs431.ex2;

import edu.cpp.cs431.ex2.Threads.CharFrequency;
import edu.cpp.cs431.ex2.Threads.CountThread;
import edu.cpp.cs431.ex2.Threads.IOThread;
import edu.cpp.cs431.ex2.Threads.ReadThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author waiphyo
 *         1/21/17.
 * CS-431 | EX-2
 * Main Class
 *
 * Added 3 text files alexander, cnut, khan for testing
 */
public class ThreadTest {
    public static void main(String[] args) {
        BlockingQueue<String> queueFileName = new LinkedBlockingQueue<String>();
        BlockingQueue<String> queueTextContent = new LinkedBlockingQueue<String>();
        BlockingQueue<CharFrequency> queueFrequency = new LinkedBlockingQueue<CharFrequency>();

        Thread ioThread = new Thread(new IOThread(queueFileName, queueFrequency));
        Thread readThread = new Thread(new ReadThread(queueFileName, queueTextContent));
        Thread countThread = new Thread(new CountThread(queueTextContent, queueFrequency));

        ioThread.start();
        readThread.start();
        countThread.start();
    }
}
