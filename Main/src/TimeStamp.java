import java.time.LocalTime;

public class TimeStamp {
    private static LocalTime currentTime;
    private static String timestamp;

    public static String getTime() {
        currentTime = LocalTime.now();
        timestamp = currentTime.toString();
        return "["+timestamp+"] ";
    }
}
