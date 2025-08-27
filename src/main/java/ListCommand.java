public class ListCommand extends Command{

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTaskList(tasks);
    }
}
