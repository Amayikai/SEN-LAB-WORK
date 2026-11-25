/*CIS/SWE/24/005
  WUNUKEN AMAYIKAI
   SOFTWARE ENGINEERING*/

import java.util.Arrays;
import java.util.Comparator;

public class StudentLeaderboard {

    record Student(String name, int score) {}

    static double average(Student[] students) {
        if (students == null || students.length == 0) {
            throw new IllegalArgumentException("students must not be null or empty");
        }
        int total = 0;
        for (Student s : students) {
            total += s.score();
        }
        return (double) total / students.length;
    }

    static int countAboveAverage(Student[] students, double average) {
        int count = 0;
        for (Student s : students) {
            if (s.score() > average) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Student[] class1 = {
            new Student("Ada", 88), new Student("Musa", 72), new Student("Zainab", 95)
        };

        Arrays.sort(class1, Comparator.comparingInt(Student::score).reversed());

        System.out.printf("%-6s %-10s %5s%n", "Rank", "Name", "Score");
        for (int i = 0; i < class1.length; i++) {
            System.out.printf("%-6d %-10s %5d%n", i + 1, class1[i].name(), class1[i].score());
        }

        Student top = class1[0]; // first element after a descending sort
        double avg = average(class1);
        int aboveAverage = countAboveAverage(class1, avg);

        System.out.println("Top scorer: " + top.name() + " (" + top.score() + ")"); // Zainab (95)
        System.out.printf("Class average: %.2f%n", avg);                            // 85.00
        System.out.println("Students above average: " + aboveAverage);              // 2
    }
}
