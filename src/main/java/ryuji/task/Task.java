package ryuji.task;

import ryuji.ToDo;

/**
 * Abstract base class representing a generic task item.
 * Provides status tracking, label handling, and CSV conversion.
 */
public abstract class Task {
    protected boolean status = false;
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
        this.status = false;
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
        status = true;
    }

    /**
     * Returns the status icon.
     *
     * @return "X" if completed, " " if not
     */
    public String getStatusIcon() {
        return this.status ? "X" : " ";
    }

    /**
     * Marks the item as not completed.
     */
    void unmark() {
        status = false;
    }

    /**
     * Converts the item into a String array for CSV storage.
     *
     * @return a String array representing the item.
     */
    public abstract String toCSVRow();


    /**
     * Returns a string representation of the item, including status and label.
     *
     * @return formatted string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + label;
    }

    /**
     * Reconstructs an Item object from a CSV row.
     *
     * @param row the CSV row representing the item.
     * @return an Item object, or null if invalid.
     */
    public static Task fromCSVRow(String[] row) {
        if (row.length < 3) return null;
        String type = row[0];
        boolean done = row[1].equals("X");
        String label = row[2];

        Task item = switch (type) {
            case "TODO" -> new ToDo(label);
            case "DEADLINE" -> new Deadline(label);
            case "EVENT" -> new Event(label);
            default -> null;
        };
        if (item != null && done) item.mark();
        return item;
    }
}
