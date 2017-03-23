package edu.cpp.cs431.p3;

import edu.cpp.cs431.p3.Memory.Allocation.*;
import edu.cpp.cs431.p3.Memory.MemoryList;
import edu.cpp.cs431.p3.Misc.General;

import java.util.Scanner;

/**
 * @author waiphyo
 *         2/14/17.
 * CS-431 | Project-3
 */
public class SwapTest {
    public static void main(String[] args) {
        MemoryList memoryList = new MemoryList(100);
        System.out.println("Beginning simulation. You have a memory with size 100.\nEnter commands");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] splitCommands = scanner.nextLine().split(General.SPACE);
            if (splitCommands[0].startsWith("add")) {
                if (validateTwoIntegers(splitCommands)) {
                    memoryList.addJobs(Integer.parseInt(splitCommands[1]),
                            Integer.parseInt(splitCommands[2]));
                }
            } else if (splitCommands[0].startsWith("jobs")) {
                System.out.println(memoryList.getJobs());
            } else if (splitCommands[0].startsWith("list")) {
                System.out.println(memoryList.toString());
            } else if (splitCommands[0].startsWith("ff")) {
                allocatingMethods(memoryList, splitCommands, new FirstFit());
            } else if (splitCommands[0].startsWith("nf")) {
                allocatingMethods(memoryList, splitCommands, new NextFit());
            } else if (splitCommands[0].startsWith("bf")) {
                allocatingMethods(memoryList, splitCommands, new BestFit());
            } else if (splitCommands[0].startsWith("wf")) {
                allocatingMethods(memoryList, splitCommands, new WorstFit());
            } else if (splitCommands[0].startsWith("de")) {
                if (validateOneIntegers(splitCommands)) {
                    System.out.println(memoryList.deAllocate(Integer.parseInt(splitCommands[1])));
                }
            } else if (splitCommands[0].startsWith("exit")) {
                System.out.println("End of program. Good Bye.");
                break;
            } else {
                System.out.println("Invalid command. Try again.");
            }
        }
        scanner.close();
    }

    /**
     * Checking valid commands.
     * Array should have length 3
     * 2nd and 3rd cells are integers
     * @param splitCommands String array
     * @return true / false
     */
    private static boolean validateTwoIntegers(String[] splitCommands) {
        if ( splitCommands.length == 3 &&
                General.isInteger(splitCommands[1]) &&
                General.isInteger(splitCommands[2])) {
            return true;
        } else {
            System.out.println("Incorrect command structure");
            return false;
        }
    }

    /**
     * Checking valid commands.
     * Array should have length 2
     * 2nd cell is integer
     * @param splitCommands String array
     * @return true / false
     */
    private static boolean validateOneIntegers(String[] splitCommands) {
        return splitCommands.length == 2 &&
                General.isInteger(splitCommands[1]);
    }

    /**
     * Allocating jobs
     * @param memoryList Memory Management Unit Engine
     * @param splitCommands commands
     * @param allocates allocation concrete method
     */
    private static void allocatingMethods(MemoryList memoryList, String[] splitCommands, Allocates allocates) {
        if (validateOneIntegers(splitCommands)) {
            System.out.println(memoryList.allocate(Integer.parseInt(splitCommands[1]),
                    allocates));
        }
    }

}
