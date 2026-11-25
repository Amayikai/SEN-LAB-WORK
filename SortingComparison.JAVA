import java.util.Random;

public class SortingComparison {

    private static final int[] SIZES = {10, 100, 1000};
    private static final int MAX_VALUE = 10_000;
    private static final long SEED = 42L; // fixed seed so results are repeatable

    /** Sorts in place and returns the number of comparisons. Stops early if no swaps occur. */
    static long bubbleSort(int[] a) {
        long comparisons = 0;
        for (int pass = 0; pass < a.length - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < a.length - 1 - pass; i++) {
                comparisons++;
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return comparisons;
    }

    /** Sorts in place and returns the number of comparisons. */
    static long selectionSort(int[] a) {
        long comparisons = 0;
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                comparisons++;
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
        return comparisons;
    }

    public static void main(String[] args) {
        Random random = new Random(SEED);
        System.out.printf("%-8s %-20s %-20s%n", "size", "bubble comparisons", "selection comparisons");

        for (int size : SIZES) {
            int[] original = new int[size];
            for (int i = 0; i < size; i++) {
                original[i] = random.nextInt(MAX_VALUE);
            }
            long bubble = bubbleSort(original.clone());
            long selection = selectionSort(original.clone());
            System.out.printf("%-8d %-20d %-20d%n", size, bubble, selection);
        }
    }
}