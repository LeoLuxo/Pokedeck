package lab2.pcg.ui;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.card.Card;
import lab2.pcg.deck.card.EnergyCard;
import lab2.pcg.deck.card.PokemonCard;
import lab2.pcg.deck.card.TrainerCard;

import java.awt.*;


public class CardCreator {
	
	private static final int creatorRow = 2;
	private static final int creatorCol = 43;
	
	private static final int cardRow = 2;
	private static final int cardCol = 3;
	
	public static Card designNewCard() {
		drawTitle();
		Display.printSimpleString("What type of card do you want to create?", creatorRow+2, creatorCol, Color.WHITE, Color.BLACK, true);
		int result = Util.requestMultiChoiceInput(new String[] {"Pokémon Card", "Trainer Card", "Energy Card"}, creatorRow+4, creatorCol, true);
		
		Card card;

		if (result == 0) {
			card = new PokemonCard();
		} else if (result == 1) {
			card = new TrainerCard();
		} else {
			card = new EnergyCard();
		}
		
		boolean saveConfirmed = updateCard(card);
		return saveConfirmed ? card : null;
	}
	
	// card is modfied in place
	public static boolean updateCard(Card card) {
		int selection = 0;
		
		while (true) {
			drawTitle();
			card.displayCard(cardRow, cardCol, true, selection, true);
			drawSaveCancel(selection - card.getNumberOfFields());
			
			while (true) {
				int input = Util.requestChoiceBase();
				if (input == 0) {
					if (selection == card.getNumberOfFields()) {
						return true;
					} else if (selection == card.getNumberOfFields() + 1) {
						return false;
					} else {
						Display.setColor(Color.WHITE, Color.BLACK);
						card.fillField(selection, creatorRow + 2, creatorCol);
						break;
					}
				} else {
					selection = Math.max(Math.min(selection + input, card.getNumberOfFields()+1), 0);
				}
				
				card.displayCard(cardRow, cardCol, false, selection, true);
				drawSaveCancel(selection - card.getNumberOfFields());
			}
		}
	}
	
	private static void drawTitle() {
		Display.eraseInDisplayFull();
		Display.setUnderlineOn();
		Display.printSimpleString("Card Creator", creatorRow, creatorCol, new Color(0, 128, 255), Color.BLACK, false);
		Display.setUnderlineOff();
	}
	
	private static void drawSaveCancel(int selection) {
		if (selection == 0)
			Display.printSimpleString(">SAVE<", cardRow+26, cardCol+40, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR.darker(), false);
		else
			Display.printSimpleString(" SAVE ", cardRow+26, cardCol+40, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR, false);
		
		if (selection == 1)
			Display.printSimpleString(">CANCEL<", cardRow+26, cardCol+50, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR.darker(), false);
		else
			Display.printSimpleString(" CANCEL ", cardRow+26, cardCol+50, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR, false);
	}
	
}
