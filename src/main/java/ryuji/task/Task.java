package ryuji.task;

/**
 * Abstract base class representing a generic task item.
 * Provides status tracking, label handling, and CSV conversion.
 */
public abstract class Task {
    protected boolean isMarked = false;
    protected String label;

    /**
     * Constructs an Item with a label.
     *
     * @param label the task label.
     */
    Task(String label) {
        this.label = label;
    }

    Task(String label, boolean status) {
        this.label = label;
        this.isMarked = false;
    }

    /**
     * Checks whether the item is valid according to its type.
     *
     * @return true if valid, false otherwise.
     */
    abstract boolean checkValid();

    /**
     * Marks the item as completed.
     */
    void mark() {
        isMarked = true;
    }

    /**
     * Returns the status icon.
     *
     * @return "X" if completed, " " if not
     */
    public String getStatusIcon() {
        return isMarked ? "X" : " ";
    }

    /**
     * Marks the item as not completed.
     */
    void unmark() {
        isMarked = false;
    }

    /**
     * Converts the item into a String array for CSV storage.
     *
     * @return a String array representing the item.
     */
    public abstract String toCsvRow();

    /**
     * Reconstructs a Task object from a CSV row.
     *
     * @param row the CSV row representing the item.
     * @return an Item object, or null if invalid.
     */
    public static Task fromCsvRow(String[] row) {
        Task task;
        String taskType = row[0];
        boolean isMarked = (row[1].equals("X"));
        String label = row[2];
        switch (taskType) {
        case "todo":
            task = new ToDo(label, isMarked);
            break;
        case "deadline":
            task = new Deadline(label, isMarked);
            break;
        case "event":
            task = new Event(label, isMarked);
            break;
        default:
            task = null;
        }
        return task;
    }

    /**
     * Returns a string representation of the item, including status and label.
     *
     * @return formatted string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + label;
    }

}
