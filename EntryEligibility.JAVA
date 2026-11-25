public class EntryEligibility {

    private static final int ADULT_AGE = 18;
    private static final int STATUS_ACTIVE = 1;

    // Before: if (!(age < 18 || !hasId) == true) { ... }
    // After De Morgan: age >= 18 && hasId
    static boolean isAdult(int age) {
        return age >= ADULT_AGE;
    }

    static boolean canEnter(int age, boolean hasValidId) {
        return isAdult(age) && hasValidId;
    }

    // Before: boolean flag = status == 1 ? true : false;
    static boolean isActive(int status) {
        return status == STATUS_ACTIVE;
    }

    public static void main(String[] args) {
        System.out.println(canEnter(17, true));   // false (too young)
        System.out.println(canEnter(18, true));   // true
        System.out.println(canEnter(30, false));  // false (no ID)
        System.out.println(canEnter(17, false));  // false

        System.out.println(isActive(1));          // true
        System.out.println(isActive(0));          // false
    }
}