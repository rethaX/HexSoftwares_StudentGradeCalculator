import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // Save students to file
    public static void saveStudents(String filename, List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Load students from file
    public static List<Student> loadStudents(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}