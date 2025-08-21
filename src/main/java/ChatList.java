import java.util.ArrayList;

class ChatList {
    ArrayList<Item> inputs = new ArrayList<>();

    void addToList(Item item) {
        this.inputs.add(item);
        System.out.println("I've added '" + item + "' to your list master");
    }

    void mark(Integer index) {
        index--;
        this.inputs.get(index).mark();
        System.out.println("I have marked this as completed master");
        System.out.println("[" + this.inputs.get(index).getStatusIcon() + "] "
                + this.inputs.get(index));
    }

    void unmark(Integer index) {
        index--;
        this.inputs.get(index).unmark();
        System.out.println("I have unmarked this from your list master");
        System.out.println("[" + this.inputs.get(index).getStatusIcon() + "] "
                + this.inputs.get(index));
    }

    @Override
    public String toString() {
        String output = "Here is your list master:\n";
        for(int i = 1; i <= this.inputs.size(); i++) {
            Item item = this.inputs.get(i - 1);
            output += i + ". [" + item.getStatusIcon() + "] " + item + "\n";
        }
        return output;
    }
}
