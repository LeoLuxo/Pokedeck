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
	
	private static final int textRow = 2;
	private static final int textCol = 2;
	private static final int gridRow = 8;
	private static final int gridCol = 2;
	
	public static int globalSelection = 0;
	public static int selectedCardType = 0;
	public static int selectedSort = 0;
	
	public static Comparator<? extends Card> currentSort;
	public static Predicate<? extends Card> currentSearch;
	
	
	
	public static void mainSearchMenu(CardDeck deck) {
		currentSearch = card -> true;
		drawMenu(deck);
		
		while (true) {
			int input = Util.requestChoiceBase();
			
			if (input == 0) {
			
			} else {
				globalSelection = Math.max(Math.min(globalSelection + input, 13), 0);
			}
			
			drawButtons();
			drawEntries(deck);
		}
	}
	
	
	
	private static void drawMenu(CardDeck deck) {
		Display.resetStyle();
		Display.eraseInDisplayFull();
		
		Display.setColor(Color.WHITE, Color.BLACK);
		Display.printSimpleString("Select below which card type should be searched for.", textRow+2, textCol);
		
		Display.cursorPosition(gridRow, gridCol);
		Display.printColorDesign(Util.readDesignString("grid"), Util.QUERY_BG_COLOR, Color.BLACK, Color.BLACK, gridCol);
		
		drawButtons();
		drawEntries(deck);
	}
	
	private static void drawButtons() {
		drawFancyButton("GO BACK TO MAIN MENU", textRow, textCol, 0, false);
		drawFancyButton("ALL", textRow+3, textCol, 1, selectedCardType==0);
		drawFancyButton("POKEMON", textRow+3, textCol+6, 2, selectedCardType==1);
		drawFancyButton("TRAINER", textRow+3, textCol+16, 3, selectedCardType==2);
		drawFancyButton("ENERGY", textRow+3, textCol+26, 4, selectedCardType==3);
		
		if (selectedCardType == 0) {
			drawAnyGrid();
		} else if (selectedCardType == 1) {
			drawPokemonGrid();
		} else if (selectedCardType == 2) {
			drawTrainerGrid();
		} else if (selectedCardType == 3) {
			drawEnergyGrid();
		}
	}
	
	private static void drawEntries(CardDeck deck) {
		updateFilters();
		
		if (selectedCardType == 0) {
			List<Card> entries = deck.filterCards(Card.class, (Predicate<Card>) currentSearch, (Comparator<Card>) currentSort);
			drawAnyEntries(entries);
		} else if (selectedCardType == 1) {
			List<PokemonCard> entries = deck.filterCards(PokemonCard.class, (Predicate<PokemonCard>) currentSearch, (Comparator<PokemonCard>) currentSort);
			drawPokemonEntries(entries);
		} else if (selectedCardType == 2) {
			List<TrainerCard> entries = deck.filterCards(TrainerCard.class, (Predicate<TrainerCard>) currentSearch, (Comparator<TrainerCard>) currentSort);
			drawTrainerEntries(entries);
		} else if (selectedCardType == 3) {
			List<EnergyCard> entries = deck.filterCards(EnergyCard.class, (Predicate<EnergyCard>) currentSearch, (Comparator<EnergyCard>) currentSort);
			drawEnergyEntries(entries);
		}
	}
	
	private static void drawFancyButton(String text, int row, int col, int id, boolean selected) {
		if (id == globalSelection)
			text = ">"+text+"<";
		else
			text = " "+text+" ";
		
		drawButton(text, row, col, id, selected);
	}
	
	private static void drawButton(String text, int row, int col, int id, boolean selected) {
		if (selected) {
			if (id == globalSelection)
				Display.printSimpleString(text, row, col, Util.QUERY_FG_COLOR, BUTTON_SELECTED_COLOR.darker(), false);
			else
				Display.printSimpleString(text, row, col, Util.QUERY_FG_COLOR, BUTTON_SELECTED_COLOR, false);
		} else {
			if (id == globalSelection)
				Display.printSimpleString(text, row, col, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR.darker(), false);
			else
				Display.printSimpleString(text, row, col, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR, false);
		}
	}
	
	private static void drawSortButton(int row, int col, int id, int selectedStatus) {
		String c = selectedStatus==0 ? "-" : (selectedStatus==1 ? "▼" : "▲");
		
		if (selectedStatus > 0) {
			if (id == globalSelection)
				Display.printSimpleString(c, row, col, Util.QUERY_FG_COLOR, BUTTON_SELECTED_COLOR.darker(), false);
			else
				Display.printSimpleString(c, row, col, Util.QUERY_FG_COLOR, BUTTON_SELECTED_COLOR, false);
		} else {
			if (id == globalSelection)
				Display.printSimpleString(c, row, col, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR.darker(), false);
			else
				Display.printSimpleString(c, row, col, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR, false);
		}
	}
	
	
	
	private static void drawAnyGrid() {
		drawSortButton(gridRow+1, gridCol+1, 5, 0);
		drawSortButton(gridRow+1, gridCol+3, 6, 0);
		drawButton("NAME", gridRow+1, gridCol+5, 7, false);
		drawSortButton(gridRow+1, gridCol+29, 8, 0);
		drawButton("DESCRIPTION", gridRow+1, gridCol+31, 9, false);
		drawSortButton(gridRow+1, gridCol+87, 10, 0);
		drawButton("NUM", gridRow+1, gridCol+89, 11, false);
		drawSortButton(gridRow+1, gridCol+93, 12, 0);
		drawButton("EXP", gridRow+1, gridCol+95, 13, false);
	}
	
	private static void drawPokemonGrid() {
	
	}
	
	private static void drawTrainerGrid() {
	
	}
	
	private static void drawEnergyGrid() {
	
	}
	
	
	
	private static void drawAnyEntries(List<Card> entries) {
		for (int i = 0; i < Math.min(16, entries.size()); i++) {
			Card card = entries.get(i);
			drawButton(card.getClass().getName().substring(0, 1), gridRow+3+i, gridCol+1, 14, false);
			drawButton(card.name.substring(0, Math.min(card.name.length(), 25)), gridRow+3+i, gridCol+3, 14, false);
			drawButton(card.description.substring(0, Math.min(card.description.length(), 57)), gridRow+3+i, gridCol+29, 14, false);
			drawButton(String.valueOf(card.cardNumber), gridRow+3+i, gridCol+87, 14, false);
			drawButton(card.expansionSymbol.substring(0, Math.min(card.expansionSymbol.length(), 5)), gridRow+3+i, gridCol+93, 14, false);
		}
	}
	
	private static void drawPokemonEntries(List<PokemonCard> entries) {
	
	}
	
	private static void drawTrainerEntries(List<TrainerCard> entries) {
	
	}
	
	private static void drawEnergyEntries(List<EnergyCard> entries) {
	
	}
	
	
	
	private static void updateFilters() {
		switch (Math.abs(selectedSort)) {
			default:
			case 1:
				currentSort = Comparator.comparing(c -> c.getClass().getName());
				break;
			case 2:
			
		}
		
		if (selectedSort < 0)
	}
	
}
