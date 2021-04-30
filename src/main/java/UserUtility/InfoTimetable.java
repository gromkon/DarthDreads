package UserUtility;

public class InfoTimetable {
    private static String day, month, year, time;
    private static int dayStart = 0;

    public static String getDay() {
        return day;
    }

    public static void setDay(String day) {
        InfoTimetable.day = day;
    }

    public static String getMonth() {
        return month;
    }

    public static void setMonth(String month) {
        InfoTimetable.month = month;
    }

    public static String getYear() {
        return year;
    }

    public static void setYear(String year) {
        InfoTimetable.year = year;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        InfoTimetable.time = time;
    }

    public static int getDayStart() {
        return dayStart;
    }

    public static void setDayStart(int dayStart) {
        InfoTimetable.dayStart = dayStart;
    }
}
