package model;

import java.util.*;

// Represents a meal plan containing list of the meal user puts in, and the number of meals user puts in
public class MealPlan {
    private List<Meal> mealList;                    // list of meal
    private int numberOfMeals;                      // number of meals

    /*
     * REQUIRES: numberOfMeals has a non-zero length
     * EFFECTS: mealList is created and set to empty list;
     *          number of meals is set to numberOfMeals
     */
    public MealPlan(int numberOfMeals) {
        this.mealList = new ArrayList<>();
        this.numberOfMeals = numberOfMeals;
    }

    /*
     * MODIFIES: this
     * EFFECTS: meal is added to the mealList
     */
    public void addMeal(Meal meal) {
        this.mealList.add(meal);
    }

    /*
     * EFFECTS: returns a list of organized strings of the meals
     */
    public List<String> printMealPlan() {
        List<String> tempPrint = new ArrayList<>();

        for (int i = 0; i < getNumberOfMeals(); i++) {
            tempPrint.add("Meal" + " [" + i + "]: " + getMeals().get(i).getMealName());
        }
        return tempPrint;
    }

    public List<Meal> getMeals() {
        return this.mealList;
    }

    public int getNumberOfMeals() {
        return this.numberOfMeals;
    }
}
