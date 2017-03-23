package edu.cpp.cs431.ex2.Threads;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * @author waiphyo
 *         1/21/17.
 * CS-431 | EX-2
 */
public final class IOThread implements Runnable {
    private final BlockingQueue<String> queueFileName;
    private final BlockingQueue<CharFrequency> queueFrequency;
    private static final String ERROR_MSG = "Interrupted Frequency";
    private static final String EMPTY_MSG = "Empty Frequency";

    public IOThread(BlockingQueue<String> queueFileName, BlockingQueue<CharFrequency> queueFrequency) {
        this.queueFileName = queueFileName;
        this.queueFrequency = queueFrequency;
    }

    /**
     * Print the frequency.
     * Validations will print error message if error is validated.
     *
     * @return String
     */
    private String printFrequency() {
        if (queueFrequency.isEmpty()) {
            return EMPTY_MSG;
        }
        try {
            CharFrequency currentFreq = queueFrequency.take();
            return currentFreq.toString();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return ERROR_MSG;
    }

    /**
     * In infinite loop, ask for user input.
     */
    public void run() {
        System.out.println(General.WELCOME_MSG);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(General.PROMPT_MSG);
            String[] commandParts = scanner.nextLine().trim().split(General.SPACE);
            try {
                if (commandParts[0].equals(InputCommands.READ.getCommandName())) {
                    if (commandParts.length >= 2 && !commandParts[1].equals(General.EMPTY_TEXT)) {
                        queueFileName.put(commandParts[1]);
                    }
                } else if (commandParts[0].equals(InputCommands.COUNT.getCommandName())) {
                    System.out.println(printFrequency());
                } else if (commandParts[0].equals(InputCommands.EXIT.getCommandName())) {
                    queueFileName.put(General.END_OF_PROGRAM_STRING);
                    break;
                } else {
                    System.out.println(General.INVALID_COMMAND_MSG);
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                break;
            }
        }
        System.out.println(getClass().getName() + General.QUIT_NOTIFICATION);
    }
}
