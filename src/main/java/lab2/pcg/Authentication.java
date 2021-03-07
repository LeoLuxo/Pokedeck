package lab2.pcg;
import java.util.Scanner;
import lab2.pcg.PokedeckManager;


public class Authentication  {

  public static void askName() {
    // Ask user for name
    System.out.println("What's your name?");
    String name = Pokedeck.scanner.nextLine();
    // Call deckExists method from PokedeckManager class to see if it exists.
    //It doesn't exit- call method to ask to create one
    if (PokedeckManager.deckExists(name)) {
      askNewDeck();
    }
    else {
    //go to main menu
    }
   }

  public static void askNewDeck() {
    System.out.println("Would you like to create a new Deck?y/n");
    String answer = Pokedeck.scanner.nextLine();
    if (answer =="y") {
      //call Create new deck method
    }
    else {
      askName();
    }
  }


}
