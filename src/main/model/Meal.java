package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a meal containing a name, number of ingredients needed for its preparation and list of that ingredients
public class Meal implements Writable {
    private String mealName;                                                // meal's name
    private int numberOfIngredients;                                        // number of ingredients
    private List<String> listOfIngredients = new ArrayList<String>();            // list of ingredients

    /*
     * REQUIRES: mealName has a non-zero length
     * EFFECTS: meal's name is set to mealName;
     *          number of ingredients is set to numberOfIngredients
     *          list of in ingredients set to ingredients
     */
    public Meal(String mealName, int numberOfIngredients, List<String> ingredients) {
        this.mealName = mealName;
        this.numberOfIngredients = numberOfIngredients;
        this.listOfIngredients = ingredients;
    }

    public String getMealName() {
        return mealName;
    }

    public int getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public List<String> getListOfIngredients() {
        return listOfIngredients;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("mealName", mealName);
        json.put("numberOfIngredients", numberOfIngredients);
        json.put("listOfIngredients", listOfIngredients);

        return json;
    }
}
