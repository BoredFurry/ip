package ryuji.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Utility class for handling various date and time formats.
 * <p>This class provides methods to parse, detect, and format date-time strings in different formats.</p>
 * The supported date-time formats include both date and time (with time being optional) and different local
 * and international date-time representations.
 */
public class DateTimeHandler {

    /** Input formatter patterns to parse date and optional time (time is optional). */
    private static final List<String> patterns = List.of(
            "yyyy/MM/dd HH:mm:ss",  // Year/Month/Day Hour:Minute:Second
            "yyyy/MM/dd",            // Year/Month/Day (date only)
            "yyyy/MM/dd HHmm",       // Year/Month/Day HourMinute (without colon separator)
            "dd/MM/yyyy HH:mm:ss",   // Day/Month/Year Hour:Minute:Second
            "MM/dd/yyyy hh:mm a",    // Month/Day/Year Hour:Minute AM/PM
            "yyyy-MM-dd'T'HH:mm:ss'Z'" // ISO 8601 format (e.g., 2025-09-15T16:00:00Z)
    );

    /** Output formatter to display the date in a user-friendly format. */
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, [hh:mm a]");

    /**
     * Parses a given date-time string into a {@link LocalDateTime} object.
     * <p>This method attempts to detect the format of the provided string using predefined patterns.
     * If a valid format is detected, the string is parsed into a {@link LocalDateTime} object. If no valid
     * format is found, the method returns null.</p>
     *
     * @param dateTimeString the date-time string to parse
     * @return the parsed {@link LocalDateTime} object, or null if no valid format was found
     */
    public static LocalDateTime getDateTime(String dateTimeString) {
        String detectedFormat = detectDateTimeFormat(dateTimeString, patterns);
        LocalDateTime formattedDateTime;

        if (detectedFormat != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(detectedFormat);
            formattedDateTime = LocalDateTime.parse(dateTimeString, formatter);
        } else {
            formattedDateTime = null;
        }
        return formattedDateTime;
    }

    /**
     * Detects the format of a given date-time string by trying each of the predefined patterns.
     * <p>This method iterates over a list of date-time patterns and attempts to parse the string
     * using each pattern. If a valid format is found, it returns the corresponding pattern. If no format
     * matches, it returns null.</p>
     *
     * @param dateTimeString the date-time string to detect the format for
     * @param patterns       the list of predefined patterns to check
     * @return the detected format pattern, or null if no matching format is found
     */
    public static String detectDateTimeFormat(String dateTimeString, List<String> patterns) {
        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                LocalDateTime parsedDate = LocalDateTime.parse(dateTimeString, formatter);
                return pattern;
            } catch (DateTimeParseException e) {
                // If parsing fails, continue to the next pattern
            }
        }
        return null;  // If no format matches
    }

    /**
     * Formats a given {@link LocalDateTime} object into a user-friendly string representation.
     * <p>This method uses the predefined output formatter to convert the {@link LocalDateTime} into a string
     * formatted as "MMM dd yyyy, [hh:mm a]" (e.g., "Sep 15 2025, [04:00 PM]").</p>
     *
     * @param parsedDateTime the {@link LocalDateTime} object to format
     * @return the formatted date-time string
     */
    public static String formatDetectedDateTime(LocalDateTime parsedDateTime) {
        return parsedDateTime.format(outputFormatter);
    }

}
