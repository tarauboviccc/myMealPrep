package persistence;

import model.MealPlan;
import model.Meal;

import org.junit.jupiter.api.Test;
import java.util.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// CITATIONS: used example given in JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MealPlan mp = new MealPlan("My MealPlan");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMealPlan() {
        try {
            MealPlan mp = new MealPlan("My MealPlan");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMealPlan.json");
            writer.open();
            writer.write(mp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMealPlan.json");
            mp = reader.read();
            List<Meal> emptyMealList = new ArrayList<Meal>();
            assertEquals(0, mp.getNumberOfMeals());
            assertEquals(emptyMealList, mp.getMeals());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralMealPlan() {
        try {
            MealPlan mp = new MealPlan("My MealPlan");
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
            mp.addMeal(new Meal("macaroni", 3, foodOne));
            mp.addMeal(new Meal("salad", 5, foodTwo));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMealPlan.json");
            writer.open();
            writer.write(mp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMealPlan.json");
            mp = reader.read();
            List<Meal> meals = mp.getMeals();
            assertEquals(2, meals.size());
            checkMeal("macaroni", 3, foodOne, meals.get(0));
            checkMeal("salad", 5, foodTwo, meals.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}


