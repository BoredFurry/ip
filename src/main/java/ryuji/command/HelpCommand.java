package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

public class HelpCommand extends Command {
    private final String HELPMESSAGE = """
            Welcome to RyujiCafe, here is the menu:
            In this cafe, you can keep track of tasks to that you can focus on doing them
            Types of tasks I can assist with:
            ToDos
                type: todo make coffee
                result: [T][ ] make coffee
            
            Deadlines
                type: deadline return book /by 2025-09-15 1600
                result: [D][ ] return book (by 2025-09-15 1600)
            
            Events
                type: event JMOF /from 2026-01-09 /to 2026-01-11
                result: [E][ ] JMOF (from 2026-01-09 to 2026-01-11)
            
            Commands to help you with your list of tasks:
            Mark
                type: mark 1
                result: [T][ ] return book -> [T][X] return book
            
            Unmark
                type: unmark 1
                result: [T][X] return book -> [T][ ] return book
            
            List
                type: list
                 result:
                1. [T][ ] make coffee
                2. [D][ ] return book (by 2025-09-15 1600)
                3. [E][ ] JMOF (from 2026-01-09 to 2026-01-11)
            
            Delete
                type delete 1
                result: first item will be removed from the list
            
            Find
                type: find book
                result: 1. [D] return book (by 2025-09-15)
            
            Exit
                type: exit
                result: ends the chat with Ryuji
            
            Go ahead and try some of these commands
            """;

    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp(HELPMESSAGE);
    }
}
