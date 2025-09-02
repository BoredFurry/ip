package ryuji.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with start and end date/time.
 * Supports flexible date/time input in the format "yyyy-MM-dd" or "yyyy-MM-dd HHmm".
 */
public class Event extends Task {
    /** Parsed start date-time, or null if parsing fails */
    private final LocalDateTime startParsed;

    /** Parsed end date-time, or null if parsing fails */
    private final LocalDateTime endParsed;

    /** Raw start date/time string, fallback if parsing fails */
    private final String startRaw;

    /** Raw end date/time string, fallback if parsing fails */
    private final String endRaw;

    /** Input formatter to parse date and optional time */
    private static final DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd[ HHmm]");

    /** Output formatter for displaying date/time to user */
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs an Event task from the full command string.
     * Example input: "event party /from 2019-12-02 1800 /to 2019-12-02 2000"
     *
     * @param label the full input string including event description and times
     */
    public Event(String label) {
        super(label);
        String[] fromSplit = label.split("/from", 2);
        String[] toSplit = label.split("/to", 2);

        if (fromSplit.length < 2 || toSplit.length < 2) {
            this.startParsed = null;
            this.endParsed = null;
            this.startRaw = "";
            this.endRaw = "";
        } else {
            String startStr = fromSplit[1].split("/to", 2)[0].trim();
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

            this.startParsed = startDt;
            this.endParsed = endDt;
            this.startRaw = (startDt == null) ? startStr : null;
            this.endRaw = (endDt == null) ? endStr : null;
        }
    }

    /**
     * Constructs an Event task with mark status from the full command string.
     *
     * @param label    the full input string including event description and times
     * @param isMarked true if task is marked done, false otherwise
     */
    public Event(String label, boolean isMarked) {
        super(label, isMarked);
        String[] fromSplit = label.split("/from", 2);
        String[] toSplit = label.split("/to", 2);

        if (fromSplit.length < 2 || toSplit.length < 2) {
            this.startParsed = null;
            this.endParsed = null;
            this.startRaw = "";
            this.endRaw = "";
        } else {
            String startStr = fromSplit[1].split("/to", 2)[0].trim();
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

            this.startParsed = startDt;
            this.endParsed = endDt;
            this.startRaw = (startDt == null) ? startStr : null;
            this.endRaw = (endDt == null) ? endStr : null;
        }
    }

    /**
     * Checks if the event has valid start and end date/time.
     *
     * @return true if both start and end times are valid (parsed or raw)
     */
    @Override
    boolean checkValid() {
        return (startParsed != null || (startRaw != null && !startRaw.isEmpty()))
                && (endParsed != null || (endRaw != null && !endRaw.isEmpty()));
    }

    /**
     * Returns a user-friendly string representation of the event.
     *
     * @return string formatted as "[E]<task info> (from: start to: end)"
     */
    @Override
    public String toString() {
        String startStr = (startParsed != null) ? startParsed.format(outputFormatter) : startRaw;
        String endStr = (endParsed != null) ? endParsed.format(outputFormatter) : endRaw;
        return "[E]" + super.toString() + " (from: " + startStr + " to: " + endStr + ")";
    }

    /**
     * Returns a CSV row string representing this event.
     * Adjust this method to fit your CSV format.
     *
     * @return CSV formatted string of this event task
     */
    @Override
    public String toCsvRow() {
        String status = getStatusIcon().equals("X") ? "1" : "0";
        String labelPart = this.label.split("/from", 2)[0].trim();
        String startStr = (startParsed != null) ? startParsed.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : startRaw;
        String endStr = (endParsed != null) ? endParsed.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : endRaw;
        return String.format("E | %s | %s | %s | %s", status, labelPart, startStr, endStr);
    }

    // Getter methods for start/end date/time

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
