package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.Expansion;


public class PokemonCard extends Card {
	
	public int hp;
	public int stage = 0;
	public String evolvesFrom = "";
	
	public PokemonCard(String name, String description, int cardNumber, Expansion expansionSymbol, int hp, int stage, String evolvesFrom) {
		super(name, description, cardNumber, expansionSymbol);
		this.hp = hp;
		this.stage = stage;
		this.evolvesFrom = evolvesFrom;
	}
	
}
