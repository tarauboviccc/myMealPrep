package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MealTest {
    private Meal testMeal1;
    private Meal testMeal2;

    @BeforeEach
    void runBeforeEachTesting() {
        testMeal1 = new Meal("mac and cheese", 3, List.of("pasta", "cheese", "milk"));
        testMeal2 = new Meal("greek salad", 5,
                List.of("tomato", "cucumber", "feta cheese", "olive", "pepper"));
    }

    @Test
    void testConstructor() {
        assertEquals("mac and cheese", testMeal1.getMealName());
        assertEquals(3, testMeal1.getNumberOfIngredients());
        assertEquals(List.of("pasta", "cheese", "milk"), testMeal1.getListOfIngredients());
    }
}