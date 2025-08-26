import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Maintains the list of tasks and allows operations such as add, delete,
 * mark/unmark, and listing tasks by chronological order.
 */
public class ChatList {

    private final List<Item> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param item the task to add
     */
    public void addToList(Item item) {
        if (item.checkValid()) {
            tasks.add(item);
            System.out.println("Added: " + item);
        } else {
            System.out.println("Invalid task format.");
        }
    }

    /**
     * Deletes a task at the given 1-based index.
     *
     * @param index 1-based index of the task to delete
     */
    public void deleteFromList(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Index out of bounds.");
            return;
        }
        Item removed = tasks.remove(index - 1);
        System.out.println("Removed: " + removed);
    }

    /**
     * Marks a task as completed.
     *
     * @param index 1-based index
     */
    public void mark(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Index out of bounds.");
            return;
        }
        tasks.get(index - 1).mark();
        System.out.println("Marked as done: " + tasks.get(index - 1));
    }

    /**
     * Unmarks a task as not completed.
     *
     * @param index 1-based index
     */
    public void unmark(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Index out of bounds.");
            return;
        }
        tasks.get(index - 1).unmark();
        System.out.println("Unmarked: " + tasks.get(index - 1));
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return formatted task list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Lists only deadlines and events occurring on a specific date.
     *
     * @param date the target date
     */
    public void listTasksOnDate(java.time.LocalDate date) {
        tasks.stream()
                .filter(t -> t instanceof Deadline || t instanceof Event)
                .filter(t -> {
                    if (t instanceof Deadline d && d.getParsedDateTime() != null) {
                        return d.getParsedDateTime().toLocalDate().equals(date);
                    } else if (t instanceof Event e && e.getStartParsed() != null) {
                        return e.getStartParsed().toLocalDate().equals(date);
                    }
                    return false;
                })
                .sorted(Comparator.comparing(t -> {
                    if (t instanceof Deadline d && d.getParsedDateTime() != null) return d.getParsedDateTime();
                    else if (t instanceof Event e && e.getStartParsed() != null) return e.getStartParsed();
                    else return java.time.LocalDateTime.MAX;
                }))
                .forEach(System.out::println);
    }
}
