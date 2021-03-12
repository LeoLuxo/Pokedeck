package lab2.pcg.ui;


import lab2.pcg.Display;
import lab2.pcg.Pokedeck;
import lab2.pcg.PokedeckManager;
import lab2.pcg.Util;
import lab2.pcg.deck.CardDeck;

import java.awt.*;


public class MainMenu {
	
	private static CardDeck loadedDeck;
	
	private static final int menuRow = 3;
	private static final int menuCol = 3;
	
	public static void askDeck() {
		while (true) {
			switchMenu();
			// Ask user for name
			Display.printSimpleString("Welcome trainer! What deck do you wish to load?", menuRow, menuCol);
			String name = Util.requestString(1, 16, menuRow+2, menuCol).strip().replaceAll("\\W+", "_");
			
			// Call deckExists method from PokedeckManager class to see if it exists.
			// It doesn't exit- call method to ask to create one
			if (!PokedeckManager.deckExists(name)) {
				boolean newDeckAnswer = askNewDeck(name);
				if (!newDeckAnswer) {
					continue;
				}
			}
			
			loadedDeck = PokedeckManager.loadDeck(name);
			
			mainMenuSelector();
		}
	}
	
	public static boolean askNewDeck(String name) {
		switchMenu();
		Display.printSimpleString("Deck doesn't exist. Would you like to create a new deck \"" + name + "\"?", menuRow, menuCol);
		int answer = Util.requestMultiChoiceInput(new String[]{"YES", "NO"}, menuRow+2, menuCol);
		
		if (answer == 0) {
			PokedeckManager.createDeck(name);
			return true;
		} else {
			return false;
		}
	}

	public static void mainMenuSelector() {
		while (true) {
			switchMenu();
			Display.printSimpleString("Currently loaded deck: " + loadedDeck.name, menuRow, menuCol);
			Display.printSimpleString("What would you like to do?", menuRow+1, menuCol);
			
			int result = Util.requestMultiChoiceInput(new String[]{"Add a card", "View/Search collection", "Switch deck"}, menuRow+3, menuCol);
			
			if (result == 0) {
				CardCreator.designCard();
			} else if (result == 1) {
			
			} else {
				return;
			}
		}
	}
	
	
	
	private static void switchMenu() {
		Display.cursorPosition(menuRow, menuCol);
		Display.setColor(Color.WHITE, Color.BLACK);
		Display.eraseInDisplayFromCursor();
	}
	

}
