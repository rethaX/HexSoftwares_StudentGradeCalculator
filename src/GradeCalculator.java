import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradeCalculator {
    private List<Student> students;
    private String[] subjects;

    public GradeCalculator(String[] subjects) {
        this.students = new ArrayList<>();
        this.subjects = subjects;
    }

    // Add a new student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Get all students sorted by average
    public List<Student> getStudents() {
        Collections.sort(students);
        return students;
    }

    // Calculate class average
    public double getClassAverage() {
        if (students.isEmpty()) return 0;

        double total = 0;
        for (Student s : students) {
            total += s.getAverage();
        }
        return total / students.size();
    }

    // Get pass percentage
    public double getPassPercentage() {
        if (students.isEmpty()) return 0;

        int passed = 0;
        for (Student s : students) {
            if (s.isPassed()) passed++;
        }
        return (double) passed / students.size() * 100;
    }
}