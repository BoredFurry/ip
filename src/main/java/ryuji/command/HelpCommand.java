package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command that displays help information to the user.
 * This command is used to explain the different types of tasks (ToDo, Deadline, Event)
 * and the supported commands in the Ryuji task management system.
 *
 * When executed, it displays a guide containing examples of usage for each command.
 */
public class HelpCommand extends Command {

    /**
     * A formatted multi-line string that contains all the help instructions
     * and examples of commands that the user can use.
     */
    private final String HELPMESSAGE = "Welcome to RyujiCafe, here is the menu:\n" +
            "In this cafe, you can keep track of tasks to that you can focus on doing them\n" +
            "Types of tasks I can assist with:\n" +
            "ToDos\n" +
            "    type: todo make coffee\n" +
            "    result: [T][ ] make coffee\n\n" +
            "Deadlines\n" +
            "    type: deadline return book /by 2025-09-15 1600\n" +
            "    result: [D][ ] return book (by 2025-09-15 1600)\n\n" +
            "Events\n" +
            "    type: event JMOF /from 2026-01-09 /to 2026-01-11\n" +
            "    result: [E][ ] JMOF (from 2026-01-09 to 2026-01-11)\n\n" +
            "Commands to help you with your list of tasks:\n" +
            "Mark\n" +
            "    type: mark 1\n" +
            "    result: [T][ ] return book -> [T][X] return book\n\n" +
            "Unmark\n" +
            "    type: unmark 1\n" +
            "    result: [T][X] return book -> [T][ ] return book\n\n" +
            "List\n" +
            "    type: list\n" +
            "     result:\n" +
            "    1. [T][ ] make coffee\n" +
            "    2. [D][ ] return book (by 2025-09-15 1600)\n" +
            "    3. [E][ ] JMOF (from 2026-01-09 to 2026-01-11)\n\n" +
            "Delete\n" +
            "    type delete 1\n" +
            "    result: first item will be removed from the list\n\n" +
            "Find\n" +
            "    type: find book\n" +
            "    result: 1. [D] return book (by 2025-09-15)\n\n" +
            "Exit\n" +
            "    type: exit\n" +
            "    result: ends the chat with Ryuji\n\n" +
            "Go ahead and try some of these commands";

    /**
     * Constructs a HelpCommand with the given raw command string.
     *
     * @param command The raw user input that triggered this command.
     */
    public HelpCommand(String command) {
        super(command);
    }

    /**
     * Executes the help command by displaying the help message through the UI.
     * This method does not modify the task list or the storage.
     *
     * @param tasks   The current task list (unused in this command).
     * @param ui      The UI instance used to display messages to the user.
     * @param storage The storage instance (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return HELPMESSAGE;
    }
}
