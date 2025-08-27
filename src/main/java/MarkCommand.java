public class MarkCommand extends Command{
    private final int position;

    MarkCommand(String command, int position) {
        super(command);
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(this.position);
        ui.showTaskMarked();
    }
}
