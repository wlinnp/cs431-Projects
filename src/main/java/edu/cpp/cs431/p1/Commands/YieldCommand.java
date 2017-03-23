package edu.cpp.cs431.p1.Commands;

import edu.cpp.cs431.p1.Process.ProcessTableEngine;

/**
 * @author waiphyo
 *         1/16/17.
 */
public class YieldCommand implements ProcessCommand {
    private ProcessTableEngine processTableEngine;

    public YieldCommand(ProcessTableEngine processTableEngine) {
        this.processTableEngine = processTableEngine;
    }

    public boolean operate(String args) throws CloneNotSupportedException {
        processTableEngine.yieldProcess();
        return true;
    }
}
