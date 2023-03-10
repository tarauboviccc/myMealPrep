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
        testMeal1 = new Meal("macNcheese", 3, foodOne);
        foodTwo.add("tomato");
        foodTwo.add("cucumber");
        foodTwo.add("feta cheese");
        foodTwo.add("olive");
        foodTwo.add("pepper");
        testMeal2 = new Meal("greekSalad", 5, foodTwo);
        testMealPlan = new MealPlan("My MealPlan");
    }

    @Test
    void testConstructor() {
        List<Meal> list = new ArrayList();
        assertEquals(list, testMealPlan.getMeals());
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

    @Test
    void testRemoveMeal() {
        testMealPlan.addMeal(testMeal1);
        List<Meal> m1 = new ArrayList<Meal>();
        //m1.add(testMeal1);
        testMealPlan.removeMeal(testMeal1);
        assertEquals(m1, testMealPlan.getMeals());
        testMealPlan.addMeal(testMeal1);
        testMealPlan.addMeal(testMeal2);
        m1.add(testMeal2);
        testMealPlan.removeMeal(testMeal1);
        assertEquals(m1, testMealPlan.getMeals());
    }

    @Test
    void testPrintMealPlan() {
        List<String> g2 = new ArrayList<String>();
        g2.add("Meal [0]: macNcheese");
        g2.add("Meal [1]: greekSalad");
        testMealPlan.addMeal(testMeal1);
        testMealPlan.addMeal(testMeal2);
        assertEquals(g2, testMealPlan.printMealPlan());
    }
}
