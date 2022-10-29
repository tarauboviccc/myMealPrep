package persistence;

import model.Meal;
import model.MealPlan;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.*;

import org.json.*;

//CITATIONS: used example given in JsonSerializationDemo
// Represents a reader that reads MealPlan from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads MealPlan from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MealPlan read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMealPlan(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses MealPlan from JSON object and returns it
    private MealPlan parseMealPlan(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        MealPlan mp = new MealPlan();
        addMeals(mp, jsonObject);
        return mp;
    }

    // MODIFIES: mp
    // EFFECTS: parses meals from JSON object and adds them to MealPlan
    private void addMeals(MealPlan mp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(mp, nextMeal);
        }
    }

    // MODIFIES: mp
    // EFFECTS: parses meal from JSON object and adds it to MealPlan
    private void addMeal(MealPlan mp, JSONObject jsonObject) {
        int j = 0;

        String mealName = jsonObject.getString("mealName");
        int numberOfIngredients = jsonObject.getInt("numberOfIngredients");
        JSONArray loi = jsonObject.getJSONArray("listOfIngredients");
        List<String> listOfIngredients = new ArrayList<String>();
        for (Object i : loi) {
            j++;
            listOfIngredients.set(j, loi.get(j).toString());
        }
        Meal meal = new Meal(mealName, numberOfIngredients, listOfIngredients);
        mp.addMeal(meal);
    }
}
