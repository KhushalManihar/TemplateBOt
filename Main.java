import java.util.Scanner;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class Main {
   static String[] goodBye = {"bye"};
   static Pizza pizzaMenu[] = {
       new Pizza("Pepperoni Pizza", 12.99),
       new Pizza("Margherita", 10.99),
       new Pizza("Combo Pizza", 13.99)
   };
   static Sides sidesMenu[] = {
       new Sides("Garlic Bread", 5.99),
       new Sides("Pasta", 7.99),
       new Sides("Chicken Wings", 8.99)
   };
   static Drinks drinksMenu[] = {
       new Drinks("Coca-Cola", 2.99),
       new Drinks("Sprite", 2.99),
       new Drinks("Shirley Temple", 3.99)
   };
   static double totalPrice = 0.0;
   static String order = "";


   // ANSI escape codes for colors
   public static final String RESET = "\033[0m";
   public static final String RED = "\033[31m";
   public static final String GREEN = "\033[32m";
   public static final String YELLOW = "\033[33m";
   public static final String CYAN = "\033[36m";


   public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       boolean pizzaOrder = true;
       timeGreeting(); // Custom greeting based on time
       while (pizzaOrder) {
           String userResp = in.nextLine().toLowerCase();
           if (userResp.equals("bye")) {
               System.out.println(GREEN + "See you next time!" + RESET);
               pizzaOrder = false;
           } else if (userResp.equals("yes") || userResp.contains("order") && !userResp.contains("items")) {
               System.out.println(GREEN + "Great!" + RESET);
               startOrder(in);
           } else if (userResp.equals("no")) {
               System.out.println(CYAN + "That's alright! I'll be here if you need me." + RESET);
           } else if (userResp.contains("menu")) {
               displayMenu();
           } else if (userResp.equals("checkout")) {
               System.out.println(CYAN + "DominoBot: Alright, here is your receipt!" + RESET);
               checkOut();
           } else if (userResp.contains("tell me") && userResp.contains("yourself") || userResp.contains("what can you do")) {
               System.out.println(CYAN + "DominoBot: I'm a chatbot that can help you order pizza, sides, and drinks. I can also display our menu for you. Would you like to order anything? (yes/no)" + RESET);
           } else if (userResp.contains("items")) {
               seeOrder();
           } else if (userResp.matches(".*\\b(hi|hello|hey|greetings|sup|howdy)\\b.*")) {
               System.out.println(CYAN + "DominoBot: Hi there! How can I assist you today? You can ask me to show the menu or start an order!" + RESET);
           } else {
               getRandomResponse();
           }
       }
   }


   public static void displayMenu() {
       System.out.println(YELLOW + "Pizzas: Pepperoni Pizza | Margherita | Combo Pizza" + RESET);
       System.out.println(YELLOW + "Sides: Garlic Bread | Pasta | Chicken Wings" + RESET);
       System.out.println(YELLOW + "Drinks: Coca-Cola | Sprite | Shirley Temple" + RESET);
   }


   public static void getRandomResponse() {
       String randomResponses[] = {
           "I'm sorry, could you rephrase that?",
           "Hmm, I'm not sure what you mean.",
           "Oops, that went over my head! Could you clarify?",
           "Can you say that another way?",
           "I'm just a simple pizza bot. Could you simplify that for me?"
       };
       int response = (int) (Math.random() * randomResponses.length);
       System.out.println(RED + randomResponses[response] + RESET);
   }


 public static void startOrder(Scanner in) {
   boolean isOrdering = true;
   while (isOrdering) {
       displayMenu();
       System.out.println(CYAN + "DominoBot: Select a pizza to order by typing its name." + RESET);
       String pizzaPicked = in.nextLine();
       Pizza choice = findPizza(pizzaPicked);
       if (choice != null) {
           System.out.println(GREEN + "DominoBot: Added " + choice.getName() + " for $" + choice.getPrice() + " to your order." + RESET);
           order += choice.getName() + " | ";
           totalPrice += choice.getPrice();
       } else {
           System.out.println(RED + "DominoBot: I'm sorry, but we could not find that pizza in our menu. Try again?" + RESET);
           continue;
       }


       // Asking the user if they want to order sides or drinks
       System.out.println(CYAN + "DominoBot: Would you like to order any sides or drinks? (yes/no)" + RESET);
       String answer = in.nextLine();
       if (answer.equals("no")) {
           System.out.println(GREEN + "DominoBot: Great! Thanks for completing your order! Type 'checkout' to finish!" + RESET);
           isOrdering = false;
       } else if (answer.equals("yes")) {
           boolean isOrderingSidesOrDrinks = true;
           while (isOrderingSidesOrDrinks) {
               System.out.println(CYAN + "DominoBot: Would you like to order a side or drink? (type 'side' or 'drink')" + RESET);
               String sideOrDrink = in.nextLine().toLowerCase();


               if (sideOrDrink.equals("side")) {
                   displayMenu();  // Display menu again when ordering a side
                   System.out.println(CYAN + "DominoBot: Select a side to order by typing its name." + RESET);
                   String sidePicked = in.nextLine();
                   Sides sideChoice = findSide(sidePicked);
                   if (sideChoice != null) {
                       System.out.println(GREEN + "DominoBot: Added " + sideChoice.getName() + " for $" + sideChoice.getPrice() + " to your order." + RESET);
                       order += sideChoice.getName() + " | ";
                       totalPrice += sideChoice.getPrice();
                   } else {
                       System.out.println(RED + "DominoBot: I'm sorry, but we could not find that side in our menu. Try again?" + RESET);
                       continue;
                   }
               } else if (sideOrDrink.equals("drink")) {
                   displayMenu();  // Display menu again when ordering a drink
                   System.out.println(CYAN + "DominoBot: Select a drink to order by typing its name." + RESET);
                   String drinkPicked = in.nextLine();
                   Drinks drinkChoice = findDrink(drinkPicked);
                   if (drinkChoice != null) {
                       System.out.println(GREEN + "DominoBot: Added " + drinkChoice.getName() + " for $" + drinkChoice.getPrice() + " to your order." + RESET);
                       order += drinkChoice.getName() + " | ";
                       totalPrice += drinkChoice.getPrice();
                   } else {
                       System.out.println(RED + "DominoBot: I'm sorry, but we could not find that drink in our menu. Try again?" + RESET);
                       continue;
                   }
               } else {
                   System.out.println(RED + "DominoBot: Please type 'side' or 'drink' to continue ordering." + RESET);
                   continue;
               }


               // After adding a side or drink, ask if the user wants to order more
               System.out.println(CYAN + "DominoBot: Would you like to order more sides or drinks? (yes/no)" + RESET);
               String moreItems = in.nextLine();
               if (moreItems.equals("no")) {
                   System.out.println(GREEN + "DominoBot: Great! Thanks for completing your order! Type 'checkout' to finish!" + RESET);
                   isOrderingSidesOrDrinks = false;
               }
           }
       }
   }
}


   public static Pizza findPizza(String input) {
       for (Pizza pizza : pizzaMenu) {
           if (pizza.getName().equalsIgnoreCase(input)) {
               return pizza;
           }
       }
       return null;
   }
   public static Drinks findDrink(String input) {
   for (Drinks drink : drinksMenu) {
       if (drink.getName().equalsIgnoreCase(input)) {
           return drink;
       }
   }
   return null;
}


