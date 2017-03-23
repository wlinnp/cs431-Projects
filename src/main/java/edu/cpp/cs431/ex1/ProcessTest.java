import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Wai Phyo
 * Cs-431 | Ex-1
 *
 * Create Process with process Builder.
 * Print outputs.
 * PocessBuilder requires command.
 */
public class ProcessTest {
    private static final String NEXT_LINE = "\n";

    /**
     * Checks argument array for possible commands.
     *
     * @param args can have command and arguments
     * @throws IOException to capture exceptions
     *
     */
    public static void main(String[] args) throws IOException {
        System.out.println(startProcess("java"));
    }

    /**
     * Run sub process from parameter with Process Builder
     * <p></p>
     * Print output string
     * <p></p>
     * Print error if doesn't exist.
     * @param command sub process name example: date
     * @return the output result / error
     * @throws IOException to capture exceptions
     */
    private static String startProcess(final String command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        StringBuilder result = bufferedToString(new BufferedReader(
                new InputStreamReader(process.getInputStream())));

        if (result.toString().equals("")) {
            result = bufferedToString(new BufferedReader(
                    new InputStreamReader(process.getErrorStream())));
        }
        return result.toString();
    }

    /**
     * Loop all lines in buffer to StringBuilder
     * @param bufferedReader buffered text
     * @return StringBuilder
     * @throws IOException to capture exceptions
     */
    private static StringBuilder bufferedToString(final BufferedReader bufferedReader) throws IOException {
        StringBuilder result = new StringBuilder();
        String eachLine;
        while ((eachLine = bufferedReader.readLine()) != null) {
            result.append(eachLine).append(NEXT_LINE);
        }
        return result;
    }
}
