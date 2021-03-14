package lab2.pcg.deck.enums;

import java.util.Arrays;


public enum TrainerType {

	ITEM("Item"),
	STADIUM("Stadium"),
	POKEMON_TOOL("Pokémon Tool"),
	SUPPORTER("Supporter"),
	TECHNICAL_MACHINE("Technical Machine"),
	ACE_SPEC("Ace Spec"),
	ROCKET_SECRET_MACHINE("Rocket's Secret Machine"),
	GOLDENROD_GAME_CORNER("Goldenrod Game Corner");

	public final String displayName;

	TrainerType(String displayName) {
		this.displayName = displayName;
	}
}
