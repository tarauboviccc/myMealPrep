package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroceryListTest {
    private GroceryList testGroceryList;
    private Meal testMeal1;
    private Meal testMeal2;

    @BeforeEach
    void runBeforeTesting() {
        testMeal1 = new Meal("mac and cheese", 3, List.of("pasta", "cheese", "milk"));
        testMeal2 = new Meal("greek salad", 5,
                List.of("tomato", "cucumber", "feta cheese", "olive", "pepper"));
        testGroceryList = new GroceryList();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testGroceryList.getNumberOfGroceries());
    }

    @Test
    void testAddGrocery() {
        List<String> g1 = new ArrayList<String>();
        g1.add("pasta");
        g1.add("cheese");
        g1.add("milk");
        testGroceryList.addGrocery(testMeal1.getNumberOfIngredients(), testMeal1.getListOfIngredients());
        assertEquals(g1, testGroceryList.getGroceries());
        g1.add("tomato"); //, "cucumber", "feta cheese", "olive", "pepper"
        g1.add("cucumber");
        g1.add("feta cheese");
        g1.add("olive");
        g1.add("pepper");
        testGroceryList.addGrocery(testMeal2.getNumberOfIngredients(), testMeal2.getListOfIngredients());
        assertEquals(g1, testGroceryList.getGroceries());
    }

    @Test
    void testRemoveGrocery() {
        List<String> g1 = new ArrayList<String>();
        g1.add("pasta");
        g1.add("milk");
        testGroceryList.addGrocery(testMeal1.getNumberOfIngredients(), testMeal1.getListOfIngredients());
        testGroceryList.removeGrocery("cheese");
        assertEquals(g1, testGroceryList.getGroceries());
    }

}
