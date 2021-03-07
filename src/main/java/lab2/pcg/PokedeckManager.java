package lab2.pcg;

import lab2.pcg.deck.CardDeck;


public class PokedeckManager {
	
	private static final String savePath = "pokedecks/";
	
	public static boolean deckExists(String name) {
		return false;
	}
	
	public static CardDeck loadDeck(String name) {
		return new CardDeck();
	}
	
	public static void saveDeck(CardDeck deck) {
		
	}
	
	private static String sanitizeInput(String input) {
		return input.toLowerCase().strip();
	}
	
}
