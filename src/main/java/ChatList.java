import java.util.ArrayList;

public class ChatList {
    ArrayList<String> inputs = new ArrayList<>();

    void addToList(String item) {
        this.inputs.add(item);
        System.out.println("added: " + item);
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 1; i <= this.inputs.size(); i++) {
            output += i + ". " + inputs.get(i - 1) + "\n";
        }
        return output;
    }
}
