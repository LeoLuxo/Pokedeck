package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.TrainerType;


public class TrainerCard extends Card {
	
	public TrainerType type;
	
	public TrainerCard(String name, String description, TrainerType type) {
		super(name, description);
		this.type = type;
	}
	
}
