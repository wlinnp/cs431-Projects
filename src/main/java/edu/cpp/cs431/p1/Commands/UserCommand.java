package edu.cpp.cs431.p1.Commands;

/**
 * @author Wai Phyo
 * CS-431   |   Project-1
 * <p></p>
 * enumeration for user commmands.
 */
public enum UserCommand {
    FORK("fork"),
    KILL("kill"),
    PRINT("print"),
    YIELD("yield"),
    EXIT("exit"),
    BLOCKED("block"),
    UNBLOCK("unblock"),
    QUIT("exit()"),
    EXECVE("execve");

    private String userCommand;

    private UserCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    public String getUserCommand() {
        return userCommand;
    }
}
