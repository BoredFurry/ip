package ryuji.task;

/**
 * Abstract base class representing a generic task item.
 * This class provides the core functionality for all task types, including:
 * - Status tracking (marked/unmarked)
 * - Label handling
 * - CSV conversion for storage
 * Concrete subclasses such as {@code ToDo}, {@code Deadline}, and {@code Event}
 * should extend this class and implement specific validation and data serialization logic.
 */
public abstract class Task {

    /**
     * Indicates whether the task is marked as completed.
     */
    protected boolean isMarked = false;

    /**
     * The textual description or label of the task.
     */
    protected String label;

    /**
     * Constructs a {@code Task} with the given label.
     *
     * @param label the task's label or description
     */
    Task(String label) {
        this.label = label;
    }

    /**
     * Constructs a {@code Task} with the given label and completion status.
     *
     * @param label  the task's label or description
     * @param status the completion status (true if completed, false otherwise)
     */
    Task(String label, boolean status) {
        this.label = label;
        this.isMarked = status;
    }

    /**
     * Checks whether the task is valid.
     * This method must be implemented by subclasses to perform type-specific validation.
     *
     * @return {@code true} if the task is valid; {@code false} otherwise
     */
    abstract boolean checkValid();

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Unmarks the task (sets it as not completed).
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is completed; otherwise, a space character " "
     */
    public String getStatusIcon() {
        return isMarked ? "X" : " ";
    }

    /**
     * Checks whether the task's label contains the specified search term.
     *
     * @param searchTerm the keyword to search for
     * @return {@code true} if the label contains the search term; {@code false} otherwise
     */
    public boolean checkLabel(String searchTerm) {
        return label.contains(searchTerm);
    }

    /**
     * Converts the task to a CSV-compatible string array for persistent storage.
     * This method must be implemented by subclasses to include task-specific fields.
     *
     * @return a string array representing the task's fields
     */
    public abstract String toCsvRow();

    /**
     * Creates a {@code Task} object from a CSV row.
     * The first element of the row should indicate the task type: "todo", "deadline", or "event".
     *
     * @param row the CSV row containing the task data
     * @return a {@code Task} object reconstructed from the row; or {@code null} if the type is invalid
     */
    public static Task fromCsvRow(String[] row) {
        Task task;
        String taskType = row[0];
        boolean isMarked = row[1].equals("X");
        String label = row[2];
        task = switch (taskType) {
            case "todo" -> new ToDo(label, isMarked);
            case "deadline" -> new Deadline(label, isMarked);
            case "event" -> new Event(label, isMarked);
            default -> null;
        };
        return task;
    }

    /**
     * Returns a string representation of the task, including its status icon and label.
     *
     * @return the formatted string representing the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + label;
    }
}
