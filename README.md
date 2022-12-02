# My MealPrep

## Description 

This application aims to take the user's plan of meals for the certain period of time with each meal's list of 
ingredients needed for its preparation, and creates a shopping list of the that groceries. 

This application is meant to be used by everyone, from students to family households, so it can ease the job of grocery 
shopping as well as reduce not needed purchases, which would also affect food waste reduction.

## User Stories

<ul>
  <li>As a user, I want to be able to add certain number of meals to my meal plan </li>
  <li> As a user, I want to be able to add list of ingredients needed for preparation of each meal </li>
  <li>As a user, I want to be able to view the list of meals I have chosen for the certain period of time </li>
  <li>As a user, I want to be able to view the list of ingredients I need to buy for the grocery shopping </li>
  <li>As a user, I want to be able to delete a grocery item from list of groceries </li>
  <li>As a user, I want to be able to save my meal list to the file </li>
  <li>As a user, I want to be able to be able to load my meal list from file </li>
</ul>

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking button add Meal
- You can generate the second required event related to adding Xs to a Y by clicking button remove meal from meal plan.
- You can locate my visual component by clicking the button print grocery list, or just by adding the meals to meal plan
- You can save the state of my application by clicking button save my meal plan
- You can reload the state of my application by clicking button load my meal plan

# Phase 4, Task 2

When a user is added, an event with description meal added to meal plan is added to the EventLog,
and when a meal is removed an event with description meal is removed from the meal plan is added to the EventLog. 
If no meals are added or removed, then the EventLog is empty.

For example: 
If meal is added and then removed the output is as follows once the application closes:

Meal added to the meal plan
Meal removed from the meal plan

# Phase 4, Task 3

Refactoring I would do to improve the design of my program:

- deleting the MyMealPrepApp class and transfer some additional methods to GUI class, 
since MyMealPrepApp is not being used anymore. 
- merging addListener and addGListener in GUI class. They're adding different types of objects to the list of objects,
but they have the similar behaviour. So, that code repetition can be reduced with a single function describing 
that behaviour.
- merging removeListener and removeMealListener in GUI class. They're removing different types of objects to the list of objects,
  but they have the similar behaviour. So, that code repetition can be reduced with a single function describing
  that behaviour.
- mealPlanDisplay and groceryListDisplay background, foreground, and font setup are implemented in ActionListener's that
don't have purpose of that behavior, so making separate listener / function for that other than having two types of
behavior in one function would improve the overall design. 