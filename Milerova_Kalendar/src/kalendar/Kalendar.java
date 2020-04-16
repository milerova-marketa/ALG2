package kalendar;

/**
 *
 * @author Marketa.Milerova
 */
public class Kalendar {

    private int day;
    private int month;
    private int year;

    public Kalendar(int day, int month, int year) {
        checkDate(day, month, year);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDayInWeek() {
        int month = this.month;
        int year = this.year;
        int day = this.day;
        if (month == 1) {
            month = 13;
            year--;
        }
        if (month == 2) {
            month = 14;
            year--;
        }
        int q = day;
        int m = month;
        int k = year % 100;
        int j = year / 100;
        int h = q + 13 * (m + 1) / 5 + k + k / 4 + j / 4 + 5 * j;
        h = h % 7;
        return ((h + 5) % 7) + 1;
    }

    public String makeCalendar() {
        StringBuilder calendar = new StringBuilder();
        calendar.append(String.format("%10s %4d%n", monthToString(month), year));
        calendar.append("PO UT ST CT PA SO NE %n");
        int tempDay = this.day;
        this.day = 1;
        int dayStart = getDayInWeek();
        for (int i = 0; i < dayStart - 1; i++) {
            calendar.append("   ");
        }
        for (int i = 0; i < daysInMonths(year)[month - 1]; i++) {
            calendar.append(String.format("%2d ", this.day));
            if (getDayInWeek() == 7) {
                calendar.append("%n");
            }
            this.day++;
        }
        int dayEnd = getDayInWeek();
        for (int i = 0; i < 7 - dayEnd + 1; i++) {
            calendar.append("   ");
        }
        calendar.append("%n");
        this.day = tempDay;
        return calendar.toString();
    }

    public void nextMonth() {
        day = 1;
        if (month == 12) {
            year++;
            month = 1;
        } else {
            month++;
        }
    }

    public void previousMonth() {
        day = 1;
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
    }

    private static int[] daysInMonths(int year) {
        int[] daysInMonths = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        daysInMonths[1] = isYearLeap(year) ? 29 : 28;
        return daysInMonths;
    }

    public static boolean isYearLeap(int year) {
        if (year % 4 != 0) {
            return false;
        }
        if (year % 100 != 0) {
            return true;
        }
        return year % 400 == 0;
    }

    public static int daysInYear(int year) {
        return isYearLeap(year) ? 366 : 365;
    }

    public static void main(String[] args) {
        Kalendar k1 = new Kalendar(6, 6, 2020);
        System.out.printf(k1.makeCalendar());
        k1.previousMonth();
        System.out.printf(k1.makeCalendar());

    }

    private void checkDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Zadejte validni mesic.");
        }
        if (day < 1 || day > daysInMonths(year)[month - 1]) {
            throw new IllegalArgumentException("Zadejte validni den");
        }
    }

    private static String monthToString(int month) {
        switch (month) {
            case 1:
                return "Leden";
            case 2:
                return "Unor";
            case 3:
                return "Brezen";
            case 4:
                return "Duben";
            case 5:
                return "Kveten";
            case 6:
                return "Cerven";
            case 7:
                return "Cervenec";
            case 8:
                return "Srpen";
            case 9:
                return "Zari";
            case 10:
                return "Rijen";
            case 11:
                return "Listopad";
            case 12:
                return "Prosinec";
        }
        return null;
    }
}
