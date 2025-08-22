import java.util.HashMap;

public class Functions {
    String[] functions = {"mark", "unmark", "todo", "deadline", "event", "delete"};

    boolean checkFunctionExists(String function) {
        for (String func: functions) {
            if (func.equals(function)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.functions.toString();
    }
}
