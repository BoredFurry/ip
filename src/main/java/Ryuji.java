import java.util.Scanner;

/**
 * Main class for the Ryuji chatbot application.
 * Handles user input and manages a task list stored in CSV format.
 */
public class Ryuji {

    private static final String LINE = "_______________________________________________________";
    private static final String CSV_FILE = "ryuji_tasks.csv";

    /**
     * Entry point of the Ryuji chatbot program.
     * Displays a welcome logo and handles user commands in a loop.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        printWelcomeLogo();

        Scanner scanner = new Scanner(System.in);
        ChatList chatList = new ChatList(CSV_FILE);
        Functions functions = new Functions();

        while (true) {
            String command = scanner.nextLine().trim().toLowerCase();
            System.out.println(LINE);

            if (command.equals("bye")) {
                System.out.println("\nThis fox will always be here for you.\n" + LINE);
                break;
            }

            if (command.equals("list")) {
                System.out.println(chatList);
                continue;
            }

            String[] splitCommand = command.split(" ", 2);
            String function = splitCommand[0];

            if (!functions.checkFunctionExists(function)) {
                System.out.println("Unknown command master. Please try again.");
                continue;
            }

            try {
                switch (function) {
                    case "mark" -> chatList.mark(Integer.parseInt(splitCommand[1]));
                    case "unmark" -> chatList.unmark(Integer.parseInt(splitCommand[1]));
                    case "todo" -> chatList.addToList(new ToDo(splitCommand[1]));
                    case "deadline" -> chatList.addToList(parseDeadline(splitCommand[1]));
                    case "event" -> chatList.addToList(parseEvent(splitCommand[1]));
                    case "delete" -> chatList.deleteFromList(splitCommand[1]);
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Please provide valid input for the command master.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

            System.out.println(LINE);
        }

        scanner.close();
    }

    /**
     * Prints the Ryuji ASCII logo and welcome message.
     */
    private static void printWelcomeLogo() {
        String logo =  " ____               _ _ \n"
                + "|  _ \\ _   _ _   _ (_|_)\n"
                + "| |_) | | | | | | || | |\n"
                + "|  _ <| |_| | |_| || | |\n"
                + "|_| \\_\\\\__, |\\__,_|/ |_|\n"
                + "       |___/     |__/   \n";
        System.out.println(LINE + "\n" + logo);
        System.out.println("Welcome to Ryuji chat bot. \nHow may I be of service today master?");
        System.out.println(LINE);
    }

    /**
     * Parses a deadline command string into a Deadline object.
     *
     * @param input the command string after "deadline"
     * @return a Deadline object
     */
    private static Deadline parseDeadline(String input) {
        String[] parts = input.split("/by", 2);
        if (parts.length < 2) throw new IllegalArgumentException("Deadline must have /by");
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses an event command string into an Event object.
     *
     * @param input the command string after "event"
     * @return an Event object
     */
    private static Event parseEvent(String input) {
        String[] parts = input.split("/at", 2);
        if (parts.length < 2) throw new IllegalArgumentException("Event must have /at");
        return new Event(parts[0].trim(), parts[1].trim());
    }
}