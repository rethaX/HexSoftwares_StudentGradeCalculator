import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {
    private String name;
    private String studentId;
    private double[] grades;
    private String[] subjects;

    public Student(String name, String studentId, String[] subjects) {
        this.name = name;
        this.studentId = studentId;
        this.subjects = subjects;
        this.grades = new double[subjects.length];
    }

    // Calculate average grade
    public double getAverage() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }

    // Check if student passed (average >= 40)
    public boolean isPassed() {
        return getAverage() >= 40;
    }

    // Get letter grade based on average
    public String getLetterGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        if (avg >= 40) return "E";
        return "F";
    }

    // For sorting students by average (highest first)
    @Override
    public int compareTo(Student other) {
        return Double.compare(other.getAverage(), this.getAverage());
    }

    // Getters and setters
    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public double[] getGrades() { return grades; }
    public String[] getSubjects() { return subjects; }
    public void setGrades(double[] grades) { this.grades = grades; }
}