package ryuji.task;

import java.time.LocalDateTime;

/**
 * Represents an event task with start and end date/time.
 * Supports flexible date/time input in the format "yyyy-MM-dd" or "yyyy-MM-dd HHmm".
 */
public class Event extends Task {
    /** Parsed start date-time, or null if parsing fails */
    private final String startParsed;

    /** Parsed end date-time, or null if parsing fails */
    private final String endParsed;

    /** Raw start date/time string, fallback if parsing fails */
    private final String startRaw;

    /** Raw end date/time string, fallback if parsing fails */
    private final String endRaw;

    /**
     * Constructs an Event task from the full command string.
     * Example input: "event party /from 2019-12-02 1800 /to 2019-12-02 2000"
     *
     * @param input the full input string including event description and times
     */
    public Event(String input) {
        super(input.split("/from", 2)[0]);
        String[] fromSplit = input.split("/from", 2);
        String[] toSplit = input.split("/to", 2);

        int endOfStartDate = fromSplit[1].indexOf("/");
        String unparsedStartDate = fromSplit[1].substring(0, endOfStartDate).trim();

        String unparsedEndDate = toSplit[1].trim();

        LocalDateTime detectedStartDateTime = DateTimeHandler.getDateTime(unparsedStartDate);
        LocalDateTime detectedEndDateTime = DateTimeHandler.getDateTime(unparsedStartDate);



        if (detectedStartDateTime != null) {
            startParsed = DateTimeHandler.formatDetectedDateTime(detectedStartDateTime);
            startRaw = null;
        } else {
            startParsed = null;
            startRaw = unparsedStartDate;
        }

        if (detectedEndDateTime != null) {
            endParsed = DateTimeHandler.formatDetectedDateTime(detectedEndDateTime);
            endRaw = null;
        } else {
            endParsed = null;
            endRaw = unparsedEndDate;
        }
    }

    /**
     * Constructs an Event task with mark status from the full command string.
     *
     * @param input    the full input string including event description and times
     * @param isMarked true if task is marked done, false otherwise
     */
    public Event(String input, boolean isMarked) {
        super(input.split("/from", 2)[0], isMarked);
        String[] fromSplit = input.split("/from", 2);
        String[] toSplit = input.split("/to", 2);

        int endOfStartDate = fromSplit[1].indexOf("/");
        String unparsedStartDate = fromSplit[1].substring(0, endOfStartDate).trim();

        String unparsedEndDate = toSplit[1].trim();

        LocalDateTime detectedStartDateTime = DateTimeHandler.getDateTime(unparsedStartDate);
        LocalDateTime detectedEndDateTime = DateTimeHandler.getDateTime(unparsedStartDate);

        if (detectedStartDateTime != null) {
            startParsed = DateTimeHandler.formatDetectedDateTime(detectedStartDateTime);
            startRaw = null;
        } else {
            startParsed = null;
            startRaw = unparsedStartDate;
        }

        if (detectedEndDateTime != null) {
            endParsed = DateTimeHandler.formatDetectedDateTime(detectedEndDateTime);
            endRaw = null;
        } else {
            endParsed = null;
            endRaw = unparsedEndDate;
        }
    }

    /**
     * Checks if the event has valid start and end date/time.
     *
     * @return true if both start and end times are valid (parsed or raw)
     */
    @Override
    boolean checkValid() {
        boolean isStartParsedDateTimePresent = this.startParsed != null;
        boolean isStartRawDateTimePresent = this.startRaw != null && !this.startRaw.isEmpty();

        boolean isStartPresent = isStartParsedDateTimePresent || isStartRawDateTimePresent;

        boolean isEndParsedDateTimePresent = this.endParsed != null;
        boolean isEndRawDateTimePresent = endRaw != null && !endRaw.isEmpty();

        boolean isEndPresent = isEndParsedDateTimePresent && isEndRawDateTimePresent;

        boolean isDateTimePresent = isStartPresent || isEndPresent;

        return isDateTimePresent;
    }

    /**
     * Returns a user-friendly string representation of the event.
     *
     * @return string formatted as "[E]<task info> (from: start to: end)"
     */
    @Override
    public String toString() {
        String startStr = (startParsed != null) ? startParsed: startRaw;
        String endStr = (endParsed != null) ? endParsed : endRaw;
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
        String status = getStatusIcon();

        String startStr = (startParsed != null) ? startParsed: startRaw;
        String endStr = (endParsed != null) ? endParsed : endRaw;

        return String.format("E," + status + "," + label + " /from " + startStr + " /to " + endStr);
    }
}
