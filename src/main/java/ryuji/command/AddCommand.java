package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.Task;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addToList(this.task);
        storage.writeToFile(this.task);
        ui.showTaskAdded();
    }
}
