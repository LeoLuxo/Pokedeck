package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.Expansion;
import lab2.pcg.deck.enums.TrainerType;


public class TrainerCard extends Card {
	
	public TrainerType type;
	
	public TrainerCard(String name, String description, int cardNumber, Expansion expansionSymbol, TrainerType type) {
		super(name, description, cardNumber, expansionSymbol);
		this.type = type;
	}
	
}
