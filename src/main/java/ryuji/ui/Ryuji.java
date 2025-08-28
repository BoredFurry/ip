package ryuji.ui;

import ryuji.command.Command;
import ryuji.storage.Storage;
import ryuji.task.TaskList;

/**
 * Main class for the Ryuji.Ryuji chatbot.
 * Initializes the chat list and UI, then starts interaction with the user.
 */
public class Ryuji {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ryuji(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.readFile());
        } catch (Exception e) {
            this.ui.showFailuretoLoadTaskListError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = this.parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showFarewell();
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Ryuji("data/tasks.csv").run();
    }
}
