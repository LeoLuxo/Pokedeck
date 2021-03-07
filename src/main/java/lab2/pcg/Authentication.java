package lab2.pcg;
import java.util.Scanner;
import lab2.pcg.PokedeckManager;


public static class Authentication  {

  public static void askName(String[]args) {
    Scanner sanner = new Scanner(System.in);
    // Ask user for name
    System.out.println("What's your name?");
    String name = scanner.nextLine();
    // Call deckExists method from PokedeckManager class to see if it exists.
    deckExists(name);
    //It doesn't exit- call method to ask to create one
    if deckEsists = false {
      askNewDeck();
    }
    else {
    //go to main menu
    }
   }

  public static void askNewDeck() {
    System.out.println("Would you like to create a new Deck?y/n");
    String answer = scanner.nextLine();
    if answer =="y"{
      //call Create new deck method
    }
    else {
      askName();
    }
  }


}