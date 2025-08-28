package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

public class DeleteCommand extends Command {
    private final int position;

    DeleteCommand(String command, int position) {
        super(command);
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteFromList(this.position);
        try {
            storage.updateFile(this.position);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
