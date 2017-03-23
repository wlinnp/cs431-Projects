package edu.cpp.cs431.p1.Commands;

import edu.cpp.cs431.p1.Misc.Generator;
import edu.cpp.cs431.p1.Process.ProcessTableEngine;

/**
 * @author waiphyo
 *         1/16/17.
 */
public class ExecveCommand implements ProcessCommand {
    private ProcessTableEngine processTableEngine;

    public ExecveCommand(ProcessTableEngine processTableEngine) {
        this.processTableEngine = processTableEngine;
    }

    public boolean operate(String args) throws CloneNotSupportedException {
        String[] separatedCommand = args.split(Generator.SPACE);
        if (separatedCommand.length >= 3) {
            processTableEngine.execveProcess(separatedCommand[1], separatedCommand[2]);
            return true;
        } else {
            return false;
        }
    }
}
