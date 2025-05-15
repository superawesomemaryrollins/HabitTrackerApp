/**
 * Faciliates completion recording and habit editing.
 *
 * @author  Mary Rollins
 * @version Apr 17, 2025
 */
package tracker;

import java.util.*;
import java.time.*;

public class HabitTracker {

    private List<Habit> userHabits;

    public HabitTracker() {
        userHabits = new ArrayList<Habit>();
    }

    public void handleAddHabit(Scanner scanner) {
        System.out.println("Enter habit name (MUST BE UNIQUE): ");
        String name = scanner.nextLine();

        System.out.println("Enter start date (yyyy-mm-dd): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Choose a category for this habit");
        Category.printCategories();
        String catInput = scanner.nextLine();
        Category category = Category.fromString(catInput);

        System.out.println("Is this habit daily or weekly (Enter d for daily, w for weekly): ");
        char habitRepeat = scanner.nextLine().charAt(0);

        if (habitRepeat == 'd') {
            Habit habit = new DailyHabit(name, startDate, category);
            userHabits.add(habit);
        } else if (habitRepeat == 'w') {
            Habit weekHabit = new WeeklyHabit(name, startDate, category);
            userHabits.add(weekHabit);
        } else {
            System.out.println("Please try again and enter either d or w");
        }
        System.out.println("Habit has been added");
    }

    public void handleViewHabits() {
        if (userHabits.size() == 0) {
            System.out.println("You have no habits!");
            return;
        }
        System.out.println("Here are your current habits: ");
        System.out.println("------------------------\n");
        for (Habit h : userHabits) {
            System.out.printf("%s, %s: started %tD\n", h.getName(), h.getCategory().toString(), h.getStartDate());
        }
    }

    public void handleEditHabit(Scanner scanner) {
        if (userHabits.size() == 0) {
            System.out.println("You have no habits!");
            return;
        }
        handleViewHabits();
        System.out.println("Please enter the name of the habit you wish to edit: ");
        String name = scanner.nextLine();
        Habit habitToChange = null;
        for (Habit h : userHabits) {
            if (h.getName().equals(name)) {
                habitToChange = h;
                break;
            }
        }
        System.out.println(
                "Do you want to edit start date, category, or both? (s for start date, c for category, b for both): ");
        char choice = scanner.nextLine().charAt(0);
        if (choice == 's') {
            System.out.println("Enter new start date (yyyy-mm-dd): ");
            LocalDate newStartDate = LocalDate.parse(scanner.nextLine());
            habitToChange.setStartDate(newStartDate);
        } else if (choice == 'c') {
            Category.printCategories();
            System.out.println("Enter new category: ");
            Category newCategory = Category.fromString(scanner.nextLine());
            habitToChange.setCategory(newCategory);
        } else {
            System.out.println("Enter new start date (yyyy-mm-dd): ");
            LocalDate newStartDate = LocalDate.parse(scanner.nextLine());
            habitToChange.setStartDate(newStartDate);
            Category.printCategories();
            System.out.println("Enter new category: ");
            Category newCategory = Category.fromString(scanner.nextLine());
            habitToChange.setCategory(newCategory);
        }
        System.out.println("Changes have been saved to habit.");
    }
    
    public void handleDeleteHabit(Scanner scanner) {
        if (userHabits.size() == 0) {
            System.out.println("You have no habits!");
            return;
        }
        handleViewHabits();
        System.out.println("Please enter the name of the habit you wish to remove: ");
        String name = scanner.nextLine();
        for (Habit h : userHabits) {
            if (h.getName().equals(name)) {
                userHabits.remove(h);
                break;
            }
        }
        System.out.println("Habit successfully deleted.");
    }

    public void handleMarkCompletion(Scanner scanner) {
        if (userHabits.size() == 0) {
            System.out.println("You have no habits!");
            return;
        }
        handleViewHabits();
        System.out.println("Please enter the name of the habit you completed: ");
        String name = scanner.nextLine();
        System.out.println("Enter the date you completed the habit (yyyy-mm-dd): ");
        LocalDate completeDate = LocalDate.parse(scanner.nextLine());
        for (Habit h : userHabits) {
            if (h.getName().equals(name)) {
                if (h.isValidCompletionDate(completeDate)) {
                    h.markCompleted(completeDate);
                    System.out.println("Habit has been marked completed.");
                } else {
                    System.out.println("Completion date is invalid for this habit. Please try again.");
                }
            }
        }
    }
    
    public void handleViewStats(Scanner scanner) {
        if (userHabits.size() == 0) {
            System.out.println("You have no habits!");
            return;
        }
        handleViewHabits();
        System.out.println("Please enter the name of the habit you want to view stats for: ");
        String name = scanner.nextLine();
        for (Habit h : userHabits) {
            if (h.getName().equals(name)) {
                System.out.printf("\nStreak: %d, Percent of Days Completed: %.4f", h.getStreak(),
                        h.getPercentCompleted());
                break;        
            }
        }
    }

}
