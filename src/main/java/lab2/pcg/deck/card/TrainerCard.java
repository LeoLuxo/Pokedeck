package lab2.pcg.deck.card;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.enums.Expansion;
import lab2.pcg.deck.enums.TrainerType;

import java.awt.*;


public class TrainerCard extends Card {
	
	public TrainerType type = TrainerType.NONE;
	
	// No non-default constructor needed, as we always set the default values on creation and then cherry pick fill the ones we want
	
	@Override
	public void displayCard(int row, int col, boolean fullDraw, int selection) {
		Display.resetSelection();
		
		if (fullDraw) {
			Display.cursorPosition(row, 1);
			Display.printColorDesign(Util.readDesignString("trainer_card"), MAIN_COLOR, SECONDARY_COLOR, Color.WHITE, col);
		}
		
		Display.printRightAlignedString(type.displayName, row+1, col+35, Color.BLACK, MAIN_COLOR, selection);
		Display.printRightAlignedString(name, row+2, col+35, Color.BLACK, MAIN_COLOR, selection);
		Display.printWrappedString(description, row+13, col+2, Color.BLACK, MAIN_COLOR, 12, 34, selection);
		printExpansion(row, col, SECONDARY_COLOR, selection);
	}
	
	@Override
	public int getNumberOfFields() {
		return 4;
	}
	
	@Override
	public void fillField(int selection) {
	
	}

}
