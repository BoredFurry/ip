package ryuji.storage; /**
 * Separates the input and determines what kind of
 * Initializes the chat list and UI, then starts interaction with the user.
 */
import ryuji.task.Task;

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
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
     * @return a list of string arrays, where each array represents a row of values.
     */
    public List<Task> readFile() {
        List<Task> rows = new ArrayList<>();
        File file = new File(getDesktopPath(), filePath);

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
                    Task task = Task.fromCsvRow(values);
                    rows.add(task);
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
     * @param task     the list of rows to write, each represented as a string array.
     */
    public void writeToFile(Task task) {
        File file = new File(getDesktopPath(), this.filePath);
        String data = task.toCsvRow();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(data);
            bw.newLine();
            System.out.println("Successfully wrote to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public void updateFile(int position) throws IOException{
        String currentLine;
        File originalFile = new File(filePath);
        File tempFile = new File("temp.csv");
        BufferedReader reader = new BufferedReader(new FileReader(originalFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        int currentPosition = 1;

        while ((currentLine = reader.readLine()) != null) {
            if (currentPosition != position) writer.write(currentLine + System.lineSeparator());
        }

        reader.close();
        writer.close();

        originalFile.delete();
        tempFile.renameTo(originalFile);
    }
}