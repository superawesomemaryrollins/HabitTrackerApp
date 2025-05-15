
/**
 * Contains basic attributes for all habits
 *
 * @author  Mary Rollins
 * @version Apr 07, 2025
 */
package tracker;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public abstract class Habit {
    private String name;
    private LocalDate startDate;
    private List<LocalDate> completionDates = new ArrayList<>();
    private Category category;

    public void markCompleted(LocalDate date) {
        if (date.isBefore(startDate)) {
            return;
        }
        for (LocalDate d : completionDates) {
            if (d.equals(date)) {
                return;
            }
        }
        completionDates.add(date);
    }

    public abstract int getStreak();
    
    public double getPercentCompleted() {
        if (completionDates.size() == 0) {
            return 0;
        }
        double percentCompleted = 0;
        LocalDate latestDate = completionDates.get(0);
        for (LocalDate d : completionDates) {
            if (d.isAfter(latestDate)) {
                latestDate = d;
            }
        }
        List<LocalDate> allDates = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(latestDate); date = date.plusDays(1)) {
            allDates.add(date);
        }
        int totalCompletions = 0;
        for (LocalDate d : allDates) {
            if (completionDates.contains(d)) {
                totalCompletions++;
            }
        }
        percentCompleted = (totalCompletions / allDates.size()) * 100;
        return percentCompleted;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate start) {
        this.startDate = start;
    }

    public void setCategory(Category cat) {
        this.category = cat;
    }

    public List<LocalDate> getCompletionDates() {
        return completionDates;
    }

    public abstract boolean isValidCompletionDate(LocalDate date);
}
