package edu.cpp.cs431.p1.Commands;

import edu.cpp.cs431.p1.Process.ProcessTableEngine;

/**
 * @author waiphyo
 *         1/16/17.
 */
public class ExitCommand implements ProcessCommand {
    private ProcessTableEngine processTableEngine;

    public ExitCommand(ProcessTableEngine processTableEngine) {
        this.processTableEngine = processTableEngine;
    }

    public boolean operate(String args) throws CloneNotSupportedException {
        processTableEngine.exitProcess();
        return true;
    }
}
