package ryuji.ui;

import ryuji.command.AddCommand;
import ryuji.command.Command;
import ryuji.command.DeleteCommand;
import ryuji.command.ExitCommand;
import ryuji.command.FindCommand;
import ryuji.command.HelpCommand;
import ryuji.command.ListCommand;
import ryuji.command.MarkCommand;
import ryuji.command.UnmarkCommand;
import ryuji.task.Deadline;
import ryuji.task.Event;
import ryuji.task.ToDo;

import java.util.Set;

/**
 * The {@code Parser} class is responsible for interpreting user input
 * and converting it into executable {@link Command} objects.
 * It supports various commands such as "list", "todo", "deadline", "event",
 * "mark", "unmark", "delete", "find", "help" and "bye".
 */
public class Parser {

    private final Set<String> commands = Set.of("list", "bye", "mark", "unmark", "todo",
            "find", "help", "deadline", "event", "delete");

    /**
     * Parses the raw user input and returns the corresponding {@link Command} object.
     *
     * @param input the user input string
     * @return the corresponding {@code Command} object, or {@code null} if the command is not recognized
     * @throws IllegalStateException if the command keyword is unexpected
     * @throws IndexOutOfBoundsException or NumberFormatException if parsing fails
     */
    public Command parse(String input) {
        Command command;
        String commandString = parseCommand(input.toLowerCase());
        switch (commandString) {
        case "list":
            command = new ListCommand(commandString);
            break;
        case "bye":
            command = new ExitCommand(commandString);
            break;
        case "mark":
            command = new MarkCommand(commandString, parsePosition(input));
            break;
        case "unmark":
            command = new UnmarkCommand(commandString, parsePosition(input));
            break;
        case "delete":
            command = new DeleteCommand(commandString, parsePosition(input));
            break;
        case "todo":
            command = new AddCommand(commandString, new ToDo(parseTask(input)));
            break;
        case "deadline":
            command = new AddCommand(commandString, new Deadline(parseTask(input)));
            break;
        case "event":
            command = new AddCommand(commandString, new Event(parseTask(input)));
            break;
        case "find":
            command = new FindCommand(commandString, parseTask(input));
            break;
        case "help":
            command = new HelpCommand(commandString);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + commandString);
        }

        if (this.checkCommandExists(commandString)) {
            return command;
        } else {
            return null;
        }
    }

    /**
     * Checks whether a given command keyword exists in the list of valid commands.
     *
     * @param command the command keyword to check
     * @return {@code true} if the command exists, {@code false} otherwise
     */
    public boolean checkCommandExists(String command) {
        return commands.contains(command);
    }

    /**
     * Extracts the command keyword from the full user input.
     *
     * @param input the full input string
     * @return the command keyword (first word in the input)
     */
    private String parseCommand(String input) {
        String[] inputSplit = input.split(" ", 2);
        return inputSplit[0];
    }

    /**
     * Extracts the task description from the user input.
     * Assumes the command is followed by a space and the task details.
     *
     * @param input the full input string
     * @return the task description or details
     * @throws ArrayIndexOutOfBoundsException if no task details are provided
     */
    private String parseTask(String input) {
        String[] inputSplit = input.split(" ", 2);
        return inputSplit[1];
    }

    /**
     * Extracts the position (typically task number) from the user input.
     * Used in commands like "mark", "unmark", and "delete".
     *
     * @param input the full input string
     * @return the task position as an integer
     * @throws NumberFormatException if the number is not valid
     * @throws ArrayIndexOutOfBoundsException if no number is provided
     */
    private int parsePosition(String input) {
        String[] inputSplit = input.split(" ", 2);
        return Integer.parseInt(inputSplit[1]);
    }
}
