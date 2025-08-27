public class AddCommand extends Command {
    private final Task task;

    AddCommand(String command, Task task) {
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
