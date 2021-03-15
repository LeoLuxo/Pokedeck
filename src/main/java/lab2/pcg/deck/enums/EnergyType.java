package lab2.pcg.deck.enums;

import java.awt.*;


public enum EnergyType {

	COLORLESS("Colorless", Color.decode("#D4DBDF")),
	GRASS("Grass", Color.decode("#299A49")),
	FIRE("Fire", Color.decode("#ED3E30")),
	WATER("Water", Color.decode("#00A3DA")),
	LIGHTNING("Lightning", Color.decode("#FDBF2F")),
	PSYCHIC("Psychic", Color.decode("#775099")),
	FIGHTING("Fighting", Color.decode("#9D4629")),
	DARKNESS("Darkness", Color.decode("#3A4E4D")),
	METAL("Metal", Color.decode("#73848C")),
	DRAGON("Dragon", Color.decode("#C49F1A")),
	FAIRY("Fairy", Color.decode("#DA4586")),
	RAINBOW("Rainbow", Color.decode("#EFDCA3")),
	SPECIAL("Other/Special", Color.decode("#D4DBDF"));

	public final String displayName;
	public final Color color;

	EnergyType(String displayName, Color color) {
		this.displayName = displayName;
		this.color = color;
	}

}
