/*CIS/SWE/24/005
  WUNUKEN AMAYIKAI
   SOFTWARE ENGINEERING*/

public class Gradebook {

    /** One row of the grade lookup table: a minimum score and the grade it earns. */
    record GradeBand(double minScore, String grade) {}

    // Table data, kept separate from the code that uses it. Must stay sorted from highest to lowest.
    // The cut-offs are assumed, so change them to match your lab.
    private static final GradeBand[] GRADE_TABLE = {
        new GradeBand(70, "A"),
        new GradeBand(60, "B"),
        new GradeBand(50, "C"),
        new GradeBand(45, "D"),
        new GradeBand(40, "E"),
        new GradeBand(0,  "F")
    };

    static String gradeFor(double score) {
        for (GradeBand band : GRADE_TABLE) {
            if (score >= band.minScore()) {
                return band.grade();
            }
        }
        return "F"; // only reached for negative scores
    }

    /** marks[student][assessment] */
    static double studentAverage(double[][] marks, int student) {
        double sum = 0;
        for (double mark : marks[student]) {
            sum += mark;
        }
        return sum / marks[student].length;
    }

    static double assessmentAverage(double[][] marks, int assessment) {
        double sum = 0;
        for (double[] row : marks) {
            sum += row[assessment];
        }
        return sum / marks.length;
    }

    static double assessmentHighest(double[][] marks, int assessment) {
        double highest = marks[0][assessment];
        for (double[] row : marks) {
            if (row[assessment] > highest) {
                highest = row[assessment];
            }
        }
        return highest;
    }

    static double assessmentLowest(double[][] marks, int assessment) {
        double lowest = marks[0][assessment];
        for (double[] row : marks) {
            if (row[assessment] < lowest) {
                lowest = row[assessment];
            }
        }
        return lowest;
    }

    static void requireRectangular(double[][] marks) {
        if (marks == null || marks.length == 0 || marks[0].length == 0) {
            throw new IllegalArgumentException("marks must not be null or empty");
        }
        for (double[] row : marks) {
            if (row.length != marks[0].length) {
                throw new IllegalArgumentException("every student needs the same number of assessments");
            }
        }
    }

    static void printTable(String[] students, String[] assessments, double[][] marks) {
        requireRectangular(marks);

        // Header
        System.out.printf("%-10s", "Student");
        for (String name : assessments) {
            System.out.printf("%9s", name);
        }
        System.out.printf("%9s %-5s%n", "Average", "Grade");

        // One row per student
        for (int s = 0; s < marks.length; s++) {
            System.out.printf("%-10s", students[s]);
            for (double mark : marks[s]) {
                System.out.printf("%9.1f", mark);
            }
            double avg = studentAverage(marks, s);
            System.out.printf("%9.2f %-5s%n", avg, gradeFor(avg));
        }

        // Summary rows per assessment
        System.out.println();
        System.out.printf("%-10s", "Average");
        for (int a = 0; a < assessments.length; a++) {
            System.out.printf("%9.2f", assessmentAverage(marks, a));
        }
        System.out.println();

        System.out.printf("%-10s", "Highest");
        for (int a = 0; a < assessments.length; a++) {
            System.out.printf("%9.1f", assessmentHighest(marks, a));
        }
        System.out.println();

        System.out.printf("%-10s", "Lowest");
        for (int a = 0; a < assessments.length; a++) {
            System.out.printf("%9.1f", assessmentLowest(marks, a));
        }
        System.out.println();
    }

    /** Finds the overall highest and lowest marks and prints who got them. */
    static void printExtremes(String[] students, String[] assessments, double[][] marks) {
        int highS = 0, highA = 0, lowS = 0, lowA = 0;
        for (int s = 0; s < marks.length; s++) {
            for (int a = 0; a < marks[s].length; a++) {
                if (marks[s][a] > marks[highS][highA]) {
                    highS = s;
                    highA = a;
                }
                if (marks[s][a] < marks[lowS][lowA]) {
                    lowS = s;
                    lowA = a;
                }
            }
        }
        System.out.printf("%nHighest score: %.1f (%s, %s)%n",
                marks[highS][highA], students[highS], assessments[highA]);
        System.out.printf("Lowest score:  %.1f (%s, %s)%n",
                marks[lowS][lowA], students[lowS], assessments[lowA]);
    }

    public static void main(String[] args) {
        String[] students = {"Ada", "Musa", "Zainab", "Chinedu"};
        String[] assessments = {"Quiz", "Test", "Project", "Exam"};
        double[][] marks = {
            {82, 75, 90, 78},   // Ada
            {55, 62, 48, 51},   // Musa
            {95, 88, 92, 97},   // Zainab
            {38, 42, 35, 40}    // Chinedu
        };

        printTable(students, assessments, marks);
        printExtremes(students, assessments, marks);
    }
}