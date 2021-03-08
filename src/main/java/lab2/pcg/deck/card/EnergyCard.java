package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.EnergyType;


public class EnergyCard extends Card {
	
	public EnergyType type;
	
	public EnergyCard(String name, String description, EnergyType type) {
		super(name, description);
		this.type = type;
	}
	
}
