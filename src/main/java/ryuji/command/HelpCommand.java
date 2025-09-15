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
    private final String HELPMESSAGE = """
            Welcome to RyujiCafe, here is the menu:
            In this cafe, you can keep track of tasks to that you can focus on doing them
            Types of tasks I can assist with:
            ToDos
                type: todo make coffee
                result: [T][ ] make coffee
            
            Deadlines
                type: deadline return book /by 2025-09-15 1600
                result: [D][ ] return book (by 2025-09-15 1600)
            
            Events
                type: event JMOF /from 2026-01-09 /to 2026-01-11
                result: [E][ ] JMOF (from 2026-01-09 to 2026-01-11)
            
            Commands to help you with your list of tasks:
            Mark
                type: mark 1
                result: [T][ ] return book -> [T][X] return book
            
            Unmark
                type: unmark 1
                result: [T][X] return book -> [T][ ] return book
            
            List
                type: list
                 result:
                1. [T][ ] make coffee
                2. [D][ ] return book (by 2025-09-15 1600)
                3. [E][ ] JMOF (from 2026-01-09 to 2026-01-11)
            
            Delete
                type delete 1
                result: first item will be removed from the list
            
            Find
                type: find book
                result: 1. [D] return book (by 2025-09-15)
            
            Exit
                type: exit
                result: ends the chat with Ryuji
            
            Go ahead and try some of these commands
            """;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp(HELPMESSAGE);
    }
}
