package edu.cpp.cs431.p3.Misc;

import java.util.Random;

/**
 * @author waiphyo
 *         2/1/17.
 */
public class General {
    public static final String NEXT_LINE = "\n";
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String PROCESS_SEPERATOR = ",";
    public static final String LINE_SEPERATOR = "\n===========================\n";
    public static final Random RANDOM = new Random();
    public static boolean isInteger(final String input) {
        return input.matches("\\d+");
    }
}
