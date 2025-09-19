package ryuji.ui;

import ryuji.command.Command;
import ryuji.storage.Storage;
import ryuji.task.TaskList;

/**
 * The {@code Ryuji} class serves as the main entry point for the Ryuji chatbot application.
 * It is responsible for initializing the user interface, task list, storage, and parser.
 * The chatbot reads user input in a loop, parses commands, and executes them accordingly.
 */
public class Ryuji {
    private Parser parser = new Parser();
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    private String response;

    /**
     * Constructs a {@code Ryuji} instance with the specified file path for storage.
     * It attempts to load existing tasks from the given file. If loading fails,
     * it initializes an empty task list and notifies the user.
     *
     * @param filePath the file path to the storage file where tasks are saved
     */
    public Ryuji(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.readFile());
        } catch (Exception e) {
            this.ui.showFailuretoLoadTaskListError();
            this.tasks = new TaskList();
        }
    }

    public Ryuji() {
        this.storage = new Storage("tasks.csv");
        try {
            this.tasks = new TaskList(this.storage.readFile());
        } catch (Exception e) {
            this.ui.showFailuretoLoadTaskListError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot application.
     * Displays a welcome message, then enters a loop to continuously read, parse,
     * and execute user commands until the exit command is received.
     * Shows error messages and a farewell message as appropriate.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                response = c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return response = c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return response = ui.showError(e.getMessage());
        }
    }

    /**
     * Main method that launches the Ryuji chatbot application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Ryuji("tasks.csv").run();
    }
}
