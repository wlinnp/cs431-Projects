package edu.cpp.cs431.ex2;

import edu.cpp.cs431.ex2.Threads.CharFrequency;
import edu.cpp.cs431.ex2.Threads.CountThread;
import edu.cpp.cs431.ex2.Threads.General;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author waiphyo
 *         1/21/17.
 */
public class CountThreadTest {
    private BlockingQueue<String> queueTextContent = new LinkedBlockingQueue<String>();
    private BlockingQueue<CharFrequency> queueFrequency = new LinkedBlockingQueue<CharFrequency>();

    @Before
    public void init() {
        queueTextContent.add("abcdeABCDE123");
        queueTextContent.add(General.END_OF_FILE_STRING);
        queueTextContent.add("XYZxyz123 :");
        queueTextContent.add(General.END_OF_FILE_STRING);
        queueTextContent.add("ABCabc123DEFdefABC123  ...");
        queueTextContent.add(General.END_OF_FILE_STRING);
    }

    @Test
    public void CountThreadTest1() {
        Thread countThread = new Thread(new CountThread(queueTextContent, queueFrequency));
        countThread.start();
        try {
            Thread.sleep(2000);
            CharFrequency result = queueFrequency.take();
            System.out.println(result.toString());
            System.out.println("=================End of result=================");
            Assert.assertEquals(1, result.getCount(0));
            Assert.assertEquals(1, result.getCount(1));
            Assert.assertEquals(1, result.getCount(2));
            Assert.assertEquals(1, result.getCount(3));
            Assert.assertEquals(1, result.getCount(4));
            Assert.assertEquals(0, result.getCount(5));

            Assert.assertEquals(1, result.getCount(26));
            Assert.assertEquals(1, result.getCount(27));
            Assert.assertEquals(1, result.getCount(28));
            Assert.assertEquals(1, result.getCount(29));
            Assert.assertEquals(1, result.getCount(30));
            Assert.assertEquals(0, result.getCount(31));

            Assert.assertEquals(0, result.getCount(52));
            Assert.assertEquals(1, result.getCount(53));
            Assert.assertEquals(1, result.getCount(54));
            Assert.assertEquals(1, result.getCount(55));
            Assert.assertEquals(0, result.getCount(56));


            CharFrequency result1 = queueFrequency.take();
            System.out.println(result1.toString());
            System.out.println("=================End of result=================");
            Assert.assertEquals(0, result1.getCount(22));
            Assert.assertEquals(1, result1.getCount(23));
            Assert.assertEquals(1, result1.getCount(24));
            Assert.assertEquals(1, result1.getCount(25));
            Assert.assertEquals(0, result1.getCount(26));

            Assert.assertEquals(0, result1.getCount(48));
            Assert.assertEquals(1, result1.getCount(49));
            Assert.assertEquals(1, result1.getCount(50));
            Assert.assertEquals(1, result1.getCount(51));
            Assert.assertEquals(0, result1.getCount(52));

            Assert.assertEquals(1, result1.getCount(53));
            Assert.assertEquals(1, result1.getCount(54));
            Assert.assertEquals(1, result1.getCount(55));
            Assert.assertEquals(0, result1.getCount(56));

            CharFrequency result2 = queueFrequency.take();
            System.out.println(result2.toString());
            System.out.println("=================End of result=================");

            //        queueTextContent.add("ABCabc123DEFdefABC123  ...");
            Assert.assertEquals(1, result2.getCount(0));
            Assert.assertEquals(1, result2.getCount(1));
            Assert.assertEquals(1, result2.getCount(2));
            Assert.assertEquals(1, result2.getCount(3));
            Assert.assertEquals(1, result2.getCount(4));
            Assert.assertEquals(1, result2.getCount(5));
            Assert.assertEquals(0, result2.getCount(6));

            Assert.assertEquals(0, result2.getCount(25));
            Assert.assertEquals(2, result2.getCount(26));
            Assert.assertEquals(2, result2.getCount(27));
            Assert.assertEquals(2, result2.getCount(28));
            Assert.assertEquals(1, result2.getCount(29));
            Assert.assertEquals(1, result2.getCount(30));
            Assert.assertEquals(1, result2.getCount(31));
            Assert.assertEquals(0, result2.getCount(32));

            Assert.assertEquals(0, result2.getCount(52));
            Assert.assertEquals(2, result2.getCount(53));
            Assert.assertEquals(2, result2.getCount(54));
            Assert.assertEquals(2, result2.getCount(55));
            Assert.assertEquals(0, result2.getCount(56));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
