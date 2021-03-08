package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.Expansion;


public class PokemonCard extends Card {
	
	public int hp;
	public int stage;
	public String evolvesFrom;
	public int cardNumber;
	public Expansion expansionSymbol;
	
	public PokemonCard(String name, String description, int hp, int stage, String evolvesFrom, int cardNumber, Expansion expansionSymbol) {
		super(name, description);
		this.hp = hp;
		this.stage = stage;
		this.evolvesFrom = evolvesFrom;
		this.cardNumber = cardNumber;
		this.expansionSymbol = expansionSymbol;
	}
	
	public PokemonCard(String name, String description, int hp, int stage, String evolvesFrom, int cardNumber) {
		this(name, description, hp, stage, evolvesFrom, cardNumber, Expansion.UNDEFINED);
	}
	
	public PokemonCard(String name, String description, int hp, int stage, String evolvesFrom) {
		this(name, description, hp, stage, evolvesFrom, -1);
	}
	
	public PokemonCard(String name, String description, int hp) {
		this(name, description, hp, 0, "");
	}
	
}
