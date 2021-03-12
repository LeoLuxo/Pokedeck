package lab2.pcg;

import lab2.pcg.ui.MainMenu;


public class Pokedeck {
  
  public static void main(String[] args) {
    
    try {
      // The program uses ANSI escape codes extensively as well as the "stty" command.
      // It will ONLY work on Linux terminals or WSL, and isn't guaranteed to work with every terminal.
      
      Display.initDisplay();

      MainMenu.askDeck();

    } finally {
      Display.exitDisplay();
    }
    
  }
}
