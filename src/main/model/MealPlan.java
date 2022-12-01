package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a meal plan containing list of the meal user puts in
public class MealPlan implements Writable {
    private List<Meal> mealList;                    // list of Meal
    private String name;

    /*
     * EFFECTS: mealList is created and set to empty list;
     */
    public MealPlan(String name) {
        this.mealList = new ArrayList<>();
        this.name = name;
    }

    /*
     * MODIFIES: this
     * EFFECTS: meal is added to the mealList
     */
    public void addMeal(Meal meal) {
        mealList.add(meal);
        EventLog.getInstance().logEvent(new Event("Meal added to the meal plan."));
    }

    /*
      MODIFIES: this
      EFFECTS: meal is removed from the mealList
     */
    public void removeMeal(Meal meal) {
        mealList.remove(meal);
        EventLog.getInstance().logEvent(new Event("Meal removed from the meal plan."));
    }


    /*
     * EFFECTS: returns a list of organized strings of the meals
     */
    public List<String> printMealPlan() {
        List<String> tempPrint = new ArrayList<>();

        for (int i = 0; i < mealList.size(); i++) {
            tempPrint.add("Meal" + " [" + i + "]: " + mealList.get(i).getMealName());//getMeals().get(i).getMealName());
        }
        return tempPrint;
    }

    public List<Meal> getMeals() {
        return this.mealList;
    }

    public int getNumberOfMeals() {
        return this.mealList.size();
    }

    public String getName() {
        return this.name;
    }

    // CITATIONS: used example given in JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("meals", mealsToJson());
        return json;
    }

    // EFFECTS: returns things in this MealPlan as a JSON array
    private JSONArray mealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal t : mealList) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: prints the events from the event log
    public void logPrinter(EventLog eventLog) {
        for (Event e : eventLog) {
            System.out.println(e.getDescription());
        }
    }
}
