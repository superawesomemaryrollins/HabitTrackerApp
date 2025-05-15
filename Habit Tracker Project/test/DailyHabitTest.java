/**
 * Testing class for DailyHabit
 *
 * @author  Mary Rollins
 * @version Apr 26, 2025
 */
package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import tracker.*;

public class DailyHabitTest {
    
    private DailyHabit habit;

    @BeforeEach
    public void setUp() {
        habit = new DailyHabit("Read Bible", LocalDate.of(2025, 4, 1), Category.FAITH);
    }

    @Test
    public void testHabitName() {
        assertEquals("Read Bible", habit.getName());
    }

    @Test
    public void testIsValidCompletionDate() {
        assertTrue(habit.isValidCompletionDate(LocalDate.now()));
    }

    @Test
    public void testPercentCompleted() {
        // Assuming 3 completions since April 1st to April 3rd = 3/3 = 100%
        habit.markCompleted(LocalDate.of(2025, 4, 1));
        habit.markCompleted(LocalDate.of(2025, 4, 2));
        habit.markCompleted(LocalDate.of(2025, 4, 3));

        double percent = habit.getPercentCompleted();
        assertEquals(100.0, percent, 0.01);
    }

    @Test
    public void testMarkCompletedAndGetStreak() {
        habit.markCompleted(LocalDate.of(2025, 4, 1));
        habit.markCompleted(LocalDate.of(2025, 4, 2));
        habit.markCompleted(LocalDate.of(2025, 4, 3));

        assertEquals(3, habit.getStreak());
    }
}



