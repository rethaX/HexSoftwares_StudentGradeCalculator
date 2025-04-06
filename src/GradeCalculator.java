import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradeCalculator {
    private List<Student> students;
    private String[] subjects;

    public GradeCalculator(String[] subjects) {
        this.subjects = subjects;
        this.students = FileHandler.loadStudents();
    }

    public void addStudent(Student student) {
        students.add(student);
        FileHandler.saveStudents(students);
    }

    public List<Student> getStudents() {
        Collections.sort(students);
        return students;
    }

    public double getClassAverage() {
        if (students.isEmpty()) return 0;
        double total = 0;
        for (Student s : students) {
            total += s.calculateAverage();
        }
        return total / students.size();
    }

    public double getPassPercentage() {
        if (students.isEmpty()) return 0;
        int passed = 0;
        for (Student s : students) {
            if (s.hasPassed()) passed++;
        }
        return (double) passed / students.size() * 100;
    }

    public String[] getSubjects() {
        return subjects;
    }
}