package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MealTest {
    private Meal testMeal1;
    private Meal testMeal2;
    private List<String> foodOne = new ArrayList<String>();
    private List<String> foodTwo = new ArrayList<String>();

    @BeforeEach
    void runBeforeEachTesting() {
        foodOne.add("pasta");
        foodOne.add("cheese");
        foodOne.add("milk");
        testMeal1 = new Meal("macaroni", 3, foodOne);
        foodTwo.add("tomato");
        foodTwo.add("cucumber");
        foodTwo.add("feta cheese");
        foodTwo.add("olive");
        foodTwo.add("pepper");
        testMeal2 = new Meal("salad", 5, foodTwo);
    }

    @Test
    void testMealConstructor() {
        assertEquals("macaroni", testMeal1.getMealName());
        assertEquals(3, testMeal1.getNumberOfIngredients());
        assertEquals(foodOne, testMeal1.getListOfIngredients());
    }
}