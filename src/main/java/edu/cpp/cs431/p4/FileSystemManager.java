package edu.cpp.cs431.p4;

import java.beans.Customizer;
import java.util.*;

/**
 * @author waiphyo
 *         2/28/17.
 * CS-461  |  Project-4
 */
public class FileSystemManager {
    private static final int EMPTY_BLOCK = -1;
    private static final int HIGHEST_NUMBER = 64;
    private long bitMap;
    private int[] fat;
    private Map<String, INode> iNodeMap;

    public FileSystemManager() {
        bitMap = 0;
        fat = new int[HIGHEST_NUMBER];
        iNodeMap = new HashMap<String, INode>();
    }

    /**
     * Validate file name
     * Validate available spots
     * allocate new file
     * @param fileName new file name
     * @param size new file size
     * @return status
     */
    public boolean addFile(String fileName, int size) {
        if (iNodeMap.containsKey(fileName)) {
            System.out.println("File already exists");
            return false;
        }
        List<Integer> allocatedList = new ArrayList<Integer>();
        if (!getAllocation(allocatedList, size)) {
            System.out.println("Not enough space");
            return false;
        }
        allocateFile(allocatedList, new INode(fileName, size, allocatedList.get(0)));
        return true;
    }

    /**
     * Add new edu.cpp.cs431.p4.INode to the map
     * Mark indexes in bitmap
     * Update FAT array for indexes.
     * @param allocatedList List with indexes available for new file
     * @param iNode new edu.cpp.cs431.p4.INode for the file
     */
    private void allocateFile(List<Integer> allocatedList, INode iNode) {
        for (int i = 0; i < allocatedList.size() - 1; i++) {
            markAllocated(allocatedList.get(i));
            fat[allocatedList.get(i)] = allocatedList.get(i + 1);
        }
        markAllocated(allocatedList.get(allocatedList.size() - 1));
        fat[allocatedList.get(allocatedList.size() - 1)] = EMPTY_BLOCK;
        iNodeMap.put(iNode.getFileName(), iNode);
    }

    /**
     * Finding all available indexes for new file.
     * @param allocatedList Empty List to store indexes
     * @param size number of available indexes needed
     * @return whether there is enough available indexes.
     */
    private boolean getAllocation(List<Integer> allocatedList, int size) {
        int index = 0;
        for (int i = 0; i < HIGHEST_NUMBER; i++) {
            if (isAvailable(i) && index++ < size) {
                allocatedList.add(i);
            }
        }
        return index >= size;
    }

    /**
     * example:
     * bitmap = 1101011
     * Checking for index 3
     * bitmap = 110 ? 011
     *
     * get checking value 2^index = 2^3 = 8 = 1000
     *
     * bitmap & checking value => 1101011 & 0001000 = 1
     *
     * if it is 0, available, else NOT available
     * @param index to check in bitmap
     * @return status
     */
    private boolean isAvailable(int index) {
        return (bitMap & (getCorrectLong(index))) == 0;
    }

    /**
     * example
     * bitmap = 110100;
     * marking index 3
     * bitmap = 11=>0<=100;
     *
     * checking value = 2^index = 2^3 = 8 = 1000
     *
     * bitmap | checking value = 110100 | 001000 = 111100
     * @param index of array
     */
    private void markAllocated(int index) {
        bitMap |= (getCorrectLong(index));
    }

    /**
     * Example
     * bitmap = 1010110
     * marking index = 4
     * bitmap = 10=>1<=0110
     *
     * checking value = 2^index = 2^4 = 16 = 10000
     * fliping all bits = 111...11101111
     *
     * bitmap & checking value = 000...01010110 & 111..11101111 = 000...01000110
     * @param index of array
     */
    private void markAvailable(int index) {
        bitMap &= ~getCorrectLong(index);
    }

    /**
     * calculate correct long for bitwise operation
     *
     * 0 to 62 is normal. return long value of 2^index.
     *
     * for 63, it is sign bit. So 2^63 will give wrong answer.
     *
     * for 63, correct value is 8000 0000 0000 0000 which is Long.MIN_VALUE
     *
     * @param index 0 to 63
     * @return correct long value
     */
    private long getCorrectLong(int index) {
        return index == HIGHEST_NUMBER - 1 ? Long.MIN_VALUE : Double.valueOf(Math.pow(2.0, index)).longValue();
    }

    /**
     * example
     * bitmap = 10101101
     * finding index 4
     * bitmap = 101=>0<=1101
     * shift right 4 times
     * bitmap = 1010 = 10.
     *
     * removing preceding digits by ANDing with 1
     *
     * 1010 & 0001 = 0
     *
     * @param index of array
     * @return value at index is 1 or 0
     */
    private long getBitAtIndex(int index) {
        return (bitMap >> index) & 1;
    }

    /**
     * Validate file name
     * Remove from hash map
     * mark respective bitmap indexes to 0 / available
     * @param fileName
     * @return
     */
    public boolean removeFile(String fileName) {
        if (!iNodeMap.containsKey(fileName)) {
            System.out.println("File does not exist");
            return false;
        }
        INode removingFile = iNodeMap.remove(fileName);
        int startingBlock = removingFile.getStartingBlock();
        while (startingBlock != EMPTY_BLOCK) {
            markAvailable(startingBlock);
            startingBlock = fat[startingBlock];
        }
        return true;
    }

    /**
     * Printing BitMap in this style
     * 0    0 to 7 bit
     * 1    8 to 15 bit
     * 2    16 to 23 bit
     * .    .
     * .    .
     * .    .
     * 7    56 to 62 bit
     * @return Result String
     */
    public String printBitMap() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            result.append(i).append(Misc.TAB);
            for (int j = 0; j < 8; j++) {
                result.append(isAvailable(i * 8 + j) ? 0 : 1);
            }
            result.append(Misc.NEXT_LINE);
        }
        return result.toString();
    }

    /**
     * Print all hash map entries
     * and allocated blocks in linked list style
     * @return Result String
     */
    public String printINodes() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, INode> each : iNodeMap.entrySet()) {
            result.append(each.getKey()).append(Misc.COLON).append(Misc.TAB);
            int startIndex = each.getValue().getStartingBlock();
            while (startIndex != EMPTY_BLOCK) {
                result.append(startIndex).append(Misc.CONNECTOR);
                startIndex = fat[startIndex];
            }
            result.append(startIndex).append(Misc.NEXT_LINE);
        }
        return result.toString();
    }

    public String printFat() {
        return fat.toString();
    }

    public long getBitMap() {
        return bitMap;
    }
}
