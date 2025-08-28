package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTaskList(tasks);
    }
}
