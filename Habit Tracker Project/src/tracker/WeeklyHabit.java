/**
 * Specific implementation of a habit to be repeated weekly.
 *
 * @author  Mary Rollins
 * @version Apr 17, 2025
 */
package tracker;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class WeeklyHabit extends Habit {
    
    private DayOfWeek dayOfWeek;

    public WeeklyHabit(String name, LocalDate start,Category cat) {
        setName(name);
        setStartDate(start);
        this.dayOfWeek = getStartDate().getDayOfWeek();
        setCategory(cat);
    }

    public boolean isValidCompletionDate(LocalDate date) {
        if (this.dayOfWeek.equals(date.getDayOfWeek())) {
            return true;
        }
        return false;
    }

    public int getStreak() {
        if (getCompletionDates().size() == 0) {
            return 0;
        } else if (getCompletionDates().size() == 1) {
            return 1;
        }
        int streakCount = 0;
        LocalDate latestDate = getCompletionDates().get(0);
        for (LocalDate d : getCompletionDates()) {
            if (d.isAfter(latestDate)) {
                latestDate = d;
            }
        }
        List<LocalDate> allDates = new ArrayList<>();
        for (LocalDate date = latestDate; !date.isBefore(getStartDate()); date = date.minusDays(7)) {
            allDates.add(date);
        }
        for (LocalDate d : allDates) {
            if (getCompletionDates().contains(d)) {
                streakCount++;
            } else {
                break;
            }
        }

        return streakCount;
    }
    
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }


    
}
