package ryuji.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 * Supports flexible date/time input in the format "yyyy-MM-dd" or "yyyy-MM-dd HHmm".
 */
public class Deadline extends Task {

    /** Parsed date-time if input matches expected format; null otherwise. */
    private final String parsedDateTime;

    /** Raw date-time string used as fallback if parsing fails or no standard format. */
    private final String rawDateTime;


    /**
     * Constructs a Deadline task from a command string.
     * Example input: "deadline return book /by 2019-12-02 1800"
     *
     * @param input the full command string including the deadline date/time
     */
    public Deadline(String input) {
        super(input.split("/by", 2)[0]);
        String[] parts = input.split("/by", 2);
        String dateTimeString = parts[1].trim();

        LocalDateTime detectedDateTime = DateTimeHandler.getDateTime(dateTimeString);

        if (detectedDateTime != null) {
            parsedDateTime = DateTimeHandler.formatDetectedDateTime(detectedDateTime);
            rawDateTime = null;
        } else {
            parsedDateTime = null;
            rawDateTime = dateTimeString;
        }
    }

    /**
     * Constructs a Deadline task with mark status from a command string.
     *
     * @param input    the full command string including the deadline date/time
     * @param isMarked true if the task is marked done, false otherwise
     */
    public Deadline(String input, boolean isMarked) {
        super(input.split("/by", 2)[0], isMarked);
        String[] parts = input.split("/by", 2);
        String dateTimeString = parts[1].trim();

        LocalDateTime detectedDateTime = DateTimeHandler.getDateTime(dateTimeString);

        if (detectedDateTime != null) {
            parsedDateTime = DateTimeHandler.formatDetectedDateTime(detectedDateTime);
            rawDateTime = null;
        } else {
            parsedDateTime = null;
            rawDateTime = dateTimeString;
        }
    }

    /**
     * Checks if this deadline task has a valid date/time (either parsed or raw).
     *
     * @return true if the deadline has valid date or non-empty raw date string
     */
    @Override
    boolean checkValid() {
        boolean isParsedDateTimePresent = this.parsedDateTime != null;
        boolean isRawDateTimePresent = this.rawDateTime != null && !this.rawDateTime.isEmpty();

        boolean areBothTimesPresent = isParsedDateTimePresent || isRawDateTimePresent;

        return areBothTimesPresent;
    }

    /**
     * Returns a CSV row string representing this task.
     * Format suggestion: "D | status | label | date/time"
     *
     * @return CSV formatted string of this task
     */
    @Override
    public String toCsvRow() {
        String status = getStatusIcon();
        String labelPart = this.label.split("/by", 2)[0].trim();
        String dateStr;

        boolean isDateTimePresent = parsedDateTime != null;

        if (isDateTimePresent)  {
            dateStr = parsedDateTime;
        } else {
            dateStr = rawDateTime;
        }

        return String.format("D," + status + "," + labelPart + " /by" + dateStr);
    }

    /**
     * Returns a user-friendly string representation of this deadline task.
     *
     * @return formatted string including deadline date/time
     */
    @Override
    public String toString() {
        if (parsedDateTime != null) {
            return "[D]" + super.toString() + " (by: "
                    + parsedDateTime + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + rawDateTime + ")";
        }
    }
}
