package ui;

import model.GroceryList;
import model.Meal;
import model.MealPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

// Meal prep application
public class MyMealPrepApp {
    private MealPlan mealPlan;
    private GroceryList groceryList;
    private Scanner input;
    private int numberOfMeals;
    private String command;

    // EFFECTS: runs the meal prep application
    public MyMealPrepApp() {
        runMealPrep();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runMealPrep() {
        System.out.println("Please enter the number of meals you would like to do the grocery run for: ");
        input = new Scanner(System.in);
        numberOfMeals = input.nextInt();
        mealPlan = new MealPlan(numberOfMeals);
        groceryList = new GroceryList();

        enterYourMeals(numberOfMeals);

        displayMealsAndGroceryList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    void displayMealsAndGroceryList() {
        System.out.println("Would you like to see the list of the meals you entered?");
        command = input.next();
        if (command.equals("yes")) {
            for (int i = 0; i < mealPlan.printMealPlan().size(); i++) {
                System.out.println(mealPlan.printMealPlan().get(i));
            }
        }

        System.out.println("Here is your grocery shopping list: ");

        for (int i = 0; i < groceryList.printGroceryList().size(); i++) {
            System.out.println(groceryList.printGroceryList().get(i));
        }
        System.out.println("Would you like to delete some of the groceries from your list? (yes/no)");

        checkIfCorrection();
    }


    // MODIFIES: this
    // EFFECTS: processes user input if they want to make
    //          additional changes to grocery list
    public void checkIfCorrection() {
        String command;
        command = input.next();

        if (command.equals("no")) {
            exit(0);
        }
        while (command.equals("yes")) {
            System.out.println("Please write down the name of the grocery that you would like to delete of your"
                    + "groceries list");
            command = input.next();
            groceryList.removeGrocery(command);
            System.out.println("Would you like to delete something else from your groceries list? (yes/no)");
            command = input.next();
        }
        System.out.println("Here is your updated grocery shopping list: ");
        for (int i = 0; i < groceryList.printGroceryList().size(); i++) {
            System.out.print(groceryList.printGroceryList().get(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input of meals and their specifics and adds them to meal plan
    //          as well as adds groceries to the list
    public void enterYourMeals(int numberOfMeals) {
        String mealName;
        int numberOfIngredients;

        for (int i = 0; i < numberOfMeals; i++) {
            List<String> listOfIngredients = new ArrayList<String>();
            Meal newMeal;

            System.out.println("Please enter the name of the meal: ");
            mealName = input.next();
            System.out.println("Please enter the number of the ingredients the meal is made of: ");
            numberOfIngredients = input.nextInt();
            System.out.println("Please enter the list of the ingredients of your meal: ");
            for (int j = 0; j < numberOfIngredients; j++) {
                listOfIngredients.add(input.next());
            }

            newMeal = new Meal(mealName, numberOfIngredients, listOfIngredients);
            mealPlan.addMeal(newMeal);
            groceryList.addGrocery(newMeal.getNumberOfIngredients(), newMeal.getListOfIngredients());
        }
    }
}
