/**
 * Represents a simple to-do task without any dates.
 */
class ToDo extends Item {

    /**
     * Constructs a ToDo task.
     *
     * @param label the task label.
     */
    ToDo(String label) {
        super(label);
    }

    /**
     * Checks if the task is valid (must contain at least one space).
     *
     * @return true if valid, false otherwise.
     */
    @Override
    boolean checkValid() {
        return label.split(" ").length > 1;
    }

    /**
     * Converts this ToDo task to a CSV row.
     *
     * @return a String array representing the task.
     */
    @Override
    String[] toCSVRow() {
        return new String[]{"TODO", getStatusIcon(), label};
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return formatted string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
