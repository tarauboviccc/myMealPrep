package persistence;

import model.GroceryList;
import model.Meal;
import model.MealPlan;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//CITATIONS: used test example given in JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MealPlan mp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMealPlan() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMealPlan.json");
        try {
            List<Meal> emptyList = new ArrayList<Meal>();
            MealPlan mp = reader.read();
            assertEquals(0, mp.getNumberOfMeals());
            assertEquals(emptyList, mp.getMeals());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    /*
    @Test
    void testReaderGeneralMealPlan() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMealPlan.json");
        try {
            MealPlan mp = reader.read();
            List<Meal> meals = mp.getMeals();
            assertEquals(2, meals.size());
            List<String> foodOne = new ArrayList<String>();
            List<String> foodTwo = new ArrayList<String>();
            foodOne.add("pasta");
            foodOne.add("cheese");
            foodOne.add("milk");
            foodTwo.add("tomato");
            foodTwo.add("cucumber");
            foodTwo.add("feta cheese");
            foodTwo.add("olive");
            foodTwo.add("pepper");
            checkMeal("macaroni", 2, foodOne, meals.get(0));
            checkMeal("salad", 5, foodTwo, meals.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

     */
}