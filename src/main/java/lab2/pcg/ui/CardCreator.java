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
	
	public static void designCard() {
		Display.eraseInDisplayFull();
		Display.setUnderlineOn();
		Display.printSimpleString("Card Creator", 2, 3, new Color(0, 128, 256), Color.BLACK, true);
		Display.setUnderlineOff();
		Display.printSimpleString("What type of card do you want to create?", 4, 3, Color.WHITE, Color.BLACK, true);
		int result = Util.requestMultiChoiceInput(new String[] {"Pokémon Card", "Trainer Card", "Energy Card"}, 5, 3);
		
		Card card;

		if (result == 1) {
			card = new PokemonCard();
		} else if (result == 2) {
			card = new TrainerCard();
		} else {
			card = new EnergyCard();
		}
		
		card.displayCard(4, 3, true, -1);
		Display.printSimpleString("SAVE", 3, 3, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR, true);
		Display.printSimpleString("CANCEL", 3, 10, false);
		
		while (true);
	}
	
}
