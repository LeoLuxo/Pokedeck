package lab2.pcg;

import lab2.pcg.deck.CardDeck;

import java.util.Scanner;


public class Pokedeck {
  
  public static Scanner scanner;
  public static CardDeck loadedDeck;
  
  public static void main(String[] args) {
    
    scanner = new Scanner(System.in);
    loadedDeck = Authentication.askDeck();
    
  }
}
