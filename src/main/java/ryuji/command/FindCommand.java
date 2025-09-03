package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to search for tasks that contain a given keyword.
 * The {@code FindCommand} filters the list of tasks based on a search term
 * and displays the matching tasks to the user.
 */
public class FindCommand extends Command {

    /**
     * The term to search for within the task descriptions.
     * This term is used to filter the task list for matches.
     */
    private final String searchTerm;

    /**
     * Constructs a {@code FindCommand} with the specified command keyword and search term.
     *
     * @param command    The command keyword (e.g., "find").
     * @param searchTerm The term to search for within the task descriptions.
     */
    public FindCommand(String command, String searchTerm) {
        super(command);
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the find command by filtering the tasks that contain the search term
     * and displaying them through the UI.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI instance used to display messages to the user.
     * @param storage The storage instance used for saving/loading tasks (not used here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.find(searchTerm);
        ui.displayTaskList(filteredTasks);
    }
}