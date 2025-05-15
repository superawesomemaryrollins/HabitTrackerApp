/**
 * Testing class for WeeklyHabit
 *
 * @author  Mary Rollins
 * @version Apr 28, 2025
 */
package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import tracker.*;

public class WeeklyHabitTest {
    private WeeklyHabit habit;

    @BeforeEach
    public void setUp() {
        habit = new WeeklyHabit("Strength Train", LocalDate.of(2025, 4, 28), Category.HEALTH);
    }

    @Test
    public void testDayOfWeek() {
        assertEquals(DayOfWeek.MONDAY, habit.getDayOfWeek());
    }

    @Test
    public void testIsValidCompletionDate() {
        assertTrue(habit.isValidCompletionDate(LocalDate.of(2025, 5, 5)));
        assertFalse(habit.isValidCompletionDate(LocalDate.of(2025, 4, 29)));
        assertFalse(habit.isValidCompletionDate(LocalDate.of(2025, 5, 1)));
    }

    @Test
    public void testStreak() {
        habit.markCompleted(LocalDate.of(2025, 4, 28));
        habit.markCompleted(LocalDate.of(2025, 5, 5));

        assertEquals(2, habit.getStreak());
    }


}
