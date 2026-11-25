/*CIS/SWE/24/005
  WUNUKEN AMAYIKAI
   SOFTWARE ENGINEERING*/

public class TimeAndNumberUtils {

    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    private static final int LEAP_CYCLE = 4;
    private static final int CENTURY = 100;
    private static final int LEAP_CENTURY_CYCLE = 400;

    private static final int DECIMAL_BASE = 10;

    /** Returns {hours, minutes, seconds}. */
    static int[] splitSeconds(int totalSeconds) {
        if (totalSeconds < 0) {
            throw new IllegalArgumentException("totalSeconds must be >= 0");
        }
        int hours = totalSeconds / SECONDS_PER_HOUR;
        int minutes = (totalSeconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE;
        int seconds = totalSeconds % SECONDS_PER_MINUTE;
        return new int[] {hours, minutes, seconds};
    }

    static boolean isLeapYear(int year) {
        return (year % LEAP_CYCLE == 0 && year % CENTURY != 0)
                || (year % LEAP_CENTURY_CYCLE == 0);
    }

    static int digitSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += Math.abs(n % DECIMAL_BASE); // abs handles negatives
            n /= DECIMAL_BASE;
        }
        return sum;
    }

    public static void main(String[] args) {
        // splitSeconds
        System.out.println(java.util.Arrays.toString(splitSeconds(3661)));  // [1, 1, 1]
        System.out.println(java.util.Arrays.toString(splitSeconds(0)));     // [0, 0, 0]
        System.out.println(java.util.Arrays.toString(splitSeconds(59)));    // [0, 0, 59]
        System.out.println(java.util.Arrays.toString(splitSeconds(86399))); // [23, 59, 59]

        // isLeapYear
        System.out.println(isLeapYear(2000)); // true
        System.out.println(isLeapYear(1900)); // false
        System.out.println(isLeapYear(2024)); // true
        System.out.println(isLeapYear(2023)); // false

        // digitSum
        System.out.println(digitSum(12345)); // 15
        System.out.println(digitSum(-907));  // 16
        System.out.println(digitSum(0));     // 0
    }
}