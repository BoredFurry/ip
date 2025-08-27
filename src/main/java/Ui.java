import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions between the user and the chatbot.
 * Delegates task management to ChatList and persistence to DataHandler.
 */
public class Ui {
    private final String LOGO = " ____               _ _ \n"
            + "|  _ \\ _   _ _   _ (_|_)\n"
            + "| |_) | | | | | | || | |\n"
            + "|  _ <| |_| | |_| || | |\n"
            + "|_| \\_\\\\__, |\\__,_|/ |_|\n"
            + "       |___/     |__/   \n";
    private final String LINE = "_______________________________________________________";
    private final String GREETING = "Hello! I'm Ryuji\nWhat can I do for you?";
    private final String FAREWELL = "Bye. Hope to see you again soon!\n";
    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(this.LINE + "\n" + this.LOGO + "\n" + this.GREETING + "\n" + this.LINE);
    };

    public void showLine() {
        System.out.println(this.LINE);
    }

    public void showFarewell() {
        System.out.println(this.FAREWELL);
    }

    public void displayTaskList(TaskList tasks){
        System.out.println(tasks);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String exception) {
        System.out.println(new RyujiException(exception).getMessage());
    }

    public void showTaskAdded() {
        System.out.println("Item added to tasklist");
    }

    public void showTaskUnmarked() {
        System.out.println("Item marked as done");
    }

    public void showTaskMarked() {
        System.out.println("Item unmarked");
    }

    public void showFailuretoLoadTaskListError() {
        System.out.println("the file doesn't exist, here's an empty list");
    }
}
