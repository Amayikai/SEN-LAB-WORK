public class LoanEligibility {

    // The exercise does not give the rules, so these are assumed. Change them to match your lab.
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 60;
    private static final double MIN_INCOME = 100_000.0;
    private static final int MIN_YEARS_EMPLOYED = 2;

    static boolean isWithinAgeRange(int age) {
        return age >= MIN_AGE && age <= MAX_AGE;
    }

    static boolean hasSufficientIncome(double income) {
        return income >= MIN_INCOME;
    }

    static boolean hasCleanRecord(boolean hasDefaulted) {
        return !hasDefaulted;
    }

    static boolean hasStableEmployment(int yearsEmployed) {
        return yearsEmployed >= MIN_YEARS_EMPLOYED;
    }

    static boolean isEligibleForLoan(int age, double income,
                                     boolean hasDefaulted, int yearsEmployed) {
        return isWithinAgeRange(age)
                && hasSufficientIncome(income)
                && hasCleanRecord(hasDefaulted)
                && hasStableEmployment(yearsEmployed);
    }

    public static void main(String[] args) {
        // Two values per input (one passing, one failing) gives 2^4 = 16 combinations.
        int[] ages = {17, 30};
        double[] incomes = {50_000.0, 250_000.0};
        boolean[] defaults = {false, true};
        int[] years = {0, 5};

        int eligibleCount = 0;
        System.out.printf("%-5s %-10s %-10s %-6s %s%n",
                "age", "income", "defaulted", "years", "eligible");

        for (int age : ages) {
            for (double income : incomes) {
                for (boolean defaulted : defaults) {
                    for (int yrs : years) {
                        boolean result = isEligibleForLoan(age, income, defaulted, yrs);
                        if (result) {
                            eligibleCount++;
                        }
                        System.out.printf("%-5d %-10.0f %-10b %-6d %b%n",
                                age, income, defaulted, yrs, result);
                    }
                }
            }
        }
        System.out.println("Eligible combinations: " + eligibleCount); // 1 of 16
    }
}