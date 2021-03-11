package lab2.pcg;

import lab2.pcg.deck.CardDeck;
import lab2.pcg.ui.Authentication;
import lab2.pcg.ui.MainMenu;

import java.util.Scanner;


public class Pokedeck {
  
  public static CardDeck loadedDeck;
  
  public static void main(String[] args) {
    
    try {
      
      Display.initDisplay();

      loadedDeck = Authentication.askDeck();
      MainMenu.mainMenuSelector();

    } finally {
      Display.exitDisplay();
    }
    
  }
}
