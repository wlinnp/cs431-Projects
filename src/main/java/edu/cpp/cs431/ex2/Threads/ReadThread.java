package edu.cpp.cs431.ex2.Threads;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * @author waiphyo
 *         1/21/17.
 * CS-431 | EX-2
 */
public final class ReadThread implements Runnable {
    private final BlockingQueue<String> queueFileName;
    private final BlockingQueue<String> queueTextContent;

    public ReadThread(BlockingQueue<String> queueFileName, BlockingQueue<String> queueTextContent) {
        this.queueFileName = queueFileName;
        this.queueTextContent = queueTextContent;
    }

    /**
     * Find file.
     * Read file and store into String
     */
    public void run() {
        while (true) {
            BufferedReader bufferedReader = null;
            try {
                String fileName = queueFileName.take();
                if (endOfThreadCheck(fileName)) {
                    break;
                }
                bufferedReader = new BufferedReader(new FileReader(fileName));
                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    queueTextContent.put(currentLine);
                }
                queueTextContent.put(General.END_OF_FILE_STRING);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                closeBufferReader(bufferedReader);
            }
        }
        System.out.println(getClass().getName() + General.QUIT_NOTIFICATION);

    }

    private boolean endOfThreadCheck(String fileName) {
        try {
            if (fileName.equals(General.END_OF_PROGRAM_STRING)) {
                queueTextContent.put(General.END_OF_PROGRAM_STRING);
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            return false;
        }
    }

    /**
     * Close buffer when reading is done
     * @param bufferedReader BufferedReader
     */
    private void closeBufferReader(BufferedReader bufferedReader) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
