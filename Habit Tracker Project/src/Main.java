import java.util.Scanner;
import tracker.HabitTracker;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HabitTracker tracker = new HabitTracker();
        boolean running = true;

        System.out.println("Welcome to the habit tracker!");
        System.out.println("-----------------------");
        while (running) {
            System.out.println("\nPlease select an option by entering the number of the choice: ");
            System.out.println("1. Add a habit");
            System.out.println("2. Edit a habit start date or category");
            System.out.println("3. Delete a habit");
            System.out.println("4. Mark habit as completed");
            System.out.println("5. View habits");
            System.out.println("6. View habit statistics");
            System.out.println("7. Exit");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    tracker.handleAddHabit(scan);
                    break;
                case "2":
                    tracker.handleEditHabit(scan);
                    break;
                case "3":
                    tracker.handleDeleteHabit(scan);
                    break;
                case "4":
                    tracker.handleMarkCompletion(scan);
                    break;
                case "5":
                    tracker.handleViewHabits();
                    break;
                case "6":
                    tracker.handleViewStats(scan);
                    break;
                case "7":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
            }
        }
        scan.close();
    }
}