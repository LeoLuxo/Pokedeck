package lab2.pcg.deck.card;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.enums.Expansion;
import lab2.pcg.deck.enums.TrainerType;

import java.awt.*;


public class TrainerCard extends Card {
	
	public TrainerType type = TrainerType.ITEM;
	
	// No non-default constructor needed, as we always set the default values on creation and then cherry pick fill the ones we want
	
	@Override
	public void displayCard(int row, int col, boolean fullDraw, int selection, boolean checkEmpty) {
		Display.resetSelection();
		
		if (fullDraw) {
			Display.cursorPosition(row, 1);
			Display.printColorDesign(Util.readDesignString("trainer_card"), MAIN_COLOR, SECONDARY_COLOR, Color.WHITE, col);
		}
		
		Display.printRightAlignedString(type.displayName, row+1, col+35, Color.BLACK, MAIN_COLOR, selection, checkEmpty);
		Display.printRightAlignedString(name, row+2, col+35, Color.BLACK, MAIN_COLOR, selection, checkEmpty);
		Display.printWrappedString(description, row+13, col+2, Color.BLACK, MAIN_COLOR, 12, 34, selection, checkEmpty);
		Display.printRightAlignedString(cardNumber + "/", row+25, col+36-expansionSymbol.length(), Color.BLACK, MAIN_COLOR, selection, checkEmpty);
		Display.printRightAlignedString(expansionSymbol, row+25, col+36, Color.BLACK, MAIN_COLOR, selection, checkEmpty);
	}
	
	@Override
	public int getNumberOfFields() {
		return 5;
	}
	
	@Override
	public void fillField(int selection, int row, int col) {
		switch (selection) {
			case 0:
				Display.printSimpleString("The Trainer card's type.", row, col);
				type = Util.requestTrainerTypeInput(row+1, col, true);
				break;
			case 1:
				fillName(row, col);
				break;
			case 2:
				fillDescription(12, 34, row, col);
				break;
			case 3:
				fillCardNumber(row, col);
				break;
			case 4:
				fillExpansionSymbol(row, col);
				break;
		}
	}
	
	@Override
	public Color getSearchBackground() {
		return MAIN_COLOR;
	}

}
