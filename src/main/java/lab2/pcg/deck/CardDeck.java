package lab2.pcg.deck;

import lab2.pcg.deck.card.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CardDeck {
	public transient String name;
	
	// TODO: FIX MAJOR SAVING BUG, when saving to json after loading, will remove all subclass attributes
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
	
	public List<Card> filterCards(Predicate<Card> filter, Comparator<Card> sorter) {
		return cards.stream()
				.filter(filter)
				.sorted(sorter)
				.collect(Collectors.toList());
	}
	
	// Trying to merge the methods for the three card types using reflection and Class<? extends Card> just for the sake
	// of avoiding code repetition would have been horribly redudant and made these methods wayyy more complicated.
	public List<Card> filterPokemonCards(Predicate<PokemonCard> filter, Comparator<PokemonCard> sorter) {
		return cards.stream()
				.filter(card -> card instanceof PokemonCard)
				.map(card -> (PokemonCard) card)
				.filter(filter)
				.sorted(sorter)
				.collect(Collectors.toList());
	}
	
	public List<Card> filterTrainerCards(Predicate<TrainerCard> filter, Comparator<TrainerCard> sorter) {
		return cards.stream()
				.filter(card -> card instanceof TrainerCard)
				.map(card -> (TrainerCard) card)
				.filter(filter)
				.sorted(sorter)
				.collect(Collectors.toList());
	}
	
	public List<Card> filterEnergyCards(Predicate<EnergyCard> filter, Comparator<EnergyCard> sorter) {
		return cards.stream()
				.filter(card -> card instanceof EnergyCard)
				.map(card -> (EnergyCard) card)
				.filter(filter)
				.sorted(sorter)
				.collect(Collectors.toList());
	}
}
