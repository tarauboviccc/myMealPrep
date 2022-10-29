package model;

import java.util.*;

// Represents a meal plan containing list of the meal user puts in
public class MealPlan {
    private List<Meal> mealList;                    // list of Meal

    /*
     * EFFECTS: mealList is created and set to empty list;
     */
    public MealPlan() {
        this.mealList = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: meal is added to the mealList
     */
    public void addMeal(Meal meal) {
        mealList.add(meal);
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
}
