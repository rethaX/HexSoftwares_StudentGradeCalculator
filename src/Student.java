import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {
    private String name;
    private String studentId;
    private double[] grades;
    private String[] subjects;

    public Student(String name, String studentId, String[] subjects, double[] grades) {
        this.name = name;
        this.studentId = studentId;
        this.subjects = subjects;
        this.grades = grades;
    }

    public double calculateAverage() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }

    public boolean hasPassed() {
        return calculateAverage() >= 40;
    }

    public String getLetterGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        if (avg >= 40) return "E";
        return "F";
    }

    public void displayGrades() {
        System.out.println("\nGrades for " + name + ":");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%-10s: %.2f\n", subjects[i], grades[i]);
        }
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(other.calculateAverage(), this.calculateAverage());
    }

    // Getters
    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public double[] getGrades() { return grades; }
    public String[] getSubjects() { return subjects; }
}