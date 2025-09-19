package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /** The 1-based position of the task to be deleted. */
    private final int position;

    /**
     * Constructs a {@code DeleteCommand} with the specified command and task position.
     *
     * @param command  the command keyword (typically "delete")
     * @param position the position of the task to delete (1-based index)
     */
    public DeleteCommand(String command, int position) {
        super(command);
        assert position > 0 : "use a number that is greater than 0";
        this.position = position;
    }

    /**
     * Executes the delete command by removing the specified task from the task list
     * and updating the storage file accordingly.
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the {@code Ui} used for user interaction
     * @param storage the {@code Storage} responsible for saving task data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.deleteFromList(position);
        try {
            storage.removeTaskFromFile(position);
        } catch (Exception e) {
            return "There was an error trying to remove your task master: " + e.getMessage();
        }
        return message;
    }
}
