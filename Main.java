import java.util.Scanner;

public class Main {

    // when user doesnt understand
    private static final String[] misunderstandings = {
        "I'm sorry, I didn't catch that.",
        "Could you say that again?",
        "I'm not sure what you mean.",
        "Can you try asking in a different way?",
        "Hmm, that's beyond my expertise."
    };

    // greetings when the bot starts talking
    private static final String[] greetings = {
        "Hello! Welcome to Chipotle! How can I help you today?",
        "Hi there, welcome in! What would you like to order?",
        "Hey there! Ready to place your order?"
    };

    // these are the goodbyes when the user ends the convo
    private static final String[] goodbye = {
        "Thank you for coming! Have a great day!",
        "Thanks for stopping by! Hope to see you again soon!",
        "Thanks for choosing Chipotle! Have an awesome day!",
        "Have a great one, and enjoy your meal!",
        "Take care! Hope to see you again soon!",
        "Thanks a lot! Enjoy your food, and we will see you next time!"
    };

    // scanner to get input from user
    private static final Scanner scanner = new Scanner(System.in);
    // total cost of the meal
    private static double totalPrice = 0.0;
    // keeps track of all the stuff they ordered
    private static String orderSummary = "";

    // the menu items (everything they can choose)
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
        // say hi and start the convo
        System.out.println(getRandomResponse(greetings));
        
