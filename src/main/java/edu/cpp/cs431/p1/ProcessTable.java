package edu.cpp.cs431.p1;

import edu.cpp.cs431.p1.Commands.*;
import edu.cpp.cs431.p1.Misc.Generator;
import edu.cpp.cs431.p1.Process.ProcessTableEngine;
import edu.cpp.cs431.p1.Process.ProcessTableEngineDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * @author Wai Phyo
 * CS-431   |   Project-1
 * <p></p>
 * Main Driver for Process Management Simulation Project
 */
public class ProcessTable {
    private static final String COMMAND_NOT_FOUND = "Invalid command, try again.";
    private static ProcessTableEngine processTableEngine;
    private static Map<String, ProcessCommand> mapSimpleProcessCommand = new HashMap<String, ProcessCommand>();
    public static void main(String[] args) throws CloneNotSupportedException {
        processTableEngine = new ProcessTableEngineDetails();
        Scanner scanner = new Scanner(System.in);
        String userCommand;
        fillCommandsInMap();
        while (true) {
            System.out.println("Enter commands. Enter \"exit()\" to exit the program");
            userCommand = scanner.nextLine().trim().toLowerCase();
            if (processCommand(userCommand)) {
                break;
            }
        }
    }

    /**
     * Fill Hash Maps with respective commands
     */
    private static void fillCommandsInMap() {
        mapSimpleProcessCommand.put(UserCommand.FORK.getUserCommand(), new ForkCommand(processTableEngine));
        mapSimpleProcessCommand.put(UserCommand.PRINT.getUserCommand(), new PrintCommand(processTableEngine));
        mapSimpleProcessCommand.put(UserCommand.BLOCKED.getUserCommand(), new BlockCommand(processTableEngine));
        mapSimpleProcessCommand.put(UserCommand.YIELD.getUserCommand(), new YieldCommand(processTableEngine));
        mapSimpleProcessCommand.put(UserCommand.EXIT.getUserCommand(), new ExitCommand(processTableEngine));
        mapSimpleProcessCommand.put(UserCommand.KILL.getUserCommand(), new KillCommand(processTableEngine));
        mapSimpleProcessCommand.put(UserCommand.UNBLOCK.getUserCommand(), new UnblockCommand(processTableEngine));
        mapSimpleProcessCommand.put(UserCommand.EXECVE.getUserCommand(), new ExecveCommand(processTableEngine));

    }

    /**
     *
     * @param userCommand user input
     * @return result whether to quit the program
     * @throws CloneNotSupportedException for clone process content to CPU
     */
    private static boolean processCommand(final String userCommand) throws CloneNotSupportedException {
        String mainCommand = userCommand.split(Generator.SPACE)[0];

        if (mapSimpleProcessCommand.get(mainCommand) != null) {
            if (!mapSimpleProcessCommand.get(mainCommand).operate(userCommand)) {
                System.out.println(COMMAND_NOT_FOUND);
            }
        } else if (mainCommand.equals(UserCommand.QUIT.getUserCommand())) {
            System.out.println("Exiting. Good day!");
            return true;
        } else {
            System.out.println(COMMAND_NOT_FOUND);
        }
        return false;
    }
}
