/**
 * Represents a task with a specific event time or range.
 */
class Event extends Item {
    private final String time;

    /**
     * Constructs an Event task.
     *
     * @param label the task label.
     * @param time  the time or range of the event.
     */
    Event(String label, String time) {
        super(label);
        this.time = time;
    }

    /**
     * Checks if the event task is valid (must contain "/from" and "/to").
     *
     * @return true if valid, false otherwise.
     */
    @Override
    boolean checkValid() {
        return label.contains("/from") && label.contains("/to");
    }

    /**
     * Converts this Event task to a CSV row.
     *
     * @return a String array representing the task.
     */
    @Override
    String[] toCSVRow() {
        return new String[]{"EVENT", getStatusIcon(), label, time};
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return formatted string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
