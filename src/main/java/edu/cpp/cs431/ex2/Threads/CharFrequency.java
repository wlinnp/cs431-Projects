package edu.cpp.cs431.ex2.Threads;

/**
 * @author waiphyo
 *         1/21/17.
 * CS-431 | EX-2
 * ADT to store character counts for a-z, A-Z, 0-9
 * <p></p>
 * Store them in array
 */
public class CharFrequency {
    private int[] allFrequency;
    private static final int ALL_CHAR_COUNT = 62; // a-z, A-Z, 0-9
    private static final int SMALL_CHAR_START = 97; //ascii for 'a'
    private static final int BIG_CHAR_START = 65; //ascii for 'A'
    private static final int DIGIT_START = 48; //ascii for '0'

    private static final int SMALL_CHAR_OFFSET = 0;//a-z stores in 0-25
    private static final int BIG_CHAR_OFFSET = 26;//A-Z stores in 26-51
    private static final int DIGIT_OFFSET = 52;//0-9 stores in 52-61

    public CharFrequency() {
        allFrequency = new int[ALL_CHAR_COUNT];
    }

    /**
     * check the category of input and update the cell accordingly
     *
     * @param input char
     */
    public void increaseCount(final char input) {
        if (input >= 'a' && input <= 'z') {
            increaseAtIndex(getActualValue(input, SMALL_CHAR_START), SMALL_CHAR_OFFSET);
        } else if (input >='A' && input <= 'Z') {
            increaseAtIndex(getActualValue(input, BIG_CHAR_START), BIG_CHAR_OFFSET);
        } else if (input >= '0' && input <= '9') {
            increaseAtIndex(getActualValue(input, DIGIT_START), DIGIT_OFFSET);
        }
    }

    /**
     * Convert ascii int to valid cell number
     *
     * @param input char
     * @param start int
     * @return int
     */
    private int getActualValue(char input, int start) {
        return (int)input - start;
    }

    /**
     * Calculate the correct cell and increase by 1.
     *
     * @param index int
     * @param offset int
     */
    private void increaseAtIndex(int index, int offset) {
        allFrequency[index + offset]++;
    }

    /**
     * Print all frequency
     *
     * @return String
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Char").append(General.TAB).append("Frequency").append(General.NEXT_LINE);
        result.append(printPortion(SMALL_CHAR_OFFSET, BIG_CHAR_OFFSET, SMALL_CHAR_START));
        result.append(printPortion(BIG_CHAR_OFFSET, DIGIT_OFFSET, BIG_CHAR_START));
        result.append(printPortion(DIGIT_OFFSET, ALL_CHAR_COUNT, DIGIT_START));
        return result.toString();
    }

    /**
     * Print certain portion of the array
     * @param startIndex int
     * @param endIndex int
     * @param offset int
     * @return StringBuilder
     */
    private StringBuilder printPortion(int startIndex, int endIndex, int offset) {
        StringBuilder result = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            result.append((char)(i - startIndex + offset)).append(General.TAB).append(allFrequency[i]).append(General.NEXT_LINE);
        }
        return result;
    }

    /**
     * return content of the index
     * @param index int
     * @return int
     */
    public int getCount(int index) {
        if (index < 0 || index >= ALL_CHAR_COUNT) {
            return -1;
        }
        return allFrequency[index];
    }
}
