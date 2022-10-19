package model;

import java.util.*;

// Represents a grocery list containing number of groceries and list of those groceries
// the list of the groceries is formed from adding each of meal's list of ingredients
public class GroceryList {
    private int numberOfGroceries;                       //number of groceries
    private List<String> groceriesList;                  // list of groceries

    /*
     * EFFECTS: groceriesList is created and set to empty list;
     *          number of groceries is set to 0
     */
    public GroceryList() {
        groceriesList = new ArrayList<>();
        numberOfGroceries = 0;
    }

    /*
     * REQUIRES: grocery is a non-zero String
     * MODIFIES: this
     * EFFECTS: grocery in the grocery list that is equal to
     *          the user's String input is removed from the groceries list
     *          number of groceries also decreased for 1 because one grocery is removed
     */
    public void removeGrocery(String grocery) {
        groceriesList.remove(grocery);
        numberOfGroceries--;
    }

    /*
     * REQUIRES: numberOfGroceries is not 0
     *           groceries is a non-empty list
     * MODIFIES: this
     * EFFECTS: adds list of meal's ingredients to the groceriesList
     *          increases numberOfGroceries by the amount of numberOfIngredients
     */
    public void addGrocery(int numberOfIngredients, List<String> listOfIngredients) {
        this.numberOfGroceries += numberOfIngredients;
        for (int i = 0; i < numberOfIngredients; i++) {
            groceriesList.add(listOfIngredients.get(i));
        }
    }

    /*
     * EFFECTS: returns a list of organized strings of the groceries
     */
    public List<String> printGroceryList() {
        List<String> tempPrint = new ArrayList<>();

        for (int i = 0; i < getNumberOfGroceries(); i++) {
            tempPrint.add("Groceries" + " [" + i + "]: " + getGroceries().get(i));
        }

        return tempPrint;
    }

    public int getNumberOfGroceries() {
        return numberOfGroceries;
    }

    public List<String> getGroceries() {
        return groceriesList;
    }
}
