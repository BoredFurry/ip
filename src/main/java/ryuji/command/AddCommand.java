package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.Task;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {

    /** The task to be added to the list. */
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the given command keyword and task.
     *
     * @param command the command keyword (e.g., "todo", "deadline", "event")
     * @param task    the task to be added
     */
    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the list,
     * writing it to storage, and showing a confirmation message.
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the {@code Ui} used to display messages
     * @param storage the {@code Storage} responsible for saving task data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.addToList(task);
        storage.writeTaskToFile(task);
        return message;
    }
}
