package ryuji.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a list of {@link Task} objects with operations
 * to add, delete, mark, unmark, filter, and search tasks.
 */
public class TaskList {

    /**
     * The internal list that stores tasks.
     */
    private List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks the list of tasks to initialize the task list with
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a valid task to the list. If the task is invalid,
     * it will not be added and a message will be printed.
     *
     * @param item the task to add
     */
    public void addToList(Task item) {
        if (item.checkValid()) {
            tasks.add(item);
            System.out.println("Added: " + item);
        } else {
            System.out.println("Invalid task format.");
        }
    }

    /**
     * Deletes a task at the specified 1-based index.
     * If the index is out of bounds, an error message is printed.
     *
     * @param index the 1-based index of the task to delete
     */
    public void deleteFromList(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Index out of bounds.");
            return;
        }
        Task removed = tasks.remove(index - 1);
        System.out.println("Removed: " + removed);
    }

    /**
     * Marks the task at the specified 1-based index as completed.
     * If the index is out of bounds, an error message is printed.
     *
     * @param index the 1-based index of the task to mark as done
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
     * Unmarks the task at the specified 1-based index as not completed.
     * If the index is out of bounds, an error message is printed.
     *
     * @param index the 1-based index of the task to unmark
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
     * Finds and returns a new {@code TaskList} containing tasks
     * whose labels match the given search term.
     *
     * @param searchTerm the keyword to search for in task descriptions or labels
     * @return a {@code TaskList} of matching tasks
     */
    public TaskList find(String searchTerm) {
        ArrayList<Task> validTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.checkLabel(searchTerm)) {
                validTasks.add(task);
            }
        }
        return new TaskList(validTasks);
    }

    /**
     * Returns a string representation of all tasks in the list.
     * Tasks are numbered starting from 1.
     *
     * @return a formatted string listing all tasks
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
     * Lists only deadlines and events that occur on the specified date.
     * The matching tasks are printed in chronological order.
     *
     * @param date the date to filter tasks by (only deadlines and events are considered)
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
