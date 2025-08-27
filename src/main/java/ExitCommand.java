public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
