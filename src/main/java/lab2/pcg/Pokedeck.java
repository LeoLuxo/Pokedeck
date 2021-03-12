package lab2.pcg;

import lab2.pcg.ui.MainMenu;


public class Pokedeck {
  
  public static void main(String[] args) {
    
    try {
      
      Display.initDisplay();

      MainMenu.askDeck();

    } finally {
      Display.exitDisplay();
    }
    
  }
}
