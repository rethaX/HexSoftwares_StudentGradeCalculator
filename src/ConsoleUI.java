import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private GradeCalculator calculator;

    public ConsoleUI(String[] subjects) {
        this.scanner = new Scanner(System.in);
        this.calculator = new GradeCalculator(subjects);
    }

    // Main menu
    public void start() {
        while (true) {
            System.out.println("\nStudent Grade Calculator");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Class Stats");
            System.out.println("4. Save Data");
            System.out.println("5. Load Data");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> viewStats();
                case 4 -> saveData();
                case 5 -> loadData();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        Student student = new Student(name, id, calculator.getSubjects());
        double[] grades = new double[calculator.getSubjects().length];

        for (int i = 0; i < grades.length; i++) {
            System.out.printf("Enter grade for %s: ", calculator.getSubjects()[i]);
            grades[i] = scanner.nextDouble();
        }
        scanner.nextLine(); // consume newline

        student.setGrades(grades);
        calculator.addStudent(student);
        System.out.println("Student added!");
    }

    private void viewStudents() {
        List<Student> students = calculator.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        for (Student s : students) {
            System.out.printf("\n%s (%s) - Average: %.1f - Grade: %s - %s\n",
                    s.getName(), s.getStudentId(), s.getAverage(),
                    s.getLetterGrade(), s.isPassed() ? "Passed" : "Failed");

            for (int i = 0; i < s.getSubjects().length; i++) {
                System.out.printf("  %s: %.1f\n", s.getSubjects()[i], s.getGrades()[i]);
            }
        }
    }

    private void viewStats() {
        System.out.printf("\nClass Average: %.1f\n", calculator.getClassAverage());
        System.out.printf("Pass Percentage: %.1f%%\n", calculator.getPassPercentage());
        System.out.println("Total Students: " + calculator.getStudents().size());
    }

    private void saveData() {
        FileHandler.saveStudents("students.dat", calculator.getStudents());
        System.out.println("Data saved!");
    }

    private void loadData() {
        List<Student> loaded = FileHandler.loadStudents("students.dat");
        calculator = new GradeCalculator(calculator.getSubjects());
        loaded.forEach(calculator::addStudent);
        System.out.println("Data loaded!");
    }
}