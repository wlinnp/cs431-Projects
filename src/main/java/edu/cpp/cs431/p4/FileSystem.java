package edu.cpp.cs431.p4;

import java.util.Scanner;

/**
 * @author waiphyo
 *         2/28/17.
 * CS-461  |  Project-4
 * User Interface Class
 */
public class FileSystem {
    public static void main(String[] args) {
        FileSystemManager fileSystemManager = new FileSystemManager();
        System.out.println("Enter commands for file system");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.startsWith("put")) {
                System.out.println(addFile(command, fileSystemManager) ? "succeeded" : "failed");
            } else if (command.startsWith("del")) {
                System.out.println(removeFile(command, fileSystemManager) ? "succeeded" : "failed");
            } else if (command.startsWith("bitmap")) {
                System.out.println(fileSystemManager.printBitMap());
            } else if (command.startsWith("inodes")) {
                System.out.println(fileSystemManager.printINodes());
            } else if (command.startsWith("exit()")) {
                System.out.println("End of Program. Bye Bye");
                break;
            } else {
                System.out.println("Invalid Command. Try again.");
            }
        }
        scanner.close();
    }

    /**
     * Validate add command.
     * Call Add function.
     * @param command complete command string
     * @param fileSystemManager current file system
     * @return status
     */
    private static boolean addFile(String command, FileSystemManager fileSystemManager) {
        String[] commandParts = command.split(Misc.SPACE);
        if (commandParts.length < 3 || !Misc.isPositiveInteger(commandParts[2])) {
            System.out.println("Invalid arguments");
            return false;
        }
        return fileSystemManager.addFile(commandParts[1], Integer.parseInt(commandParts[2]));
    }

    /**
     * Validate remove command.
     * Call remove function
     * @param command complete command string
     * @param fileSystemManager current file system
     * @return status
     */
    private static boolean removeFile(String command, FileSystemManager fileSystemManager) {
        String[] commandParts = command.split(Misc.SPACE);
        if (commandParts.length < 2) {
            System.out.println("Invalid arguments");
            return false;
        }
        return fileSystemManager.removeFile(commandParts[1]);
    }
}
