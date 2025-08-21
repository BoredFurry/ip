import java.util.Scanner;

public class Ryuji {
    public static void main(String[] args) {
        String logo =  " ____               _ _ \n"
                + "|  _ \\ _   _ _   _ (_|_)\n"
                + "| |_) | | | | | | || | |\n"
                + "|  _ <| |_| | |_| || | |\n"
                + "|_| \\_\\\\__, |\\__,_|/ |_|\n"
                + "       |___/     |__/   \n";
        String line = "_______________________________________________________";
        System.out.println(line + "\n" + logo);
        System.out.println("Welcome to Ryuji chat bot. \n How may I be of serve today master?");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        ChatList chatList = new ChatList();
        RyujiException ryujiException = new RyujiException();
        Functions functions = new Functions();

        while(true) {
            // get input
            String command = scanner.nextLine().toLowerCase();
            System.out.println(line);

            // goodbye
            if (command.equals("bye")) {
                System.out.println("\nThis fox will always be here for you.\n" + line);
                break;
            }

            // list out chatlist
            if (command.equals("list")) {
                System.out.println(chatList);
                continue;
            }

            String[] splitCommand = command.split(" "); // split input
            String function = splitCommand[0]; // get function

            // run it through exception handler

            switch (function) {
                case "mark":
                    chatList.mark(Integer.parseInt(splitCommand[1]));
                    break;
                case "unmark":
                    chatList.unmark(Integer.parseInt(splitCommand[1]));
                    break;
                case "todo":
                    ToDo toDo = new ToDo(command);
                    chatList.addToList(toDo);
                    break;
                case "deadline":
                    Deadline deadline = new Deadline(command);
                    chatList.addToList(deadline);
                    break;
                case "event":
                    Event event = new Event(command);
                    chatList.addToList(event);
                    break;
                case "delete":
                    chatList.deleteFromList(splitCommand[1]);
                    break;
            }
            System.out.println(line);
        }
    }
}