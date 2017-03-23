package edu.cpp.cs431.p4;

import java.util.Random;

/**
 * @author waiphyo
 *         2/1/17.
 */
public class Misc {
    public static final String NEXT_LINE = "\n";
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String PROCESS_SEPERATOR = ",";
    public static final String LINE_SEPERATOR = "\n===========================\n";
    public static final String COLON = ":";
    public static final String CONNECTOR = " -> ";
    public static final Random RANDOM = new Random();
    public static boolean isPositiveInteger(final String input) {
        return input.matches("\\d+");
    }
}
