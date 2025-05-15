/**
 * Every option for habit categorization.
 *
 * @author  Mary Rollins
 * @version Apr 22, 2025
 */
package tracker;

public enum Category {
    HEALTH,
    FAITH,
    PRODUCTIVITY,
    RELATIONSHIPS,
    CHORES,
    OTHER;

    public static void printCategories()
    {
        System.out.println("All categories: ");
        for (Category c : Category.values()) {
            System.out.println("- " + c);
        }
    }

    public static Category fromString(String input) {
        try{
            return Category.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }
    
    
}
