package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealPlanTest {
    private MealPlan testMealPlan;
    private Meal testMeal1;
    private Meal testMeal2;

    @BeforeEach
    void runBeforeEachTesting() {
        testMeal1 = new Meal("mac and cheese", 3, List.of("pasta", "cheese", "milk"));
        testMeal2 = new Meal("greek salad", 5,
                List.of("tomato", "cucumber", "feta cheese", "olive", "pepper"));
        testMealPlan = new MealPlan(2);
    }

    @Test
    void testConstructor() {
        assertEquals(2, testMealPlan.getNumberOfMeals());
    }

    @Test
    void testAddMeal() {
        testMealPlan.addMeal(testMeal1);
        List<Meal> m1 = new ArrayList<Meal>();
        m1.add(testMeal1);
        assertEquals(m1, testMealPlan.getMeals());
        testMealPlan.addMeal(testMeal2);
        m1.add(testMeal2);
        assertEquals(m1, testMealPlan.getMeals());
    }
}
