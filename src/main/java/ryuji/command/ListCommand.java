package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to display the full list of tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand} with the specified command keyword.
     *
     * @param command the command keyword (typically "list")
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the list command by displaying all tasks in the current {@code TaskList}.
     *
     * @param tasks   the current task list
     * @param ui      the UI used to interact with the user
     * @param storage the storage handler (not used in this command)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayTaskList(tasks);
    }
}
