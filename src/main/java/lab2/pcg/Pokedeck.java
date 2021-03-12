package lab2.pcg;

import lab2.pcg.ui.MainMenu;


public class Pokedeck {
  
  public static void main(String[] args) {
    
    try {
      // The program uses ANSI escape codes extensively as well as the "stty" command.
      // It will ONLY work on Linux terminals or WSL, and isn't guaranteed to work with every terminal.
      // If the program happens to hang and doing ^C doesn't work, the only option is to kill
      // the terminal/java via an external program (or in case of WSL on Windows close it entirely)
      
      Display.initDisplay();

      MainMenu.askDeck();

    } finally {
      Display.exitDisplay();
    }
    
  }
}
