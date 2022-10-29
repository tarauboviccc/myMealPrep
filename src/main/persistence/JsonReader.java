package persistence;

import model.GroceryList;
import model.MealPlan;
import model.Meal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// CITATIONS: used the example of JsonReader given for the JsonSerializationDemo
// Represents a reader that reads grocery list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads grocery list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GroceryList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGroceryList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses grocery list from JSON object and returns it
    private GroceryList parseGroceryList(JSONObject jsonObject) {
        String groceryList = jsonObject.getString("grocery list");
        GroceryList gl = new GroceryList();
        addGroceryList(gl, jsonObject);
        return gl;
    }

    // MODIFIES: gl
    // EFFECTS: parses groceries from JSON object and adds them to grocery list
    private void addGroceryList(GroceryList gl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("groceries");
        for (Object json : jsonArray) {
            String nextGrocery = (String) json;
            addGroceryItem(gl, nextGrocery);
        }
    }

    // MODIFIES: gl
    // EFFECTS: parses grocery item from JSON object and adds it to grocery list
    private void addGroceryItem(GroceryList gl, String jsonObject) {
        String groceryName = jsonObject;
        gl.addGrocery(groceryName);
    }
}
