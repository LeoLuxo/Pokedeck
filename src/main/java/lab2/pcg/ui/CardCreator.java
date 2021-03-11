package lab2.pcg.ui;

import lab2.pcg.Display;
import lab2.pcg.Pokedeck;
import lab2.pcg.PokedeckManager;
import lab2.pcg.Util;
import lab2.pcg.deck.card.Card;
import lab2.pcg.deck.card.EnergyCard;
import lab2.pcg.deck.card.PokemonCard;
import lab2.pcg.deck.card.TrainerCard;

import java.awt.*;


public class CardCreator {
	
	
	private static final Color MAIN_COLOR = new Color(198, 195, 193);
	private static final Color SECONDARY_COLOR = new Color(135, 135, 135);
	
	
	
	public static void designCard() {
		System.out.println("What card do you want to create?");
		System.out.println("1) Pokémon Card\n2) Trainer Card\n3) Energy Card");
		
		int result = Util.requestRangedInteger(1, 3);
		
		Card card;
		
		if (result == 1) {
			card = new PokemonCard();
			designBaseCard(card);
			designPokemonCard(card);
		} else if (result == 2) {
			card = new TrainerCard();
			designBaseCard(card);
			designTrainerCard(card);
		} else {
			card = new EnergyCard();
			designBaseCard(card);
			designEnergyCard(card);
		}
		
		displayCard(card, 2, 4);
		
		Pokedeck.loadedDeck.addCard(card);
		PokedeckManager.saveDeck(Pokedeck.loadedDeck);
	}
	
	private static void designBaseCard(Card baseCard) {
		System.out.print("NAME: ");
		baseCard.name = Util.requestAnyString();
		System.out.print("DESCRIPTION: ");
		baseCard.description = Util.requestAnyString();
		System.out.print("CARDNUMBER: ");
		baseCard.cardNumber = Util.requestRangedInteger(0, Integer.MAX_VALUE);
		System.out.print("EXPANSION: ");
		baseCard.expansionSymbol = Util.requestExpansion();
	}
	
	private static void designPokemonCard(Card baseCard) {
		PokemonCard card = (PokemonCard) baseCard;
		
		System.out.print("HP: ");
		card.hp = Util.requestRangedInteger(0, Integer.MAX_VALUE);
		System.out.print("STAGE: ");
		card.stage = Util.requestRangedInteger(0, 2);
		if (card.stage != 0) {
			System.out.print("EVOLVES FROM: ");
			card.evolvesFrom = Util.requestAnyString();
		}
		System.out.print("TYPE: ");
		card.type = Util.requestEnergyType();
	}
	
	private static void designTrainerCard(Card baseCard) {
		TrainerCard card = (TrainerCard) baseCard;
		
		System.out.print("TRAINER CARD TYPE: ");
		card.type = Util.requestTrainerType();
	}
	
	private static void designEnergyCard(Card baseCard) {
		EnergyCard card = (EnergyCard) baseCard;
		
		System.out.print("ENERGY TYPE: ");
		card.type = Util.requestEnergyType();
	}
	
	public static void displayCard(Card card, int row, int col) {
		Display.eraseInDisplayFull();
		Display.cursorPosition(row, 1);
		
		// TODO: Change
		if (card instanceof PokemonCard) {
			displayPokemonCard((PokemonCard) card, row, col, true);
		} else if (card instanceof TrainerCard) {
			displayTrainerCard((TrainerCard) card, row, col, true);
		} else if (card instanceof EnergyCard) {
			displayEnergyCard((EnergyCard) card, row, col, true);
		}
		
//		while (true);
//		if (Pokedeck.scanner.hasNext())
//			Pokedeck.scanner.nextLine();
	}
	
	private static void displayPokemonCard(PokemonCard card, int row, int col, boolean fullDraw) {
		if (fullDraw)
			Display.printColorDesign(Util.readDesignString("pokemon_card"), MAIN_COLOR, SECONDARY_COLOR, card.type.color, col);
		
		Display.printRightAlignedString(card.type.displayName, row, col+36, card.type.color, Color.BLACK);
		Display.printLeftAlignedString(card.name, row+1, col+1, Color.BLACK, card.type.color);
		Display.printLeftAlignedString("Stage " + card.stage + (card.evolvesFrom.length() == 0 ? "" : ": " + card.evolvesFrom), row+2, col+2, Color.BLACK, MAIN_COLOR);
		Display.printRightAlignedString("HP " + card.hp, row+1, col+36, Color.BLACK, card.type.color);
		Display.printWrappedString(card.description, row+13, col+2, Color.BLACK, card.type.color, 12, 34);
		Display.printRightAlignedString(card.cardNumber + "/00 " + card.expansionSymbol, row+25, col+36, Color.BLACK, MAIN_COLOR);
	}
	
	private static void displayTrainerCard(TrainerCard card, int row, int col, boolean fullDraw) {
		if (fullDraw)
			Display.printColorDesign(Util.readDesignString("trainer_card"), MAIN_COLOR, SECONDARY_COLOR, Color.WHITE, col);
		
		Display.printRightAlignedString(card.type.displayName, row+1, col+35, Color.BLACK, MAIN_COLOR);
		Display.printRightAlignedString(card.name, row+2, col+35, Color.BLACK, MAIN_COLOR);
		Display.printWrappedString(card.description, row+13, col+2, Color.BLACK, MAIN_COLOR, 12, 34);
		Display.printRightAlignedString(card.cardNumber + "/00 " + card.expansionSymbol, row+25, col+36, Color.BLACK, SECONDARY_COLOR);
	}
	
	private static void displayEnergyCard(EnergyCard card, int row, int col, boolean fullDraw) {
		if (fullDraw)
			Display.printColorDesign(Util.readDesignString("energy_card"), MAIN_COLOR, SECONDARY_COLOR, card.type.color, col);
		
		Display.printRightAlignedString(card.type.displayName, row, col+36, card.type.color, Color.BLACK);
		Display.printLeftAlignedString(card.name, row+19, col+2, Color.BLACK, MAIN_COLOR);
		Display.printWrappedString(card.description, row+20, col+2, Color.BLACK, MAIN_COLOR, 12, 34);
		Display.printRightAlignedString(card.cardNumber + "/00 " + card.expansionSymbol, row+25, col+36, Color.BLACK, MAIN_COLOR);
	}
	
}
