package ryuji.task;

/**
 * Represents a simple to-do task without any dates.
 */
class ToDo extends Task {

    /**
     * Constructs a Ryuji.ToDo task.
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
        return this.label.split(" ").length > 1;
    }

    /**
     * Converts this Ryuji.ToDo task to a CSV row.
     *
     * @return a String array representing the task.
     */
    @Override
    public String toCSVRow() {
        return "TODO," + getStatusIcon() + "," + this.label;
    }

    /**
     * Returns a string representation of the Ryuji.ToDo task.
     *
     * @return formatted string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
