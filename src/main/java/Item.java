import java.util.Map;

abstract class Item {
    boolean status = false; // false == not done, true == done
    String label;
    Map<String, String> validParams = Map.of(
            "todo", "todo [task]",
            "deadline", "deadline [task] /by [time]",
            "event", "event [task] /from [start] /by [end]"
            );

    abstract boolean checkValid();

    Item(String label) {
        int firstSpace = label.split(" ")[0].length();
        int firstDate = label.split("/")[0].length();
        this.label = label.substring(firstSpace, firstDate);
    }

    String getStatusIcon() {
        return this.status ? "X" : " ";
    }

    void mark() {
        this.status = true;
    }

    void unmark() {
        this.status = false;
    }

    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "] " + this.label;
    }
}