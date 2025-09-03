package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Abstract base class for all commands in the Ryuji chatbot application.
 * Each command represents an action triggered by user input.
 */
public abstract class Command {

    /** The command keyword string (e.g., "todo", "mark", "bye", "find"). */
    private final String command;

    /**
     * Constructs a Command with the given command keyword.
     *
     * @param command the command keyword string
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Returns the command keyword string.
     *
     * @return the command string
     */
    public String getCommand() {
        return command;
    }

    /**
     * Indicates whether this command signals the application to exit.
     * By default, this returns true if the command string equals "bye".
     * Subclasses may override this behavior if needed.
     *
     * @return true if this command is an exit command, false otherwise
     */
    public boolean isExit() {
        return this.getCommand().equals("bye");
    }

    /**
     * Executes the command, performing its intended action.
     * Implementing subclasses must define this method.
     *
     * @param tasks   the task list to be manipulated
     * @param ui      the UI component for interaction with the user
     * @param storage the storage component for persistent data management
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns the string representation of this command.
     *
     * @return the command string
     */
    @Override
    public String toString() {
        return command;
    }
}
