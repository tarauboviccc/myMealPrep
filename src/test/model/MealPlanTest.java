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
    private List<String> foodOne = new ArrayList<String>();
    private List<String> foodTwo = new ArrayList<String>();

    @BeforeEach
    void runBeforeEachTesting() {
        foodOne.add("pasta");
        foodOne.add("cheese");
        foodOne.add("milk");
        testMeal1 = new Meal("mac and cheese", 3, foodOne);
        foodTwo.add("tomato");
        foodTwo.add("cucumber");
        foodTwo.add("feta cheese");
        foodTwo.add("olive");
        foodTwo.add("pepper");
        testMeal2 = new Meal("greek salad", 5, foodTwo);
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

    /*@Test
    void testPrintout() {
        testMealPlan.addMeal(testMeal1);
    }*/
}
