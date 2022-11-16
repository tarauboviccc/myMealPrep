package ui;

import model.GroceryList;
import model.Meal;
import model.MealPlan;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/mp.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MealPlan mp;
    private Meal newMeal;
    private GroceryList groceryList;
    private int repeat;
    private Scanner input;

    private JLabel jlabel = new JLabel();
    private Font font1;
    private Font font2;
    private JPanel jpanel = new JPanel();
    private JTextArea textArea = new JTextArea();
    private ImageIcon image;
    private JTextArea mealPlanDisplay = new JTextArea();


    // EFFECTS: action listener for adding a meal
    private ActionListener addListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            enterYourMeal();
            mealPlanDisplay.setFont(font2);
            mealPlanDisplay.setBackground(Color.GRAY);
            mealPlanDisplay.setForeground(Color.WHITE);
            mealPlanDisplay.setText(mealPlanPrintout());
        }
    };

    // EFFECTS: action listener for saving a meal
    private ActionListener saveListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveMealPlan();
        }
    };

    // EFFECTS: action listener for loading a meal plan
    private ActionListener loadListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadMealPlan();
            //mealPlanDisplay.setText(mealPlanPrintout());
        }
    };

    // EFFECTS: action listener for printing out a meal plan
    private ActionListener printListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mealPlanDisplay.setText(mealPlanPrintout());
        }
    };

    // EFFECTS: action listener for quitting the application
    private ActionListener quitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Thank you for using MyMealPrep!");
        }
    };

    // EFFECTS: constructs mealPlan and runs app
    public GUI() throws InterruptedException {
        super("Meal Plan");
        input = new Scanner(System.in);
        mp = new MealPlan("MealPlan");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMealPrepApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runMealPrepApp() throws
            InterruptedException {
        repeat = 1;
        Thread.sleep(1000);

        menu();
        pack();
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: menu of the user's possible choices:
    //          - adding a meal
    //          - loading a meal plan
    public void menu() {
        System.out.println("Welcome to MyMealPrep app!");
        System.out.println("Select one of the menu options to continue: ");
        setupGui();
    }

    private String mealPlanPrintout() {
        String tempPrint = "";
        int i = 0;

        for (Meal m : mp.getMeals()) {
            i++;
            tempPrint += ("Meal" + " [" + i + "]: " + m.getMealName()) + "\n";
        }
        return tempPrint;
    }

    // MODIFIES: this
    // EFFECTS: setup for GUI
    public void setupGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(800, 400));

        jpanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));

        addImage();
        add(jpanel);
        add(jlabel);
        addMealButton();
        saveMealPlanButton();
        loadMealPlanButton();
        printMealPlanButton();
        quitButton();
        setupMealPlanDisplay();

        pack();
        setVisible(true);

    }

    // EFFECTS: sets up area where meal plan is being displayed
    private void setupMealPlanDisplay() {
        this.add(mealPlanDisplay);
        this.setLocation(600, 100);
    }

    // MODIFIES: gui
    // EFFECTS: prints out meal plan
    public void printMealPlanButton() {
        JButton buttonPrint = new JButton("Print meal plan");
        buttonPrint.addActionListener(printListener);
        jpanel.add(buttonPrint);
    }

    // MODIFIES: gui
    // EFFECTS: adds button for quitting the app
    public void quitButton() {
        JButton buttonQuit = new JButton("Quit");
        buttonQuit.addActionListener(quitListener);
        jpanel.add(buttonQuit);
    }

    // MODIFIES: gui
    // EFFECTS: adds button for loading saved meal plan
    public void loadMealPlanButton() {
        JButton buttonLoadMealPlan = new JButton("Load saved meal plan");
        buttonLoadMealPlan.addActionListener(loadListener);
        jpanel.add(buttonLoadMealPlan);
    }

    // MODIFIES: gui
    // EFFECTS: adds button for saving a meal plan
    public void saveMealPlanButton() {
        JButton buttonSaveMealPlan = new JButton("Save your meal plan");
        buttonSaveMealPlan.addActionListener(saveListener);
        jpanel.add(buttonSaveMealPlan);
    }

    // MODIFIES: giu
    // EFFECTS: adds button for adding new meal
    public void addMealButton() {
        JButton buttonAddMeal = new JButton("Add a new meal");
        buttonAddMeal.addActionListener(addListener);
        jpanel.add(buttonAddMeal);
    }

    // MODIFIES: gui
    // EFFECTS: adds an image to the panel
    public void addImage() {
        try {
            image = new ImageIcon("mealPlanWelcome.jpeg");
            font1 = new Font("Century Gothic", Font.BOLD, 17);
            font2 = new Font("Century Gothic", Font.BOLD, 12);
            jlabel.setFont(font1);
            jlabel.setText("Welcome to your MyMealPrep!");
            jlabel.setIcon(image);
            jlabel.setVerticalTextPosition(JLabel.TOP);
            jlabel.setHorizontalTextPosition(JLabel.CENTER);
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input of new meal and its ingredients
    //          adds new meal to the mealPlan
    //          adds ingredients to the grocery list
    private void enterYourMeal() {
        String mealName;
        int numberOfIngredients;
        List<String> listOfIngredients = new ArrayList<String>();
        Meal newMeal;

        //System.out.println("Enter the name of the meal [1 string]: ");
        mealName = JOptionPane.showInputDialog("Enter the name of the meal: ");
        //System.out.println("Enter the number of the ingredients you need to prepare the meal: ");
        numberOfIngredients = Integer.parseInt(JOptionPane.showInputDialog("Enter the number "
                + "of ingredients needed for preparation: "));
        //System.out.println("Please enter the list of the ingredients of your meal: ");

        for (int j = 0; j < numberOfIngredients; j++) {
            String item;
            item = JOptionPane.showInputDialog("Enter ingredient no." + j + ":");
            listOfIngredients.add(item);
        }

        newMeal = new Meal(mealName, numberOfIngredients, listOfIngredients);
        mp.addMeal(newMeal);

        JOptionPane.showMessageDialog(null,
                "Meal added successfully!",
                "successful", JOptionPane.INFORMATION_MESSAGE);

        //return (newMeal);
    }

    // EFFECTS: save the MealPlan to file
    private void saveMealPlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(mp);
            jsonWriter.close();
            System.out.println("Saved your meal plan to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads meal plan from file
    private void loadMealPlan() {
        try {
            mp = jsonReader.read();
            System.out.println("Loaded meal plan from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
