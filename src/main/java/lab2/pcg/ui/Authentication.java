package lab2.pcg.ui;


import lab2.pcg.PokedeckManager;
import lab2.pcg.Util;
import lab2.pcg.deck.CardDeck;


public class Authentication  {
	
	public static CardDeck askDeck() {
		while (true) {
			// Ask user for name
			System.out.println("Welcome trainer! What deck do you wish to load?");
			String name = Util.requestNonEmptyString();
			name = Util.sanitizeDeckNameInput(name);
			
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
		System.out.println("Deck doesn't exist. Would you like to create a new deck \"" + name + "\"? (y/n)");
		boolean answer = Util.requestYesNoBoolean();
		
		if (answer) {
			PokedeckManager.createDeck(name);
			return true;
		} else {
			return false;
		}
	}
	
	
}
