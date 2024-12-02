import java.util.Scanner;




public class Main {




    // Arrays of predefined chatbot responses for misunderstandings, greetings, and goodbyes.
    private static final String[] misunderstandings = {
        "I'm sorry, I didn't catch that.",
        "Could you say that again?",
        "I'm not sure what you mean.",
        "Can you try asking in a different way?",
        "Hmm, that's beyond my expertise."
    };
    private static final String[] greetings = {
        "Hello! Welcome to Chipotle! How can I help you today?",
        "Hi there, welcome in! What would you like to order?",
        "Hey there! Ready to place your order?"
    };
    private static final String[] goodbye = {
        "Thank you for coming! Have a great day!",
        "Thanks for stopping by! Hope to see you again soon!",
        "Thanks for choosing Chipotle! Have an awesome day!",
        "Have a great one, and enjoy your meal!",
        "Take care! Hope to see you again soon!",
        "Thanks a lot! Enjoy your food, and we will see you next time!"
    };




    // Scanner for user input and variables to track the total price and order summary.
    private static final Scanner scanner = new Scanner(System.in);
    private static double totalPrice = 0.0; // Tracks the total price of the order.
    private static String orderSummary = ""; // Stores a summary of all selected items.




    // Array of menu items with name, price, and category (e.g., Dish, Protein, etc.).
    private static final MenuItem[] menuItems = {
        new MenuItem("Taco", 7.99, "Dish"),
        new MenuItem("Burrito", 8.69, "Dish"),
        new MenuItem("Burrito Bowl", 9.49, "Dish"),
        new MenuItem("Smoked Brisket", 4.00, "Protein"),
        new MenuItem("Chicken", 0.00, "Protein"),
        new MenuItem("Steak", 2.00, "Protein"),
        new MenuItem("Beef Barbacoa", 2.00, "Protein"),
        new MenuItem("Carnitas", 0.00, "Protein"),
        new MenuItem("Sofritas", 0.00, "Protein"),
        new MenuItem("White Rice", 0.00, "Carbs"),
        new MenuItem("Brown Rice", 0.00, "Carbs"),
        new MenuItem("Black Beans", 0.00, "Beans"),
        new MenuItem("Pinto Beans", 0.00, "Beans"),
        new MenuItem("Guacamole", 2.95, "Topping"),
        new MenuItem("Mild Salsa", 0.00, "Topping"),
        new MenuItem("Medium Salsa", 0.00, "Topping"),
        new MenuItem("Hot Salsa", 0.00, "Topping"),
        new MenuItem("Corn", 0.00, "Topping"),
        new MenuItem("Sour Cream", 0.00, "Topping"),
        new MenuItem("Cheese", 0.00, "Topping"),
        new MenuItem("Lettuce", 0.00, "Topping"),
        new MenuItem("Queso", 2.10, "Topping"),
        new MenuItem("Chips", 2.50, "Side"),
        new MenuItem("Fountain Drink", 2.15, "Drink")
    };




    public static void main(String[] args) {
        // Display a random greeting message to the user.
        System.out.println(getRandomResponse(greetings));
       
        // Infinite loop to keep the chatbot running until the user chooses to exit.
        while (true) {
            System.out.println("We have Burritos, Tacos, and Burrito Bowls. Which would you prefer?");
            String mainDish = scanner.nextLine().toLowerCase();




            // Check if the user wants to exit.
            if (mainDish.contains("exit")) {
                System.out.println("Thank you for visiting Chipotle Chatbot. Have a great day!");
                break;
            }




            // Process the main dish choice or handle misunderstandings.
            if (!processMainDish(mainDish)) {
                System.out.println(getRandomResponse(misunderstandings));
            } else {
                // Ask the user if they want another dish or to check out.
                System.out.println("Would you like another dish or do you want to check out?");
                String choice = scanner.nextLine().toLowerCase();
                if (choice.contains("check out")) {
                    checkout(); // Proceed to checkout if the user is done ordering.
                    break;
                }
            }
        }
        // Display a random goodbye message.
        System.out.println(getRandomResponse(goodbye));
    }




    // Processes the user's choice of main dish and adds it to the order.
    private static boolean processMainDish(String mainDish) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getType().equals("Dish") && mainDish.contains(menuItem.getName().toLowerCase())) {
                orderSummary += menuItem.getName() + ", "; // Add the dish to the order summary.
                totalPrice += menuItem.getPrice(); // Add the dish price to the total.
                processDishOptions(menuItem.getName()); // Ask for additional options like rice, protein, etc.
                return true;
            }
        }
        return false; // Return false if the main dish wasn't understood.
    }




    // Asks the user for additional options like rice, protein, beans, and toppings.
    private static void processDishOptions(String mainDish) {
        System.out.println("Would you like white rice, brown rice, or no rice?");
        String rice = scanner.nextLine().toLowerCase();
        addItemsToOrder(rice);




        System.out.println("What protein would you like? Options: Smoked Brisket, Chicken, Steak, Beef Barbacoa, Carnitas, Sofritas, or none?");
        String protein = scanner.nextLine().toLowerCase();
        addItemsToOrder(protein);




        System.out.println("Would you like black beans, pinto beans, or no beans?");
        String beans = scanner.nextLine().toLowerCase();
        addItemsToOrder(beans);




        System.out.println("Do you want Guacamole, Mild salsa, Medium salsa, Hot salsa, Corn, Sour Cream, Fajitas, Cheese, Lettuce, or Queso?");
        String toppings = scanner.nextLine().toLowerCase();
        addItemsToOrder(toppings);
    }




    // Adds selected items (rice, protein, beans, or toppings) to the order and updates the total price.
    private static void addItemsToOrder(String input) {
        for (MenuItem menuItem : menuItems) {
            if (input.contains(menuItem.getName().toLowerCase())) {
                orderSummary += menuItem.getName() + ", "; // Add the item to the order summary.
                totalPrice += menuItem.getPrice(); // Add the item's price to the total.
            }
        }
    }




    // Displays the order summary and calculates the final total at checkout.
    private static void checkout() {
        System.out.println("Here’s your order:");
        System.out.println(orderSummary); // Display all selected items.
        System.out.println("Your total is $" + totalPrice + ". Chips or a drink for $2 each?");
        String extra = scanner.nextLine().toLowerCase();




        // Add chips or a drink if selected.
        if (extra.contains("chips")) {
            orderSummary += "Chips, ";
            totalPrice += 2.50;
        }
        if (extra.contains("drink")) {
            orderSummary += "Fountain Drink, ";
            totalPrice += 2.00;
        }




        // Display the final order and total price.
        System.out.println("Final Order:");
        System.out.println(orderSummary);
        System.out.println("Your final total is $" + totalPrice + ". Thanks for choosing Chipotle!");
    }




    // Returns a random response from the given array.
    private static String getRandomResponse(String[] responses) {
        return responses[(int) (Math.random() * responses.length)];
    }
}
















