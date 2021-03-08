package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.EnergyType;
import lab2.pcg.deck.enums.Expansion;


public class EnergyCard extends Card {
	
	public EnergyType type;
	
	public EnergyCard(String name, String description, int cardNumber, Expansion expansionSymbol, EnergyType type) {
		super(name, description, cardNumber, expansionSymbol);
		this.type = type;
	}
	
}
