package lab2.pcg.ui;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.CardDeck;
import lab2.pcg.deck.card.Card;
import lab2.pcg.deck.card.EnergyCard;
import lab2.pcg.deck.card.PokemonCard;
import lab2.pcg.deck.card.TrainerCard;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class Search {
	
	public static final Color BUTTON_SELECTED_COLOR = new Color(0, 128, 255);
	private static final int MENU_BUTTONS = 12;
	
	private static final int textRow = 2;
	private static final int textCol = 2;
	private static final int gridRow = 8;
	private static final int gridCol = 2;
	private static final int searchRow = textRow;
	private static final int searchCol = textCol + 65;
	private static final int actionRow = gridRow;
	private static final int actionCol = gridCol + 102;
	private static final int cardViewRow = 2;
	private static final int cardViewCol = 50;
	
	
	
	private final CardDeck deck;
	
	private int globalSelection = 0;
	private int globalSelectionMax = 0;
	private int selectedCardType = 0;
	private int selectedSort = 0;
	
	private String filterText = "";
	
	private Comparator<? extends Card> currentSort = CardDeck.DEFAULT_SORT;
	private Predicate<? extends Card> currentSearch = CardDeck.DEFAULT_FILTER;
	
	private List<? extends Card> entries;
	
	
	
	public Search(CardDeck deck) {
		this.deck = deck;
	}
	
	
	
	public void mainSearchMenu() {
		while (true) {
			drawMenu();
			
			boolean exit = queryInput();
			if (exit) {
				return;
			}
		}
	}
	
	
	
	private void drawMenu() {
		Display.resetStyle();
		Display.eraseInDisplayFull();
		
		Display.setColor(Color.WHITE, Color.BLACK);
		Display.printSimpleString("Select below which card type should be searched for.", textRow+2, textCol);
		
		Display.cursorPosition(gridRow, gridCol);
		Display.printColorDesign(Util.readDesignString("grid"), Util.QUERY_BG_COLOR, Color.BLACK, Color.BLACK, gridCol);
		
		if (!filterText.isBlank()) {
			Display.printSimpleString("Filter:", searchRow+2, searchCol, Color.BLACK, Util.QUERY_BG_COLOR, false);
			Display.printSimpleString(filterText, searchRow+3, searchCol, Color.BLACK, BUTTON_SELECTED_COLOR, false);
		}
		
		drawButtons();
		drawEntries();
	}
	
	private void drawButtons() {
		drawFancyButton("GO BACK TO MAIN MENU", textRow, textCol, 0, false);
		drawFancyButton("ALL", textRow+3, textCol, 1, selectedCardType==0);
		drawFancyButton("POKEMON", textRow+3, textCol+6, 2, selectedCardType==1);
		drawFancyButton("TRAINER", textRow+3, textCol+16, 3, selectedCardType==2);
		drawFancyButton("ENERGY", textRow+3, textCol+26, 4, selectedCardType==3);
		
		drawGridButtons();
	}
	
	private void drawGridButtons() {
		drawSortButton(gridRow+1, gridCol+3, 5, 1);
		drawButton("NAME", gridRow+1, gridCol+5, 6, false);
		drawSortButton(gridRow+1, gridCol+29, 7, 2);
		drawButton("DESCRIPTION", gridRow+1, gridCol+31, 8, false);
		drawSortButton(gridRow+1, gridCol+87, 9, 3);
		drawButton("NUM", gridRow+1, gridCol+89, 10, false);
		drawSortButton(gridRow+1, gridCol+93, 11, 4);
		drawButton("EXP", gridRow+1, gridCol+95, 12, false);
	}
	
	private void drawEntries() {
		updateEntries();
		
		for (int i = 0; i < Math.min(16, entries.size()); i++) {
			Card card = entries.get(i);
			Color c = card.getSearchBackground();
			drawButton(Util.delimitString(card.getClass().getSimpleName(), 1), gridRow+3+i, gridCol+1, MENU_BUTTONS+1+i, false, c);
			drawButton(Util.delimitString(card.name, 25), gridRow+3+i, gridCol+3, MENU_BUTTONS+1+i, false, c);
			drawButton(Util.delimitString(card.description.replaceAll("\n+", ";"), 57), gridRow+3+i, gridCol+29, MENU_BUTTONS+1+i, false, c);
			drawButton(Util.delimitString(String.valueOf(card.cardNumber), 5), gridRow+3+i, gridCol+87, MENU_BUTTONS+1+i, false, c);
			drawButton(Util.delimitString(card.expansionSymbol,5), gridRow+3+i, gridCol+93, MENU_BUTTONS+1+i, false, c);
		}
	}
	
	private void updateFilters() {
		switch (Math.abs(selectedSort)) {
			case 1:
				currentSort = Comparator.comparing(c -> c.name.toLowerCase());
				break;
			case 2:
				currentSort = Comparator.comparing(c -> c.description.toLowerCase());
				break;
			case 3:
				currentSort = Comparator.comparing(c -> c.cardNumber);
				break;
			case 4:
				currentSort = Comparator.comparing(c -> c.expansionSymbol.toLowerCase());
				break;
			default:
				currentSort = CardDeck.DEFAULT_SORT;
		}
		
		if (selectedSort < 0) {
			currentSort = currentSort.reversed();
		}
	}
	
	private void updateEntries() {
		updateFilters();
		
		if (selectedCardType == 1) {
			entries = deck.filterCards(PokemonCard.class, (Predicate<PokemonCard>) currentSearch, (Comparator<PokemonCard>) currentSort);
		} else if (selectedCardType == 2) {
			entries = deck.filterCards(TrainerCard.class, (Predicate<TrainerCard>) currentSearch, (Comparator<TrainerCard>) currentSort);
		} else if (selectedCardType == 3) {
			entries = deck.filterCards(EnergyCard.class, (Predicate<EnergyCard>) currentSearch, (Comparator<EnergyCard>) currentSort);
		} else {
			entries = deck.filterCards(Card.class, (Predicate<Card>) currentSearch, (Comparator<Card>) currentSort);
		}
		
		globalSelectionMax = MENU_BUTTONS + Math.min(entries.size(), 16);
	}
	
	
	
	private void drawFancyButton(String text, int row, int col, int id, boolean selected) {
		if (id == globalSelection)
			text = ">"+text+"<";
		else
			text = " "+text+" ";
		
		drawButton(text, row, col, id, selected);
	}
	
	private void drawButton(String text, int row, int col, int id, boolean selected) {
		drawButton(text, row, col, id, selected, Util.QUERY_BG_COLOR);
	}
	
	private void drawButton(String text, int row, int col, int id, boolean selected, Color bgColor) {
		if (selected) {
			if (id == globalSelection)
				Display.printSimpleString(text, row, col, Util.QUERY_FG_COLOR, BUTTON_SELECTED_COLOR.darker(), false);
			else
				Display.printSimpleString(text, row, col, Util.QUERY_FG_COLOR, BUTTON_SELECTED_COLOR, false);
		} else {
			if (id == globalSelection)
				Display.printSimpleString(text, row, col, Util.QUERY_FG_COLOR, bgColor.darker(), false);
			else
				Display.printSimpleString(text, row, col, Util.QUERY_FG_COLOR, bgColor, false);
		}
	}
	
	private void drawSortButton(int row, int col, int id, int selectionID) {
		String c = selectionID!=Math.abs(selectedSort) ? "-" : (selectedSort>0 ? "▼" : "▲");
		
		if (id == globalSelection)
			Display.printSimpleString(c, row, col, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR.darker(), false);
		else
			Display.printSimpleString(c, row, col, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR, false);
	}
	
	
	
	private boolean queryInput() {
		while (true) {
			int input = Util.requestChoiceBase();
			
			if (input == 0) {
				switch (globalSelection) {
					case 0:
						return true;
					
					case 1:
					case 2:
					case 3:
					case 4:
						selectedCardType = globalSelection - 1;
						return false;
					
					case 5:
					case 7:
					case 9:
					case 11:
						querySort();
						return false;
					
					case 6:
						querySearchName();
						return false;
					
					case 8:
						querySearchDescription();
						return false;
					
					case 10:
						querySearchNumber();
						return false;
					
					case 12:
						querySearchExpansion();
						return false;
					
					default:
						queryCardAction();
						return false;
					
				}
			} else {
				globalSelection = Math.max(Math.min(globalSelection + input, globalSelectionMax), 0);
			}
			
			drawButtons();
			drawEntries();
		}
	}
	
	private void querySort() {
		int i = (globalSelection - 1) / 2 - 1;
		if (Math.abs(selectedSort) == i)
			selectedSort = -selectedSort;
		else
			selectedSort = i;
	}
	
	private void querySearchBase() {
	
	}
	
	private void querySearchName() {
		Display.printSimpleString("Search by name:", searchRow, searchCol, Color.WHITE, Color.BLACK, false);
		Display.printSimpleString("(leave empty to clear filter)", searchRow+1, searchCol);
		String query = Util.requestString(0, 30, searchRow+2, searchCol, false);
		if (query.isBlank()) {
			currentSearch = CardDeck.DEFAULT_FILTER;
			filterText = "";
		} else {
			currentSearch = card -> card.name.toLowerCase().contains(query.toLowerCase());
			filterText = "Name contains " + query;
		}
	}
	
	private void querySearchDescription() {
		Display.printSimpleString("Search by description:", searchRow, searchCol, Color.WHITE, Color.BLACK, false);
		Display.printSimpleString("(leave empty to clear filter)", searchRow+1, searchCol);
		String query = Util.requestString(0, 30, searchRow+2, searchCol, false);
		if (query.isBlank()) {
			currentSearch = CardDeck.DEFAULT_FILTER;
			filterText = "";
		} else {
			currentSearch = card -> card.description.toLowerCase().contains(query.toLowerCase());
			filterText = "Description contains " + query;
		}
	}
	
	private void querySearchNumber() {
		Display.printSimpleString("Search by card number:", searchRow, searchCol, Color.WHITE, Color.BLACK, false);
		Display.printSimpleString("(leave empty to clear filter)", searchRow+1, searchCol);
		String query = Util.requestDigitString(0, 3, searchRow+2, searchCol, false);
		if (query.isBlank()) {
			currentSearch = CardDeck.DEFAULT_FILTER;
			filterText = "";
		} else {
			currentSearch = card -> card.cardNumber == Integer.parseInt(query);
			filterText = "Card number matches " + query;
		}
	}
	
	private void querySearchExpansion() {
		Display.printSimpleString("Search by expansion set:", searchRow, searchCol, Color.WHITE, Color.BLACK, false);
		Display.printSimpleString("(leave empty to clear filter)", searchRow+1, searchCol);
		String query = Util.requestString(0, 3, searchRow+2, searchCol, true);
		if (query.isBlank()) {
			currentSearch = CardDeck.DEFAULT_FILTER;
			filterText = "";
		} else {
			currentSearch = card -> card.expansionSymbol.equalsIgnoreCase(query);
			filterText = "Expansion matches " + query;
		}
	}
	
	private void queryCardAction() {
		int action = Util.requestMultiChoiceInput(new String[]{"View card", "Edit card", "Delete card", "CANCEL"}, actionRow, actionCol, true);
		if (action == 3) {
			return;
		}
		
		Card card = entries.get(globalSelection - MENU_BUTTONS - 1);
		int cardIndex = deck.findCard(card);
		if (action == 0) {
			Display.printSimpleString("(Press any key to return)", cardViewRow, cardViewCol, Color.WHITE, Color.BLACK, false);
			card.displayCard(cardViewRow+1, cardViewCol, true, -1);
			Util.requestSingleChar();
		} else if (action == 1) {
			boolean save = CardCreator.updateCard(card);
			if (save) {
				deck.updateCard(cardIndex, card);
			}
		} else if (action == 2) {
			deck.removeCard(cardIndex);
		}
	}
}
