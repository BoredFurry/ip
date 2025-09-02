package ryuji.ui;

import ryuji.task.TaskList;
import java.util.Scanner;

/**
 * The {@code Ui} class handles all interactions with the user.
 * It is responsible for displaying messages, reading input, and showing task-related outputs.
 */
public class Ui {

    private final String LOGO = " ____               _ _ \n"
            + "|  _ \\ _   _ _   _ (_|_)\n"
            + "| |_) | | | | | | || | |\n"
            + "|  _ <| |_| | |_| || | |\n"
            + "|_| \\_\\\\__, |\\__,_|/ |_|\n"
            + "       |___/     |__/   \n";
    private final String LINE = "_______________________________________________________";
    private final String GREETING = "Hello! I'm Ryuji.Ryuji\nWhat can I do for you?";
    private final String FAREWELL = "Bye. Hope to see you again soon!\n";

    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message and logo when the application starts.
     */
    public void showWelcome() {
        System.out.println(this.LINE + "\n" + this.LOGO + "\n" + this.GREETING + "\n" + this.LINE);
    }

    /**
     * Displays a horizontal divider line to separate console output.
     */
    public void showLine() {
        System.out.println(this.LINE);
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void showFarewell() {
        System.out.println(this.FAREWELL);
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks the {@code TaskList} containing all tasks to be displayed
     */
    public void displayTaskList(TaskList tasks) {
        System.out.println(tasks);
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return the user input as a {@code String}
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param exception the error message to be shown
     */
    public void showError(String exception) {
        System.out.println(new RyujiException(exception).getMessage());
    }

    /**
     * Displays a confirmation message when a task is added to the task list.
     */
    public void showTaskAdded() {
        System.out.println("Item added to tasklist");
    }

    /**
     * Displays a confirmation message when a task is unmarked (marked as not done).
     */
    public void showTaskUnmarked() {
        System.out.println("Item unmarked");
    }

    /**
     * Displays a confirmation message when a task is marked as done.
     */
    public void showTaskMarked() {
        System.out.println("Item marked as done");
    }

    /**
     * Displays an error message when the task list fails to load from file.
     * An empty list will be initialized in this case.
     */
    public void showFailuretoLoadTaskListError() {
        System.out.println("The file doesn't exist, here's an empty list");
    }
}
