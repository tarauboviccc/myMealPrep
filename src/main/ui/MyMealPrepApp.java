package ui;

import model.GroceryList;
import model.Meal;
import model.MealPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

import static java.lang.System.exit;

public class MyMealPrepApp {
    private MealPlan mealPlan;
    private GroceryList groceryList;
    private Scanner input;
    private int numberOfMeals;
    private String command;

    public MyMealPrepApp() {
        runMealPrep();
    }


    public void runMealPrep() {
        System.out.println("Please enter the number of meals you would like to do the grocery run for: ");
        input = new Scanner(System.in);
        numberOfMeals = input.nextInt();
        mealPlan = new MealPlan(numberOfMeals);
        groceryList = new GroceryList();

        enterYourMeals(numberOfMeals);

        displayMeals();
    }

    void displayMeals() {
        System.out.println("Would you like to see the list of the meals you entered?");
        command = input.next();
        if (command.equals("yes")) {
            mealPlan.printout();
        }

        System.out.println("Here is your grocery shopping lis1t: ");
        groceryList.printout();
        System.out.println("Would you like to delete some of the groceries from your list? (yes/no)");
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
        groceryList.printout();
    }

    public void enterYourMeals(int numberOfMeals) {
        String mealName;
        int numberOfIngredients;
        List<String> listOfIngredients = new ArrayList<String>();
        Meal newMeal;

        for (int i = 0; i < numberOfMeals; i++) {
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
