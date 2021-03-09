package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.EnergyType;
import lab2.pcg.deck.enums.Expansion;


public class PokemonCard extends Card {
	
	public int hp = 0;
	public int stage = 0;
	public String evolvesFrom = "";
	public EnergyType type = EnergyType.NONE;
	
//	public PokemonCard(String name, String description, int cardNumber, Expansion expansionSymbol, int hp, int stage, String evolvesFrom, EnergyType type) {
//		super(name, description, cardNumber, expansionSymbol);
//		this.hp = hp;
//		this.stage = stage;
//		this.evolvesFrom = evolvesFrom;
//		this.type = type;
//	}

}
