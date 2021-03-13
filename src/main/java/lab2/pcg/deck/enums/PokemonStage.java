package lab2.pcg.deck.enums;


import java.util.Arrays;


public enum PokemonStage {
	
	BASIC("Basic"),
	STAGE_1("Stage 1"),
	STAGE_2("Stage 2"),
	LEVEL_UP("Level Up"),
	EX("EX"),
	MEGA("MEGA"),
	SP("SP"),
	LEGEND("LEGEND"),
	RESTORED("Restored"),
	BREAK("BREAK"),
	GX("GX"),
	TAG_TEAM("TAG TEAM"),
	V("V"),
	VMAX("VMAX");
	
	public final String displayName;
	
	PokemonStage(String displayName) {
		this.displayName = displayName;
	}
}
