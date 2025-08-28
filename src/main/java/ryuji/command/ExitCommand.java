package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
