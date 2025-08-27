public class UnmarkCommand extends Command {
    private final int position;

    UnmarkCommand(String command, int position) {
        super(command);
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(this.position);
        ui.showTaskUnmarked();
    }
}