        // loop to keep talking unless they say "exit"
        while (true) {
            // color coded menu 
            String tacos = getColoredText("Tacos", "\u001B[31m"); // Red
            String burritos = getColoredText("Burritos", "\u001B[34m"); // Blue
            String burritoBowls = getColoredText("Burrito Bowls", "\u001B[32m"); // Green

            System.out.println("We have " + burritos + ", " + tacos + ", and " + burritoBowls + ". What would you want to have?");
            String mainDish = scanner.nextLine().toLowerCase();

            // exit keyword check
            if (mainDish.contains("exit")) {
                System.out.println("Thank you for visiting Chipotle Chatbot. Have a great day!");
                break;
            }

            if (!isValidDish(mainDish)) {//checks if the dish exists
                System.out.println(getRandomResponse(misunderstandings));
            } else {
                System.out.println("You picked " + capitalizeFirstLetter(mainDish) + ". Let's customize it!");
                processMainDish(mainDish);
                System.out.println("Wanna get another dish or check out?");
                String choice = scanner.nextLine().toLowerCase();//makes a new scanner to see if they said they want a new dish

                // exit check again
                if (choice.contains("exit")) {
                    System.out.println("Thank you for visiting Chipotle Chatbot. Have a great day!");
                    break;
                }

                if (choice.contains("check out")) {
                    checkout();
                    break;
                }
                if (choice.contains("checkout")) {
                    checkout();
                    break;
                }
            }
        }
        // say bye when done
        System.out.println(getRandomResponse(goodbye));
    }

    private static boolean isValidDish(String mainDish) {
        String[] validDishes = {"taco", "burrito", "burrito bowl"};
        //checks witha for loop that the dish the user inputed exists
        for (int i = 0; i < validDishes.length; i++) {
            if (mainDish.contains(validDishes[i])) {
                return true;
            }
        }
        return false;
    }

    private static void processMainDish(String mainDish) {
        // loop through menu and check if dish is in the input
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getType().equals("Dish") && mainDish.contains(menuItem.getName().toLowerCase())) {
                orderSummary += menuItem.getName() + ", ";
                totalPrice += menuItem.getPrice();
                processDishOptions(menuItem.getName());
                return;
            }
        }
    }

    private static void processDishOptions(String mainDish) {
        // pick which rice they want or none
        while (true) {
            System.out.println("Want white rice, brown rice, or no rice?");
            String rice = scanner.nextLine().toLowerCase();

            // exit check
            if (rice.contains("exit")) {
                System.out.println("Thank you for visiting Chipotle Chatbot. Have a great day!");
                System.exit(0);//terminates the JVM so entire code stops, not just loop like break does
            }

            if (addItemsToOrder(rice) || rice.equals("no") || rice.equals("none") || rice.equals("no rice")) {
                if (rice.equals("no") || rice.equals("none")||rice.equals("no rice")) {
                    orderSummary += "No Rice, ";
                }
                break;
            }
            System.out.println(getRandomResponse(misunderstandings));
        }

        // pick what protein or none
        while (true) {
            System.out.println("What protein? Options: Smoked Brisket, Chicken, Steak, Beef Barbacoa, Carnitas, Sofritas, or none?");
            String protein = scanner.nextLine().toLowerCase();

            // exit check
            if (protein.contains("exit")) {
                System.out.println("Thank you for visiting Chipotle Chatbot. Have a great day!");
                System.exit(0);//terminates the JVM so entire code stops, not just loop like break does
            }

            if (addItemsToOrder(protein) || protein.equals("no") || protein.equals("none")||protein.equals("no protein")||protein.equals("no meat")) {
                if (protein.equals("no") || protein.equals("none")||protein.equals("no protein")||protein.equals("no meat")) {
                    orderSummary += "No Protein, ";
                }
                break;
            }
            System.out.println(getRandomResponse(misunderstandings));
        }

        // pick which beans or none
        while (true) {
            System.out.println("Want black beans, pinto beans, or no beans?");
            String beans = scanner.nextLine().toLowerCase();

            // exit check
            if (beans.contains("exit")) {
                System.out.println("Thank you for visiting Chipotle Chatbot. Have a great day!");
                System.exit(0);//terminates the JVM so entire code stops, not just loop like break does
            }

            if (addItemsToOrder(beans) || beans.equals("no") || beans.equals("none")||beans.equals("no beans")) {
                if (beans.equals("no") || beans.equals("none")||beans.equals("no beans")) {
                    orderSummary += "No Beans, ";
                }
                break;
            }
            System.out.println(getRandomResponse(misunderstandings));
        }

        // pick which toppings or none
        while (true) {
            System.out.println("Want Guacamole, Mild salsa, Medium salsa, Hot salsa, Corn, Sour Cream, Fajitas, Cheese, Lettuce, Queso, or None?");
            String toppings = scanner.nextLine().toLowerCase();

            // exit check
            if (toppings.contains("exit")) {
                System.out.println("Thank you for visiting Chipotle Chatbot. Have a great day!");
                System.exit(0);//terminates the JVM so entire code stops, not just loop like break does
            }

            if (addItemsToOrder(toppings) || toppings.equals("no") || toppings.equals("none")) {
                if (toppings.equals("no") || toppings.equals("none")) {
                    orderSummary += "No Toppings, ";
                }
                break;
            }
            System.out.println(getRandomResponse(misunderstandings));
        }
    }

    
    
    private static boolean addItemsToOrder(String input) {
        boolean validInput = false;//need this bc otherwise it says the || opperator doesnt work
        for (MenuItem menuItem : menuItems) {// for loop that goes thru each thing in the menu
            if (input.contains(menuItem.getName().toLowerCase())) {// if the item they said is in the menu
                orderSummary += menuItem.getName() + ", ";// add to the order summary
                totalPrice += menuItem.getPrice();// also add the price to the total price
                validInput = true;//
            }
        }
        return validInput;
    }
    



    private static void checkout() {
        System.out.println("Here’s your order:");
        System.out.println(orderSummary);
        System.out.println("Your total is $" + totalPrice + ". Chips for $2.50 or a drink for $2.00");
        String extra = scanner.nextLine().toLowerCase();
        if (extra.contains("chips")) {
            orderSummary += "Chips, ";
            totalPrice += 2.50;
        }
        if (extra.contains("drink")) {
            orderSummary += "Fountain Drink, ";
            totalPrice += 2.00;
        }
        System.out.println("Final Order:");
        System.out.println(orderSummary);
        System.out.println("Your final total is $" + totalPrice + ". Thanks for choosing Chipotle!");
    }

    private static String getRandomResponse(String[] responses) {
        return responses[(int) (Math.random() * responses.length)];
    }

    private static String getColoredText(String text, String colorCode) {
        return colorCode + text + "\u001B[0m";
    }

    private static String capitalizeFirstLetter(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
