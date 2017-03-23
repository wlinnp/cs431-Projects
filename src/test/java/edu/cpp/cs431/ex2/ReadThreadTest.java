package edu.cpp.cs431.ex2;

import edu.cpp.cs431.ex2.Threads.ReadThread;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author waiphyo
 *         1/21/17.
 */
public class ReadThreadTest {
    private BlockingQueue<String> queueFileName = new LinkedBlockingQueue<String>();
    private BlockingQueue<String> queueTextContent = new LinkedBlockingQueue<String>();

    @Test
    public void ReadThreadTest1() {
        queueFileName.add("cnut");
        Thread readThread = new Thread(new ReadThread(queueFileName, queueTextContent));
        readThread.start();
        try {
            Thread.sleep(2000);
            String result = queueTextContent.take();
            System.out.println(result);
            Assert.assertEquals("King Cnut the Great", result.substring(0, 19));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

    @Test
    public void ReadThreadTest2() {
        queueFileName.add("alexander");
        Thread readThread = new Thread(new ReadThread(queueFileName, queueTextContent));
        readThread.start();
        try {
            Thread.sleep(2000);
            String result = queueTextContent.take();
            System.out.println(result);
            Assert.assertEquals("Alexander III of Ma", result.substring(0, 19));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

    @Test
    public void ReadThreadTest3() {
        queueFileName.add("khan");
        Thread readThread = new Thread(new ReadThread(queueFileName, queueTextContent));
        readThread.start();
        try {
            Thread.sleep(2000);
            String result = queueTextContent.take();
            System.out.println(result);
            Assert.assertEquals("Genghis Khan  c. 11", result.substring(0, 19));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }
}
