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

        while(true) {
            String command = scanner.nextLine().toLowerCase();
            System.out.println(line);
            if (command.equals("bye")) {
                System.out.println("\nThis fox will always be here for you.\n" + line);
                break;
            }

            if (command.equals("list")) {
                System.out.println(chatList);
                continue;
            }

            String[] splitCommand = command.split(" ");

            switch (splitCommand[0]) {
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
            }
            System.out.println(line);
        }
    }
}

