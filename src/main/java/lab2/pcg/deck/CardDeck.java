package lab2.pcg.deck;

import lab2.pcg.deck.card.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CardDeck {
	
	public static final Comparator<? extends Card> DEFAULT_SORT = (Comparator<Card>) (c1, c2) -> {
		List<String> order = Arrays.asList("PokemonCard", "TrainerCard", "EnergyCard");
		return Integer.compare(order.indexOf(c1.getClass().getName()), order.indexOf(c2.getClass().getName()));
	};
	
	public static final Predicate<? extends Card> DEFAULT_FILTER = card -> true;
	
	
	
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
	
	public int findCard(Card card) {
		return cards.indexOf(card);
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public <T extends Card> List<T> filterCards(Class<T> cardType, Predicate<T> filter, Comparator<T> sorter) {
		return cards.stream()
				.filter(cardType::isInstance)
				.map(cardType::cast)
				.filter(filter)
				.sorted((Comparator<T>) DEFAULT_SORT)
				.sorted(sorter)
				.collect(Collectors.toList());
	}
}
