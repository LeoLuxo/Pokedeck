package lab2.pcg.deck;

import lab2.pcg.deck.card.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CardDeck {
	
	public static final Comparator<? extends Card> DEFAULT_SORT = Comparator.comparing(c -> c.getClass().getSimpleName());
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
	
	public <T extends Card> List<T> filterCards(Class<T> cardType, Predicate<T> filter, Comparator<T> sorter) {
		return cards.stream()
				.filter(cardType::isInstance)
				.map(cardType::cast)
				.filter(filter)
				.sorted((Comparator<T>) DEFAULT_SORT)
				.sorted(sorter)
				.collect(Collectors.toList());
	}
	
//	// Trying to merge the methods for the three card types using reflection and Class<? extends Card> just for the sake
//	// of avoiding code repetition would have been horribly redudant and made these methods wayyy more complicated.
//	// Yeah actually nvm, look above lol
//	public List<Card> filterPokemonCards(Predicate<PokemonCard> filter, Comparator<PokemonCard> sorter) {
//		return cards.stream()
//				.filter(card -> card instanceof PokemonCard)
//				.map(card -> (PokemonCard) card)
//				.filter(filter)
//				.sorted(Comparator.comparing(c -> c.name))
//				.sorted(sorter)
//				.collect(Collectors.toList());
//	}
//
//	public List<Card> filterTrainerCards(Predicate<TrainerCard> filter, Comparator<TrainerCard> sorter) {
//		return cards.stream()
//				.filter(card -> card instanceof TrainerCard)
//				.map(card -> (TrainerCard) card)
//				.filter(filter)
//				.sorted(Comparator.comparing(c -> c.name))
//				.sorted(sorter)
//				.collect(Collectors.toList());
//	}
//
//	public List<Card> filterEnergyCards(Predicate<EnergyCard> filter, Comparator<EnergyCard> sorter) {
//		return cards.stream()
//				.filter(card -> card instanceof EnergyCard)
//				.map(card -> (EnergyCard) card)
//				.filter(filter)
//				.sorted(Comparator.comparing(c -> c.name))
//				.sorted(sorter)
//				.collect(Collectors.toList());
//	}
}
