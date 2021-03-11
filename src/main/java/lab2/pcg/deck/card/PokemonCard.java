package lab2.pcg.deck.card;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.enums.EnergyType;
import lab2.pcg.deck.enums.Expansion;

import java.awt.*;


public class PokemonCard extends Card {
	
	public int hp = 0;
	public int stage = 0;
	public String evolvesFrom = "";
	public EnergyType type = EnergyType.NONE;
	
	// No non-default constructor needed, as we always set the default values on creation and then cherry pick fill the ones we want
	
	@Override
	public void displayCard(int row, int col, boolean fullDraw) {
		if (fullDraw)
			Display.printColorDesign(Util.readDesignString("pokemon_card"), MAIN_COLOR, SECONDARY_COLOR, type.color, col);
		
		Display.printRightAlignedString(type.displayName, row, col+36, type.color, Color.BLACK);
		Display.printLeftAlignedString(name, row+1, col+1, Color.BLACK, type.color);
		Display.printLeftAlignedString("Stage " + stage + (evolvesFrom.length() == 0 ? "" : " ← " + evolvesFrom), row+2, col+2, Color.BLACK, MAIN_COLOR);
		Display.printRightAlignedString("HP " + hp, row+1, col+36, Color.BLACK, type.color);
		Display.printWrappedString(description, row+13, col+2, Color.BLACK, type.color, 12, 34);
		Display.printRightAlignedString(cardNumber + " / " + expansionSymbol, row+25, col+36, Color.BLACK, MAIN_COLOR);
	}

}
