package ryuji.storage;

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

    public Storage(String fileName) {
        File file = new File(getFilePath(), fileName);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File '" + fileName + "' created successfully.");
                } else {
                    System.out.println("File '" + fileName + "' already exists.");
                }
            } catch (IOException e) {
                System.err.println("Error creating file '" + fileName + "': " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("File '" + fileName + "' already exists.");
        }

        this.filePath = fileName;
    }

    /**
     * Returns the path to the user's desktop directory.
     *
     * @return the file path as a {@code String}.
     */
    private static String getFilePath() {
        String home = System.getProperty("user.home");
        return home + File.separator + "Downloads";
    }

    /**
     * Reads a file from the desktop and parses its contents into rows of values.
     * Each line is split by commas.
     *
     * @return a list of string arrays, where each array represents a row of values.
     */
    public List<Task> readFile() {
        List<Task> rows = new ArrayList<>();
        File file = new File(getFilePath(), filePath);

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
        File file = new File(getFilePath(), this.filePath);
        String data = task.toCsvRow();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(data);
            bw.newLine();
            System.out.println("Successfully wrote to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Updates a file by removing the line at the specified position.
     *
     * <p>This method reads the original file line by line and writes all lines
     * except the one at the specified position to a temporary file. After processing,
     * the original file is deleted and the temporary file is renamed to replace it.</p>
     *
     * @param position the 1-based line number to be removed from the file
     * @throws IOException if an I/O error occurs during reading, writing, or file manipulation
     */
    public void updateFile(int position) throws IOException{
        String currentLine;
        File originalFile = new File(filePath);
        File tempFile = new File("temp.csv");
        BufferedReader reader = new BufferedReader(new FileReader(originalFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        int currentPosition = 1;

        while ((currentLine = reader.readLine()) != null) {
            if (currentPosition != position) {
                writer.write(currentLine + System.lineSeparator());
            }
        }

        reader.close();
        writer.close();

        originalFile.delete();
        tempFile.renameTo(originalFile);
    }
}