package edu.cpp.cs431.p1.Commands;

import edu.cpp.cs431.p1.Misc.Generator;
import edu.cpp.cs431.p1.Process.ProcessTableEngine;

/**
 * @author waiphyo
 *         1/16/17.
 */
public class UnblockCommand implements ProcessCommand {
    private ProcessTableEngine processTableEngine;

    public UnblockCommand(ProcessTableEngine processTableEngine) {
        this.processTableEngine = processTableEngine;
    }

    public boolean operate(String args) throws CloneNotSupportedException {
        int processId = new Generator().getInteger(args);
        if (processId != -1) {
            processTableEngine.unblockProcess(processId);
            return true;
        } else {
            return false;
        }
    }
}
