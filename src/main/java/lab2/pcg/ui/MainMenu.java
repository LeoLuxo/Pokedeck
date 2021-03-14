package lab2.pcg.ui;


import lab2.pcg.Display;
import lab2.pcg.PokedeckManager;
import lab2.pcg.Util;
import lab2.pcg.deck.CardDeck;
import lab2.pcg.deck.card.Card;

import java.awt.*;


public class MainMenu {
	
	private static final int logoRow = 10;
	private static final int logoCol = 10;
	private static final int menuRow = 17;
	private static final int menuCol = 10;
	
	
	
	private CardDeck deck;
	
	
	
	public MainMenu() {}
	
	
	
	public void askDeck() {
		while (true) {
			drawMenu();
			// Ask user for name
			Display.printSimpleString("Welcome trainer! What deck do you wish to load?", menuRow, menuCol);
			String name = Util.requestString(1, 16, menuRow+2, menuCol, true).strip().replaceAll("\\W+", "_");
			
			// Call deckExists method from PokedeckManager class to see if it exists.
			// It doesn't exit- call method to ask to create one
			if (!PokedeckManager.deckExists(name)) {
				boolean newDeckAnswer = askNewDeck(name);
				if (!newDeckAnswer) {
					continue;
				}
			}
			
			deck = PokedeckManager.loadDeck(name);
			
			mainMenuSelector();
		}
	}
	
	public boolean askNewDeck(String name) {
		drawMenu();
		Display.printSimpleString("Deck doesn't exist. Would you like to create a new deck \"" + name + "\"?", menuRow, menuCol);
		int answer = Util.requestMultiChoiceInput(new String[]{"YES", "NO"}, menuRow+2, menuCol, true);
		
		if (answer == 0) {
			PokedeckManager.createDeck(name);
			return true;
		} else {
			return false;
		}
	}

	public void mainMenuSelector() {
		while (true) {
			drawMenu();
			Display.printSimpleString("Currently loaded deck: " + deck.name, menuRow, menuCol);
			Display.printSimpleString("What would you like to do?", menuRow+1, menuCol);
			
			int result = Util.requestMultiChoiceInput(new String[]{"View/Search collection", "Add a card", "Switch deck"}, menuRow+3, menuCol, true);
			
			if (result == 0) {
				searchMenu();
			} else if (result == 1) {
				addCardMenu();
			} else {
				return;
			}
		}
	}
	
	public void addCardMenu() {
		Card card = CardCreator.designNewCard();
		if (card != null) {
			deck.addCard(card);
			PokedeckManager.saveDeck(deck);
		}
	}
	
	public void searchMenu() {
		Search search = new Search(deck);
		search.mainSearchMenu();
	}
	
	
	
	private static void drawMenu() {
		Display.resetStyle();
		Display.eraseInDisplayFull();
		Display.cursorPosition(logoRow, logoCol);
		Display.printColorDesign(Util.readDesignString("logo"), new Color(0, 100, 255), Color.BLACK, new Color(255, 150, 0), logoCol);
		
		Display.setColor(Color.WHITE, Color.BLACK);
		Display.printSimpleString("by Léo G and Liz S", logoRow+3, logoCol);
		
		Display.cursorPosition(menuRow, menuCol);
	}
	

}
