import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and writing of CSV-like data files stored on the desktop.
 */
public class DataHandler {

    /**
     * Returns the path to the user's desktop directory.
     *
     * @return the desktop path as a {@code String}.
     */
    private static String getDesktopPath() {
        String home = System.getProperty("user.home");
        return home + File.separator + "Desktop";
    }

    /**
     * Reads a file from the desktop and parses its contents into rows of values.
     * Each line is split by commas.
     *
     * @param filename name of the file to read.
     * @return a list of string arrays, where each array represents a row of values.
     */
    public List<String[]> readFile(String filename) {
        List<String[]> rows = new ArrayList<>();
        File file = new File(getDesktopPath(), filename);

        if (!file.exists()) {
            System.err.println("Error: File does not exist: " + file.getAbsolutePath());
            return rows;
        }

        if (!file.canRead()) {
            System.err.println("Error: Cannot read file: " + file.getAbsolutePath());
            return rows;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                try {
                    String[] values = line.split(",");
                    rows.add(values);
                } catch (Exception e) {
                    System.err.println("Warning: Error parsing line " + lineNum + ": " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return rows;
    }

    /**
     * Writes the given data to a file on the desktop. Each row is written as a
     * comma-separated line.
     *
     * @param filename name of the file to write.
     * @param data     the list of rows to write, each represented as a string array.
     */
    public void writeToFile(String filename, List<String[]> data) {
        File file = new File(getDesktopPath(), filename);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String[] row : data) {
                String line = String.join(",", row);
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Successfully wrote to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}