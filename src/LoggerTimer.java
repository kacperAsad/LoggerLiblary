import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class is used to get a Date, Time, DateTime and more in easy way
 */
public class LoggerTimer {

    /**
     * Used to get a Time in Pattern 'dd-mm-yy | hh:mm:ss'
     * @return a string containing formatted date and time
     */
    public static String getTimeAsString(String pattern){
        LocalDateTime timeNotFormatted = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return timeNotFormatted.format(format);
    }
}
