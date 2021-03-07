package lab2.pcg;


public class Authentication  {

  public static void askName() {
    // Ask user for name
    System.out.println("Welcome trainer! What deck do you wish to load?");
    String name = Util.requestNonEmptyInput();
    name = Util.sanitizeDeckNameInput(name);
    
    // Call deckExists method from PokedeckManager class to see if it exists.
    //It doesn't exit- call method to ask to create one
    if (!PokedeckManager.deckExists(name)) {
      askNewDeck(name);
    }
    else {
    //go to main menu
    }
   }

  public static void askNewDeck(String name) {
    System.out.println("Deck doesn't exist. Would you like to create a new deck \"" + name + "\"? (y/n)");
    String answer = Util.requestYesNoInput();
    if (answer.equalsIgnoreCase("y")) {
      PokedeckManager.createDeck(name);
    }
    else {
      askName();
    }
  }


}
