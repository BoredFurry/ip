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
        System.out.println("Hello! I'm Ryuji\nWhat can I do for you?");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        ChatList chatList = new ChatList();
        while(true) {
            String command = scanner.nextLine();
            System.out.println(line);
            if (command.equals("bye")) {
                System.out.println("\nThis fox will always be here for you\n" + line);
                break;
            }
            if (command.equals("list")) {
                System.out.println(chatList.toString());
            } else {
                chatList.addToList(command);
            }
            System.out.println(line);
        }
    }
}
