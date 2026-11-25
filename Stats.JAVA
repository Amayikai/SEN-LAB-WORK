import java.util.Arrays;

public class Stats {

    private static void requireNonEmpty(double[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("data must not be null or empty");
        }
    }

    static double mean(double[] data) {
        requireNonEmpty(data);
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    /** Works on a sorted copy, so the caller's array is not modified. */
    static double median(double[] data) {
        requireNonEmpty(data);
        double[] sorted = data.clone();
        Arrays.sort(sorted);
        int mid = sorted.length / 2;
        if (sorted.length % 2 == 0) {
            return (sorted[mid - 1] + sorted[mid]) / 2;
        }
        return sorted[mid];
    }

    /** Most frequent value. If there is a tie, the smallest of the tied values is returned. */
    static double mode(double[] data) {
        requireNonEmpty(data);
        double[] sorted = data.clone();
        Arrays.sort(sorted);

        double bestValue = sorted[0];
        int bestCount = 1;
        int currentCount = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (Double.compare(sorted[i], sorted[i - 1]) == 0) {
                currentCount++;
            } else {
                currentCount = 1;
            }
            if (currentCount > bestCount) {
                bestCount = currentCount;
                bestValue = sorted[i];
            }
        }
        return bestValue;
    }

    /** Population standard deviation (divides by n, not n - 1). */
    static double standardDeviation(double[] data) {
        requireNonEmpty(data);
        double mean = mean(data);
        double sumSquaredDiffs = 0;
        for (double value : data) {
            double diff = value - mean;
            sumSquaredDiffs += diff * diff;
        }
        return Math.sqrt(sumSquaredDiffs / data.length);
    }

    public static void main(String[] args) {
        double[] data = {2, 4, 4, 4, 5, 5, 7, 9};
        System.out.printf("mean   = %.4f%n", mean(data));              // 5.0000
        System.out.printf("median = %.4f%n", median(data));            // 4.5000
        System.out.printf("mode   = %.4f%n", mode(data));              // 4.0000
        System.out.printf("stdDev = %.4f%n", standardDeviation(data)); // 2.0000

        double[] odd = {1, 2, 3, 4, 5};
        System.out.printf("median = %.4f%n", median(odd));             // 3.0000
        System.out.printf("stdDev = %.4f%n", standardDeviation(odd));  // 1.4142

        try {
            mean(new double[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        try {
            median(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}