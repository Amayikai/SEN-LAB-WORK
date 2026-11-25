/*CIS/SWE/24/005
  WUNUKEN AMAYIKAI
   SOFTWARE ENGINEERING*/

public class ArrayUtils {

    static int max(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("array must not be null or empty");
        }
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    /** Returns the first index of target, or -1 if not found. */
    static int indexOf(int[] a, int target) {
        if (a == null) {
            return -1;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    static void reverse(int[] a) {
        if (a == null) {
            return;
        }
        reverseRange(a, 0, a.length - 1);
    }

    private static void reverseRange(int[] a, int from, int to) {
        while (from < to) {
            int temp = a[from];
            a[from] = a[to];
            a[to] = temp;
            from++;
            to--;
        }
    }

    /** Keeps the first occurrence of each value, preserving order. */
    static int[] removeDuplicates(int[] a) {
        if (a == null) {
            return new int[0];
        }
        int[] temp = new int[a.length];
        int count = 0;
        for (int value : a) {
            boolean seen = false;
            for (int j = 0; j < count; j++) {
                if (temp[j] == value) {
                    seen = true;
                    break;
                }
            }
            if (!seen) {
                temp[count++] = value;
            }
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    /** Rotates in place using three reversals. Handles k larger than length and negative k. */
    static void rotateRight(int[] a, int k) {
        if (a == null || a.length == 0) {
            return;
        }
        int n = a.length;
        int shift = ((k % n) + n) % n;
        reverseRange(a, 0, n - 1);
        reverseRange(a, 0, shift - 1);
        reverseRange(a, shift, n - 1);
    }

    /** Ascending order. Null, empty and single-element arrays count as sorted. */
    static boolean isSorted(int[] a) {
        if (a == null) {
            return true;
        }
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    /** Merges two sorted arrays into one sorted array. */
    static int[] merge(int[] sortedA, int[] sortedB) {
        int[] result = new int[sortedA.length + sortedB.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < sortedA.length && j < sortedB.length) {
            if (sortedA[i] <= sortedB[j]) {
                result[k++] = sortedA[i++];
            } else {
                result[k++] = sortedB[j++];
            }
        }
        while (i < sortedA.length) {
            result[k++] = sortedA[i++];
        }
        while (j < sortedB.length) {
            result[k++] = sortedB[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] data = {5, 3, 9, 1, 3, 5};

        System.out.println(max(data));                  // 9
        System.out.println(indexOf(data, 9));           // 2
        System.out.println(indexOf(data, 42));          // -1

        int[] toReverse = {1, 2, 3, 4};
        reverse(toReverse);
        System.out.println(java.util.Arrays.toString(toReverse)); // [4, 3, 2, 1]

        System.out.println(java.util.Arrays.toString(removeDuplicates(data))); // [5, 3, 9, 1]

        int[] toRotate = {1, 2, 3, 4, 5};
        rotateRight(toRotate, 2);
        System.out.println(java.util.Arrays.toString(toRotate));  // [4, 5, 1, 2, 3]

        System.out.println(isSorted(new int[] {1, 2, 2, 5}));     // true
        System.out.println(isSorted(new int[] {1, 3, 2}));        // false

        int[] merged = merge(new int[] {1, 4, 7}, new int[] {2, 3, 9, 10});
        System.out.println(java.util.Arrays.toString(merged));    // [1, 2, 3, 4, 7, 9, 10]
    }
}
