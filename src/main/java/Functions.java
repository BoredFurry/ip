import java.util.Set;

public class Functions {
    private final Set<String> functions = Set.of("mark", "unmark", "todo", "deadline", "event", "delete");

    public boolean checkFunctionExists(String function) {
        return functions.contains(function);
    }

    @Override
    public String toString() {
        return functions.toString();
    }
}

