package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.Expansion;


public class Card {
	public String name;
	public String description;
	
	public int cardNumber;
	public Expansion expansionSymbol;
	
	protected Card(String name, String description, int cardNumber, Expansion expansionSymbol) {
		this.name = name;
		this.description = description;
		this.cardNumber = cardNumber;
		this.expansionSymbol = expansionSymbol;
	}
}
