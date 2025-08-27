import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event with start and end dates.
 * Supports flexible date/time input.
 */
public class Event extends Task {
    private final LocalDateTime startParsed;
    private final LocalDateTime endParsed;
    private final String startRaw;
    private final String endRaw;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd[ HHmm]");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs an Event from a command string.
     *
     * @param label raw input string, e.g.,
     *              "event party /from 2019-12-02 1800 /to 2019-12-02 2000"
     */
    public Event(String label) {
        super(label);

        String[] fromSplit = label.split("/from");
        String[] toSplit = label.split("/to");

        if (fromSplit.length < 2 || toSplit.length < 2) {
            startParsed = null;
            endParsed = null;
            startRaw = "";
            endRaw = "";
        } else {
            String startStr = fromSplit[1].split("/to")[0].trim();
            String endStr = toSplit[1].trim();
            LocalDateTime startDt;
            LocalDateTime endDt;

            try {
                startDt = LocalDateTime.parse(startStr, inputFormatter);
            } catch (DateTimeParseException e) {
                startDt = null;
            }

            try {
                endDt = LocalDateTime.parse(endStr, inputFormatter);
            } catch (DateTimeParseException e) {
                endDt = null;
            }

            startParsed = startDt;
            endParsed = endDt;
            startRaw = (startDt == null) ? startStr : null;
            endRaw = (endDt == null) ? endStr : null;
        }
    }

    @Override
    boolean checkValid() {
        return (startParsed != null || (startRaw != null && !startRaw.isEmpty()))
                && (endParsed != null || (endRaw != null && !endRaw.isEmpty()));
    }

    @Override
    public String toString() {
        String startStr = (startParsed != null) ? startParsed.format(outputFormatter) : startRaw;
        String endStr = (endParsed != null) ? endParsed.format(outputFormatter) : endRaw;
        return "[E]" + super.toString() + " (from: " + startStr + " to: " + endStr + ")";
    }

    @Override
    String toCSVRow() {
        return "TODO" + getStatusIcon() + this.label + this.startParsed + this.endParsed;
    }

    public LocalDateTime getStartParsed() {
        return startParsed;
    }

    public LocalDateTime getEndParsed() {
        return endParsed;
    }

    public String getStartRaw() {
        return startRaw;
    }

    public String getEndRaw() {
        return endRaw;
    }
}
