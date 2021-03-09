package lab2.pcg.deck.enums;

public enum EnergyType {
	
	NONE(""),
	COLORLESS("Colorless"),
	GRASS("Grass"),
	FIRE("Fire"),
	WATER("Water"),
	LIGHTNING("Lightning"),
	PSYCHIC("Psychic"),
	FIGHTING("Fighting"),
	DARKNESS("Darkness"),
	METAL("Metal"),
	DRAGON("Dragon"),
	FAIRY("Fairy"),
	ANY("Rainbow"),
	SPECIAL("Other/Special");
	
	public final String displayName;
	
	EnergyType(String displayName) {
		this.displayName = displayName;
	}
	
}
