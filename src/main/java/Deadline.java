/**
 * Represents a task with a deadline.
 */
class Deadline extends Item {
    private final String endDate;

    /**
     * Constructs a Deadline task.
     *
     * @param label   the task label.
     * @param endDate the deadline of the task.
     */
    Deadline(String label, String endDate) {
        super(label);
        this.endDate = endDate;
    }

    /**
     * Checks if the deadline task is valid (must contain "/by").
     *
     * @return true if valid, false otherwise.
     */
    @Override
    boolean checkValid() {
        return label.contains("/by");
    }

    /**
     * Converts this Deadline task to a CSV row.
     *
     * @return a String array representing the task.
     */
    @Override
    String[] toCSVRow() {
        return new String[]{"DEADLINE", getStatusIcon(), label, endDate};
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return formatted string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate + ")";
    }
}
