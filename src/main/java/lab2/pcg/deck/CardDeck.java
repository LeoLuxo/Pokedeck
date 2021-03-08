package lab2.pcg.deck;

import lab2.pcg.deck.card.Card;
import java.util.ArrayList;
import java.util.List;


public class CardDeck {
	public transient String name;
	
	private List<Card> cards = new ArrayList<>();
	
	public CardDeck(String name) {
		this.name = name;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void removeCard(int index) {
		cards.remove(index);
	}
	
	public void updateCard(int index, Card card) {
		cards.remove(index);
		cards.add(index, card);
	}
}
