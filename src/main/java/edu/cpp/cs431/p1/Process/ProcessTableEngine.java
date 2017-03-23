package edu.cpp.cs431.p1.Process;

/**
 * @author waiphyo
 * CS-431   |   Project-1
 */
public interface ProcessTableEngine {
    void forkProcess();

    void killProcess(int killedProcessID) throws CloneNotSupportedException;

    void print();

    void exitProcess() throws CloneNotSupportedException;

    void blockProcess() throws CloneNotSupportedException;

    void unblockProcess(int processID) throws CloneNotSupportedException;

    void yieldProcess() throws CloneNotSupportedException;

    void execveProcess(String newProgramName, String newUser) throws CloneNotSupportedException;
}
