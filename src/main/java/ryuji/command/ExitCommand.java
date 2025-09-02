package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command that exits the application.
 * When executed, this command displays a farewell message to the user.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an {@code ExitCommand} with the given command keyword.
     *
     * @param command the command keyword (typically "bye")
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Executes the exit command by showing a farewell message to the user.
     * No changes are made to the task list or storage.
     *
     * @param tasks   the current task list (not used)
     * @param ui      the UI used to display the farewell message
     * @param storage the storage handler (not used)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
