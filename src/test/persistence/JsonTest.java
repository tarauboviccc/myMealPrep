package persistence;

import model.Meal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMeal(String mealName, int numIngredients, List<String> loi, Meal newMeal) {
        assertEquals(mealName, newMeal.getMealName());
        assertEquals(numIngredients, newMeal.getNumberOfIngredients());
        assertEquals(loi, newMeal.getListOfIngredients());
    }
}
