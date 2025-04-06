public class Main {
    public static void main(String[] args) {
        // Define subjects for the course
        String[] subjects = {"Math", "Science", "English", "History"};

        // Start the console interface
        ConsoleUI ui = new ConsoleUI(subjects);
        ui.start();
    }
}