package edu.cpp.cs431.p1.Misc;

import java.util.Random;

/**
 * @author Wai Phyo
 * CS-431   |   Project-1
 * <p></p>
 * Helper Functions
 */
public class Generator {
    public static final String TAB = "\t";
    public static final String NEXT_LINE = "\n";
    public static final String SPACE = " ";

    private Random random = new Random();

    public int getRandomInteger() {
        return random.nextInt();
    }

    public int getRandomPositiveInteger() {
        return Math.abs(random.nextInt());
    }

    public String printHexString(final int input) {
        return "0x" + Integer.toHexString(input);
    }

    public int getRandomIntegerWithBounds(final int lowerBound, final int upperBound) {
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public boolean isInteger(final String input) {
        return input.matches("\\d+");
    }

    /**
     * Check if process id is valid.
     *
     * @param command User command [Command] [ProcessID]
     * @return process id
     */
    public int getInteger(final String command) {
        String[] separatedCommand = command.split(Generator.SPACE);
        if (separatedCommand.length >= 2 && isInteger(separatedCommand[1])) {
            return Integer.parseInt(separatedCommand[1]);
        } else {
            return -1;
        }
    }
}
