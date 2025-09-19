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
 */
public class Storage {

    private final String filePath;

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
     * Reads a file from the desktop and parses its contents into rows of values.
     * Each line is split by commas.
     *
     * @return a list of string arrays, where each array represents a row of values.
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
     * Writes the given data to a file on the desktop. Each row is written as a
     * comma-separated line.
     *
     * @param task     the list of rows to write, each represented as a string array.
     */
    public void writeTaskToFile(Task task) {
        String data = task.toCsvRow();
        writeToFile(data);
    }

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
     * Updates a file by removing the line at the specified position.
     *
     * <p>This method reads the original file line by line and writes all lines
     * except the one at the specified position to a temporary file. After processing,
     * the original file is deleted and the temporary file is renamed to replace it.</p>
     *
     * @param position the 1-based line number to be removed from the file
     * @throws IOException if an I/O error occurs during reading, writing, or file manipulation
     */
    public void removeTaskFromFile(int position) throws IOException{
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