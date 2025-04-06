import java.util.Scanner;
import java.util.List;

public class ConsoleUI {
    private Scanner scanner;
    private GradeCalculator calculator;

    public ConsoleUI(String[] subjects) {
        this.scanner = new Scanner(System.in);
        this.calculator = new GradeCalculator(subjects);
        System.out.println("Loaded " + calculator.getStudents().size() + " student(s)");
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> viewStudentGrades();
                case 4 -> viewClassStats();
                case 5 -> {
                    FileHandler.saveStudents(calculator.getStudents());
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nSTUDENT GRADE CALCULATOR");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. View Student Grades");
        System.out.println("4. View Class Stats");
        System.out.println("5. Exit");
        System.out.print("Choose option: ");
    }

    private void addStudent() {
        System.out.print("\nEnter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        String[] subjects = calculator.getSubjects();
        double[] grades = new double[subjects.length];

        System.out.println("Enter grades (0-100):");
        for (int i = 0; i < subjects.length; i++) {
            while (true) {
                System.out.print(subjects[i] + ": ");
                double grade = scanner.nextDouble();
                scanner.nextLine();

                if (grade >= 0 && grade <= 100) {
                    grades[i] = grade;
                    break;
                }
                System.out.println("Invalid! Grade must be 0-100");
            }
        }

        Student student = new Student(name, id, subjects, grades);
        calculator.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private void viewAllStudents() {
        List<Student> students = calculator.getStudents();
        if (students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }

        System.out.println("\nALL STUDENTS");
        System.out.println("------------");
        for (Student s : students) {
            System.out.printf("%s (%s) | Avg: %.2f | Grade: %s | %s\n",
                    s.getName(), s.getStudentId(), s.calculateAverage(),
                    s.getLetterGrade(), s.hasPassed() ? "Passed" : "Failed");
        }
    }

    private void viewStudentGrades() {
        List<Student> students = calculator.getStudents();
        if (students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }

        System.out.println("\nSELECT STUDENT");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s\n", i+1, students.get(i).getName());
        }

        System.out.print("\nEnter student number: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > students.size()) {
            System.out.println("Invalid selection!");
            return;
        }

        Student s = students.get(num-1);
        s.displayGrades();
        System.out.printf("\nAverage: %.2f | Grade: %s | Status: %s\n",
                s.calculateAverage(), s.getLetterGrade(), s.hasPassed() ? "Passed" : "Failed");
    }

    private void viewClassStats() {
        System.out.println("\nCLASS STATISTICS");
        System.out.println("---------------");
        System.out.printf("Average Grade: %.2f\n", calculator.getClassAverage());
        System.out.printf("Pass Rate: %.1f%%\n", calculator.getPassPercentage());
        System.out.println("Total Students: " + calculator.getStudents().size());
    }
}