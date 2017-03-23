package edu.cpp.cs431.p2.OSProcess;

import edu.cpp.cs431.p2.Misc.General;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author waiphyo
 *         2/1/17.
 */
public class ProcessLoader {
    private String fileName;
    private List<OSProcess> allProcess;

    public ProcessLoader(String fileName) {
        this.fileName = fileName;
        allProcess = new ArrayList<OSProcess>();
    }

    /**
     * Get File content from file name
     * <p></p>
     * Validate process duplication
     * <p></p>
     * Add new process to list
     */
    public void extractProcessList() {
        Set<Integer> allProcessIDs = new HashSet<Integer>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                OSProcess eachProcess = extractProcess(currentLine);
                if (eachProcess == null || allProcessIDs.contains(eachProcess.getPid())) {
                    System.out.println("WARNING: corrupted or duplicated process");
                } else {
                    allProcessIDs.add(eachProcess.getPid());
                    allProcess.add(eachProcess);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            closeBufferReader(bufferedReader);
        }
    }

    /**
     * Format <process id>,<cycle>
     * <p></p>
     * split by comma
     * <p></p>
     * ensure format and data types are correct
     * <p></p>
     * @param currentLine each Line from File
     * @return created new process
     */
    private OSProcess extractProcess(String currentLine) {
        String[] splitArray = currentLine.trim().split(General.PROCESS_SEPERATOR);
        if (splitArray.length != 2 || !General.isInteger(splitArray[0]) || !General.isInteger(splitArray[1])) {
            return null;
        }
        return new OSProcess(Integer.parseInt(splitArray[0]), Integer.parseInt(splitArray[1]));
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

    /**
     * Reset all process' data
     * <p></p>
     * Re-order them based on IDs
     * <p></p>
     * The list may be reordered by different algorithms.
     * So re-ordering them based on IDs is necessary.
     * <p></p>
     * @return clean process List
     */
    public List<OSProcess> refresh() {
        for (OSProcess each : allProcess) {
            each.reset();
        }
        Collections.sort(allProcess, ((o1, o2) -> o1.getPid() < o2.getPid() ? -1 : 1));
        return allProcess;
    }
}
