package ryuji.storage;

import ryuji.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and writing of CSV-like data files stored on the desktop.
 * <p>This class provides methods to read, write, and update task data stored in a file.
 * The file is used to persist tasks between application sessions. Tasks are read as CSV rows, and the
 * class ensures that the file is created if it doesn't exist.</p>
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file name.
     * <p>If the file does not already exist, it will be created on the user's desktop.</p>
     *
     * @param fileName the name of the file to store tasks
     */
    public Storage(String fileName) {
        File file = new File(fileName);

        String absolutePath = file.getAbsolutePath();

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

        this.filePath = absolutePath;
    }

    /**
     * Reads the file from the specified path and parses its contents into tasks.
     * <p>The file is assumed to have a CSV format, where each line represents a task. The tasks are returned
     * as a list of {@link Task} objects.</p>
     *
     * @return a list of tasks parsed from the file
     */
    public List<Task> readFile() {
        List<Task> rows = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.err.println("Sorry master but your file does not exist: " + filePath);
            return rows;
        }

        if (!file.canRead()) {
            System.err.println("Sorry master but I cannot read that file: " + filePath);
            return rows;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                try {
                    String[] values = line.split(",", 3);
                    Task task = Task.fromCsvRow(values);
                    rows.add(task);
                } catch (Exception e) {
                    System.err.println("Sorry master but I can't read this task:\n" + lineNum + ": " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Sorry master but I have trouble reading the file: " + e.getMessage());
        }

        return rows;
    }

    /**
     * Writes a task to the file in CSV format.
     * <p>The task is serialized as a CSV row and appended to the file.</p>
     *
     * @param task the task to be written to the file
     */
    public void writeTaskToFile(Task task) {
        String data = task.toCsvRow();
        writeToFile(data);
    }

    /**
     * Helper method to write a row to the file.
     * <p>This method is used to append a new line to the file.</p>
     *
     * @param row the row to be written to the file
     */
    private void writeToFile(String row) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(row);

            System.out.println("Row added successfully to " + filePath);

        } catch (IOException e) {
            System.err.println("Error adding row to CSV: " + e.getMessage());
        }
    }

    /**
     * Writes a row to a specified file.
     * <p>This method appends a row to the given file.</p>
     *
     * @param row  the row to be written to the file
     * @param path the path of the file to write to
     */
    public void writeToFile(String row, String path) {
        try (FileWriter fw = new FileWriter(path, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(row);

            System.out.println("Row added successfully to " + path);

        } catch (IOException e) {
            System.err.println("Error adding row to CSV: " + e.getMessage());
        }
    }

    /**
     * Creates a temporary file path for updating the CSV file.
     * <p>The temporary file is used to store updated data before replacing the original file.</p>
     *
     * @param filename the name of the file to generate the path for
     * @return the generated file path for the temporary file
     */
    private String makeFilePath(String filename) {
        String[] pathArray = filePath.split("/");
        ArrayList<String> pathArrayMutable = new ArrayList<>();

        for (String dir : pathArray) {
            pathArrayMutable.add(dir);
        }

        pathArrayMutable.remove(pathArrayMutable.size() - 1);
        pathArrayMutable.add("temp.csv");
        String pathTemp = String.join("/", pathArrayMutable);
        return pathTemp;
    }

    /**
     * Removes a task from the file at the specified position.
     * <p>This method reads the original file, skips the line at the given position, and writes all other lines
     * to a temporary file. The original file is then deleted, and the temporary file is renamed to replace it.</p>
     *
     * @param position the 1-based line number of the task to be removed
     * @throws IOException if an I/O error occurs during reading, writing, or file manipulation
     */
    public void removeTaskFromFile(int position) throws IOException {
        String currentLine;
        File originalFile = new File(filePath);

        String pathTemp = makeFilePath("temp.csv");
        File tempFile = new File(pathTemp);

        tempFile.createNewFile();
        BufferedReader reader = new BufferedReader(new FileReader(originalFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        int currentPosition = 1;

        while ((currentLine = reader.readLine()) != null) {
            if (currentPosition == position) {
                System.out.println("obj deleted");
                currentPosition++;
                continue;
            }
            writeToFile(currentLine, pathTemp);
            currentPosition++;
        }

        reader.close();
        writer.close();

        if (!originalFile.delete()) {
            System.err.println("Could not delete original file");
            return;
        }

        if (!tempFile.renameTo(originalFile)) {
            System.err.println("Could not rename temporary file");
        }
    }
}
