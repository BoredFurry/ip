package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to unmark a task (i.e., mark it as not done).
 * This command modifies the task's status and provides feedback via the UI.
 */
public class UnmarkCommand extends Command {

    /** The 1-based position of the task to be unmarked. */
    private final int position;

    /**
     * Constructs an {@code UnmarkCommand} with the given command string and task position.
     *
     * @param command  the command keyword (typically "unmark")
     * @param position the position of the task to unmark in the task list (1-based index)
     */
    public UnmarkCommand(String command, int position) {
        super(command);
        this.position = position;
    }

    /**
     * Executes the unmark command by updating the task list,
     * and displays a confirmation message to the user.
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the {@code Ui} used for displaying messages
     * @param storage the {@code Storage} responsible for data persistence
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(this.position);
        ui.showTaskUnmarked();
    }
}
