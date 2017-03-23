package edu.cpp.cs431.ex2.Threads;

/**
 * @author waiphyo
 *         1/21/17.
 * CS-431 | EX-2
 */
public enum InputCommands {
    READ("read"),
    COUNT("counts"),
    EXIT("exit");

    private String commandName;

    private InputCommands(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