public static Sides findSide(String input) {
   for (Sides side : sidesMenu) {
       if (side.getName().equalsIgnoreCase(input)) {
           return side;
       }
   }
   return null;
}


   public static void checkOut() {
       if (order.isEmpty()) {
           System.out.println(RED + "DominoBot: You have not ordered anything. Type 'order' to begin your order." + RESET);
       } else {
           System.out.println(CYAN + "DominoBot: Your order is: " + order + RESET);
           System.out.println(CYAN + "Your total price is: $" + totalPrice + RESET);
           System.out.println(GREEN + "Type 'bye' to exit the conversation." + RESET);
       }
   }


   public static void seeOrder() {
       if (order.isEmpty()) {
           System.out.println(RED + "DominoBot: You have not ordered anything yet. Type 'order' to begin your order." + RESET);
       } else {
           System.out.println(CYAN + "DominoBot: Your order is: " + order + RESET);
       }
   }


   public static void timeGreeting() {
       ZoneId place = ZoneId.of("America/Los_Angeles");
       ZonedDateTime timeNow = ZonedDateTime.now(place);
       int time = timeNow.getHour();
       if (time < 12) {
           System.out.println(CYAN + "DominoBot: Good morning! I see you're ready for some pizza. Let's get started!" + RESET);
       } else if (time < 18) {
           System.out.println(CYAN + "DominoBot: Good afternoon! Feeling hungry? Let's place your order!" + RESET);
       } else {
           System.out.println(CYAN + "DominoBot: Good evening! Craving a late-night slice? I'm here to help!" + RESET);
       }
   }
}




