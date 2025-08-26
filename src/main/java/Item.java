import java.util.Arrays;

/**
 * Abstract base class representing a generic task item.
 * Provides status tracking, label handling, and CSV conversion.
 */
abstract class Item {
    protected boolean status = false;
    protected String label;

    /**
     * Constructs an Item with a label.
     *
     * @param label the task label.
     */
    Item(String label) {
        this.label = label;
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
    abstract String[] toCSVRow();

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
    static Item fromCSVRow(String[] row) {
        if (row.length < 3) return null;
        String type = row[0];
        boolean done = row[1].equals("X");
        String label = row[2];

        Item item = switch (type) {
            case "TODO" -> new ToDo(label);
            case "DEADLINE" -> new Deadline(label, row.length > 3 ? row[3] : "");
            case "EVENT" -> new Event(label, row.length > 3 ? row[3] : "");
            default -> null;
        };
        if (item != null && done) item.mark();
        return item;
    }
}
