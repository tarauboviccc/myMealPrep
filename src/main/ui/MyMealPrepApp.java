package ui;

import model.GroceryList;
import model.Meal;
import model.MealPlan;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Meal prep application
public class MyMealPrepApp {
    private static final String JSON_STORE = "./data/grocerylist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MealPlan mp;
    private GroceryList groceryList;
    private Scanner input;
    private String command;
    private Meal newMeal;

    // EFFECTS: runs the meal prep application
    public MyMealPrepApp() {
        runMealPrep();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runMealPrep() {
        boolean keepGoing = true;
        String command = null;

        System.out.println("Welcome to MyMealPrepApp!");
        input = new Scanner(System.in);
        mp = new MealPlan();
        groceryList = new GroceryList();

        while (keepGoing) {

            displayMenu();
            command = input.next();

            if (command.equals("8")) {
                keepGoing = false;
            } else {
                doCommand(command);
            }
        }
    }

    // EFFECTS: displays a menu of options user can pick
    private void displayMenu() {
        System.out.println("\nSelect one of the options from the menu:");
        System.out.println("\t1 -> enter a meal, number of its ingredients and list of ingredients needed for its "
                + "preparation");
        System.out.println("\t2 -> display a list of meals entered");
        System.out.println("\t3 -> display a grocery list");
        System.out.println("\t4 -> delete an item from grocery list");
        System.out.println("\t5 -> add an item to the grocery list");
        System.out.println("\t6 -> save my grocery list to a file");
        System.out.println("\t7 -> load a grocery list from a file");
        System.out.println("\t8 -> quit the application");
    }

    // MODIFIES: this
    // EFFECTS: processes user's input
    private void doCommand(String command) {
        if (command.equals("1")) {
            newMeal = enterYourMeal();
            mp.addMeal(newMeal);
            groceryList.addGrocery(newMeal.getNumberOfIngredients(), newMeal.getListOfIngredients());
        } else if (command.equals("2")) {
            displayMeals();
        } else if (command.equals("3")) {
            displayGroceryList();
        } else if (command.equals("4")) {
            deleteItemFromGroceryList();
        } else if (command.equals("5")) {
            addItemToGroceryList();
        } else if (command.equals("6")) {
            saveGroceryList();
        } else if (command.equals("7")) {
            loadGroceryList();
        }
    }

    // EFFECTS: displays the list of mealPlan meal names (list of String)
    private void displayMeals() {
        System.out.println("The list of the meals you entered: ");
        for (int i = 0; i < mp.printMealPlan().size(); i++) {
            System.out.println(mp.printMealPlan().get(i));
        }
    }

    // EFFECTS: displays grocery list from the input entered
    private void displayGroceryList() {
        System.out.println("Here is your grocery shopping list: ");
        for (int i = 0; i < groceryList.printGroceryList().size(); i++) {
            System.out.println(groceryList.printGroceryList().get(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes an item (String) from the grocery list
    private void deleteItemFromGroceryList() {
        System.out.println("Write down the name of the item you would like to delete:");
        command = input.next();
        groceryList.removeGrocery(command);
    }

    // MODIFIES: this
    // EFFECTS: adds an item (String) to the grocery list
    private void addItemToGroceryList() {
        System.out.println("Write down the name of the item you would like to add:");
        command = input.next();
        groceryList.addGrocery(command);
    }

    // MODIFIES: this
    // EFFECTS: processes user input of new meal and its ingredients
    //          adds new meal to the mealPlan
    //          adds ingredients to the grocery list
    private Meal enterYourMeal() {
        String mealName;
        int numberOfIngredients;
        List<String> listOfIngredients = new ArrayList<String>();
        Meal newMeal;

        System.out.println("Enter the name of the meal [1 string]: ");
        mealName = input.next();
        System.out.println("Enter the number of the ingredients you need to prepare the meal: ");
        numberOfIngredients = input.nextInt();
        System.out.println("Please enter the list of the ingredients of your meal: ");
        for (int j = 0; j < numberOfIngredients; j++) {
            listOfIngredients.add(input.next());
        }

        return (new Meal(mealName, numberOfIngredients, listOfIngredients));
    }

    // citations: used an example given in the WorkRoomApp for the SaveWorkRoom function
    // EFFECTS: save the GroceryList to file
    private void saveGroceryList() {
        try {
            jsonWriter.open();
            jsonWriter.write(groceryList);
            jsonWriter.close();
            System.out.println("Saved your grocery list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //citations: used an example given in the WorkRoomApp for the loadWorkRoom function
    // MODIFIES: this
    // EFFECTS: loads grocery list from file
    private void loadGroceryList() {
        try {
            groceryList = jsonReader.read();
            System.out.println("Loaded grocery list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
