package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * This updates the task's status and provides visual confirmation to the user.
 */
public class MarkCommand extends Command {

    /** The 1-based position of the task to be marked as done. */
    private final int position;

    /**
     * Constructs a {@code MarkCommand} with the specified command string and task position.
     *
     * @param command  the command keyword (typically "mark")
     * @param position the position of the task to mark in the task list (1-based index)
     */
    public MarkCommand(String command, int position) {
        super(command);
        this.position = position;
    }

    /**
     * Executes the mark command by updating the task status,
     * and displays a confirmation message to the user.
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the {@code Ui} used for displaying messages
     * @param storage the {@code Storage} responsible for data persistence
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(this.position);
        ui.showTaskMarked();
    }
}
