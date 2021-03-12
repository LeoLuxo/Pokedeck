package lab2.pcg.ui;


import lab2.pcg.Display;
import lab2.pcg.PokedeckManager;
import lab2.pcg.Util;
import lab2.pcg.deck.CardDeck;

import java.awt.*;


public class Authentication  {
	
	public static CardDeck askDeck() {
		while (true) {
			// Ask user for name
			Display.printSimpleString("Welcome trainer! What deck do you wish to load?", 4, 2, Color.WHITE, Color.BLACK, true);
			String name = Util.requestString(1, 16, 5, 2).strip().replaceAll("\\W+", "_");
			
			// Call deckExists method from PokedeckManager class to see if it exists.
			// It doesn't exit- call method to ask to create one
			if (!PokedeckManager.deckExists(name)) {
				boolean newDeckAnswer = askNewDeck(name);
				if (!newDeckAnswer) {
					continue;
				}
			}
			
			return PokedeckManager.loadDeck(name);
		}
	}
	
	public static boolean askNewDeck(String name) {
		Display.printSimpleString("Deck doesn't exist. Would you like to create a new deck \"" + name + "\"?", 4, 2, Color.WHITE, Color.BLACK, true);
		int answer = Util.requestMultiChoiceInput(new String[]{"YES", "NO"}, 5, 2);
		
		if (answer == 0) {
			PokedeckManager.createDeck(name);
			return true;
		} else {
			return false;
		}
	}
	
	
}
