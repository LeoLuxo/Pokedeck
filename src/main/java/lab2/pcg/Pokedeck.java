package lab2.pcg;

import lab2.pcg.ui.MainMenu;


public class Pokedeck {

  public static void main(String[] args) {

    try {
      // The program uses ANSI escape codes extensively as well as the "stty" command.
      // It will ONLY work on Linux terminals or WSL, and isn't guaranteed to work with every terminal.
      // If the program happens to hang and doing ^C doesn't work, the only option is to kill
      // the terminal/java via an external program (or in case of WSL on Windows kill the ubuntu process entirely)

      // To anyone reading this (respected teacher or fellow student), the 10-line/statement rule was not respected in many sections of this project.
      // Not as any kind of rebellion, but simply to avoid turning this already over-complicated program into an even worse plate of steaming hot spaghetti
      // and obfuscating the code beyond the point of oblivion. Note that where possible, we indeed tried to decompose, just not necessarily down to 10 lines.
      // We hope this design decision on our part isn't too much of a problem and wish you a pleasant reading.

      // Léo GAMBOA DOS SANTOS
      // Liz SCHREINER

      Display.initDisplay();

      MainMenu menu = new MainMenu();
      menu.askDeck();

    } finally {
      Display.exitDisplay();
    }

  }
}
