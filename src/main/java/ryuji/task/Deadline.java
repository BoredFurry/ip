package ryuji.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * Supports flexible date/time input in the format "yyyy-MM-dd" or "yyyy-MM-dd HHmm".
 */
public class Deadline extends Task {

    /** Parsed date-time if input matches expected format; null otherwise. */
    private final LocalDateTime parsedDateTime;

    /** Raw date-time string used as fallback if parsing fails or no standard format. */
    private final String rawDateTime;

    /** Input formatter to parse date and optional time (time optional). */
    private static final DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd[ HHmm]");

    /** Output formatter to display the date in a user-friendly format. */
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a Deadline task from a command string.
     * Example input: "deadline return book /by 2019-12-02 1800"
     *
     * @param label the full command string including the deadline date/time
     */
    public Deadline(String label) {
        super(label);
        String[] parts = label.split("/by", 2);
        if (parts.length < 2) {
            this.parsedDateTime = null;
            this.rawDateTime = "";
        } else {
            String dateStr = parts[1].trim();
            LocalDateTime dt = null;
            try {
                dt = LocalDateTime.parse(dateStr, inputFormatter);
            } catch (DateTimeParseException e) {
                // parsing failed, keep dt null
            }
            this.parsedDateTime = dt;
            this.rawDateTime = (dt == null) ? dateStr : null;
        }
    }

    /**
     * Constructs a Deadline task with mark status from a command string.
     *
     * @param label    the full command string including the deadline date/time
     * @param isMarked true if the task is marked done, false otherwise
     */
    public Deadline(String label, boolean isMarked) {
        super(label, isMarked);
        String[] parts = label.split("/by", 2);
        if (parts.length < 2) {
            this.parsedDateTime = null;
            this.rawDateTime = "";
        } else {
            String dateStr = parts[1].trim();
            LocalDateTime dt = null;
            try {
                dt = LocalDateTime.parse(dateStr, inputFormatter);
            } catch (DateTimeParseException e) {
                // parsing failed
            }
            this.parsedDateTime = dt;
            this.rawDateTime = (dt == null) ? dateStr : null;
        }
    }

    /**
     * Checks if this deadline task has a valid date/time (either parsed or raw).
     *
     * @return true if the deadline has valid date or non-empty raw date string
     */
    @Override
    boolean checkValid() {
        return this.parsedDateTime != null || (this.rawDateTime != null && !this.rawDateTime.isEmpty());
    }

    /**
     * Returns a CSV row string representing this task.
     * Format suggestion: "D | status | label | date/time"
     *
     * @return CSV formatted string of this task
     */
    @Override
    public String toCsvRow() {
        String status = getStatusIcon().equals("X") ? "1" : "0"; // Assuming "X" means done
        String dateStr = (parsedDateTime != null) ? parsedDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : rawDateTime;
        return String.format("D | %s | %s | %s", status, this.label.split("/by")[0].trim(), dateStr);
    }

    /**
     * Returns a user-friendly string representation of this deadline task.
     *
     * @return formatted string including deadline date/time
     */
    @Override
    public String toString() {
        if (parsedDateTime != null) {
            return "[D]" + super.toString() + " (by: " + parsedDateTime.format(outputFormatter) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + rawDateTime + ")";
        }
    }

    /** Returns the parsed LocalDateTime if available, else null. */
    public LocalDateTime getParsedDateTime() {
        return parsedDateTime;
    }

    /** Returns the raw date/time string if parsing failed or not provided. */
    public String getRawDateTime() {
        return rawDateTime;
    }
}
