/**
 * Specific implementation of a habit meant to be repeated daily.
 *
 * @author  Mary Rollins
 * @version Apr 07, 2025
 */
package tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailyHabit extends Habit {
    
    public DailyHabit(String name, LocalDate start, Category cat) {
        setName(name);
        setStartDate(start);
        setCategory(cat);
    }

    public boolean isValidCompletionDate(LocalDate date) {
        return true;
    }
    
     public int getStreak() {
        if (getCompletionDates().size() == 0) {
            return 0;
        }
        else if (getCompletionDates().size() == 1) {
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
        for (LocalDate date = latestDate; !date.isBefore(getStartDate()); date = date.minusDays(1)) {
            allDates.add(date);
        }
        for (LocalDate d : allDates) {
            if (getCompletionDates().contains(d)) {
                streakCount++;
            }
            else {
                break;
            }
        }

        return streakCount;
    }
}
