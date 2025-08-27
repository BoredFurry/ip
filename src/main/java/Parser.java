import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

/**
 * Separates the input and determines what kind of
 * Initializes the chat list and UI, then starts interaction with the user.
 */
public class Parser{
    private final Set<String> commands = Set.of("list", "bye", "mark", "unmark", "todo", "deadline", "event", "delete");
    public Command parse(String input) {
        Command command;
        String commandString = this.parseCommand(input);
        switch (commandString) {
        case "list":
            command = new ListCommand(commandString);
            break;
        case "bye":
            command = new ExitCommand(commandString);
            break;
        case "mark":
            command = new MarkCommand(commandString, this.parsePosition(input));
            break;
        case "unmark":
            command = new UnmarkCommand(commandString, this.parsePosition(input));
            break;
        case "delete":
            command = new DeleteCommand(commandString, this.parsePosition(input));
            break;
        case "todo":
            command = new AddCommand(commandString, new ToDo(this.parseTask(input)));
            break;
        case "deadline":
            command = new AddCommand(commandString, new Deadline(this.parseTask(input)));
            break;
        case "event":
            command = new AddCommand(commandString,new Event(this.parseTask(input)));
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandString);
        }
        if (this.checkCommandExists(commandString)) {
            return null;
        } else {
            return command;
        }
    }

    public boolean checkCommandExists(String command) {
        return commands.contains(command);
    }

    private String parseCommand(String input) {
        String[] inputSplit = input.split(" ", 2);
        return inputSplit[0];
    }

    private String parseTask(String input) {
    String[] inputSplit = input.split(" ", 2);
    return inputSplit[1];
    }

    private int parsePosition(String input) {
        String[] inputSplit = input.split(" ", 2);
        return Integer.parseInt(inputSplit[1]);
    }



}
