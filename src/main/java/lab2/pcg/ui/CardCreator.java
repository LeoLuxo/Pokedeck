package lab2.pcg.ui;

import lab2.pcg.Display;
import lab2.pcg.Pokedeck;
import lab2.pcg.PokedeckManager;
import lab2.pcg.Util;
import lab2.pcg.deck.card.Card;
import lab2.pcg.deck.card.EnergyCard;
import lab2.pcg.deck.card.PokemonCard;
import lab2.pcg.deck.card.TrainerCard;
import lab2.pcg.deck.enums.EnergyType;

import java.awt.*;


public class CardCreator {
	
	private static final int creatorRow = 2;
	private static final int creatorCol = 43;
	
	private static final int cardRow = 2;
	private static final int cardCol = 3;
	
	public static void designCard() {
		drawTitle();
		Display.printSimpleString("What type of card do you want to create?", creatorRow+2, creatorCol, Color.WHITE, Color.BLACK, true);
		int result = Util.requestMultiChoiceInput(new String[] {"Pokémon Card", "Trainer Card", "Energy Card"}, creatorRow+4, creatorCol);
		
		Card card;

		if (result == 0) {
			card = new PokemonCard();
		} else if (result == 1) {
			card = new TrainerCard();
		} else {
			card = new EnergyCard();
		}
		
		drawTitle();
		Display.printSimpleString("SAVE", cardRow+26, cardCol+40, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR, false);
		Display.printSimpleString("CANCEL", cardRow+26, cardCol+50);
		card.displayCard(cardRow, cardCol, true, -1);
		
		Util.requestSingleChar();
	}
	
	private static void drawTitle() {
		Display.eraseInDisplayFull();
		Display.setUnderlineOn();
		Display.printSimpleString("Card Creator", creatorRow, creatorCol, new Color(0, 128, 255), Color.BLACK, false);
		Display.setUnderlineOff();
	}
	
}
