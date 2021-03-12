package lab2.pcg.deck.card;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.enums.EnergyType;
import lab2.pcg.deck.enums.Expansion;

import java.awt.*;


public class EnergyCard extends Card {
	
	public EnergyType type = EnergyType.COLORLESS;
	
	// No non-default constructor needed, as we always set the default values on creation and then cherry pick fill the ones we want
	
	@Override
	public void displayCard(int row, int col, boolean fullDraw, int selection) {
		Display.resetSelection();
		
		if (fullDraw) {
			Display.cursorPosition(row, 1);
			Display.printColorDesign(Util.readDesignString("energy_card"), MAIN_COLOR, SECONDARY_COLOR, type.color, col);
		}
		
		Display.printRightAlignedString(type.displayName, row, col+36, type.color, Color.BLACK, selection);
		Display.printLeftAlignedString(name, row+19, col+2, Color.BLACK, MAIN_COLOR, selection);
		Display.printWrappedString(description, row+20, col+2, Color.BLACK, MAIN_COLOR, 12, 34, selection);
		printExpansion(row, col, MAIN_COLOR, selection);
	}
	
	@Override
	public int getNumberOfFields() {
		return 4;
	}
	
	@Override
	public void fillField(int selection) {
	
	}

}
