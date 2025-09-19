package ryuji.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateTimeHandler {
    /** Input formatter to parse date and optional time (time optional). */
    private static final List<String> patterns = List.of(
            "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd",
            "yyyy/MM/dd HHmm",
            "dd/MM/yyyy HH:mm:ss",
            "MM/dd/yyyy hh:mm a",
            "yyyy-MM-dd'T'HH:mm:ss'Z'"
    );

    /** Output formatter to display the date in a user-friendly format. */
    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, [hh:mm a]");

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

    public static String formatDetectedDateTime(LocalDateTime parsedDateTime) {
        return parsedDateTime.format(outputFormatter);
    }

}
