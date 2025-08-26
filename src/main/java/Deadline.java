import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline. Supports flexible date/time input.
 */
public class Deadline extends Item {

    private final LocalDateTime parsedDateTime; // null if not a standard date
    private final String rawDateTime; // fallback if parsing fails
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd[ HHmm]");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a Deadline task from a command string.
     *
     * @param label raw input string, e.g., "deadline return book /by 2019-12-02 1800"
     */
    public Deadline(String label) {
        super(label);
        String[] parts = label.split("/by");
        if (parts.length < 2) {
            this.parsedDateTime = null;
            this.rawDateTime = "";
        } else {
            String dateStr = parts[1].trim();
            LocalDateTime dt;
            try {
                dt = LocalDateTime.parse(dateStr, inputFormatter);
            } catch (DateTimeParseException e) {
                dt = null;
            }
            this.parsedDateTime = dt;
            this.rawDateTime = (dt == null) ? dateStr : null;
        }
    }

    @Override
    boolean checkValid() {
        return this.parsedDateTime != null || (this.rawDateTime != null && !this.rawDateTime.isEmpty());
    }

    @Override
    public String toString() {
        if (parsedDateTime != null) {
            return "[D]" + super.toString() + " (by: " + parsedDateTime.format(outputFormatter) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + rawDateTime + ")";
        }
    }

    public LocalDateTime getParsedDateTime() {
        return parsedDateTime;
    }

    public String getRawDateTime() {
        return rawDateTime;
    }
}
