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
		
		card.displayCard(row, col, true, -1);
		
		Display.resetStyle();
		
		if (!Pokedeck.scanner.hasNext())
			while (true);
//			Pokedeck.scanner.nextLine();
	}
	
}
