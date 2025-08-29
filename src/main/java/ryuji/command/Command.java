package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.RyujiException;
import ryuji.ui.Ui;

abstract public class Command {
    private final String command;

    public Command(String command) {
        this.command = command;
    }


    public String getCommand() {
        return this.command;
    }

    public boolean isExit() {
        return this.getCommand().equals("bye");
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    @Override
    public String toString() {
        return this.command;
    }
}

