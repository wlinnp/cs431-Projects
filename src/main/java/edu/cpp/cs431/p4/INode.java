package edu.cpp.cs431.p4;

/**
 * @author waiphyo
 *         2/28/17.
 * CS-461  |  Project-4
 */
public class INode {
    private String fileName;
    private int size;
    private int startingBlock;

    public INode(String fileName, int size, int startingBlock) {
        this.fileName = fileName;
        this.size = size;
        this.startingBlock = startingBlock;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartingBlock() {
        return startingBlock;
    }

    public void setStartingBlock(int startingBlock) {
        this.startingBlock = startingBlock;
    }
}
