package edu.cpp.cs431.p1.Commands;

/**
 * @author waiphyo
 *         1/16/17.
 */
public interface ProcessCommand {
    boolean operate(String args) throws CloneNotSupportedException;
}
