package lab2.pcg.deck.card;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.enums.EnergyType;

import java.awt.*;


public class EnergyCard extends Card {

	public EnergyType type = EnergyType.COLORLESS;

	// No non-default constructor needed, as we always set the default values on creation and then cherry pick fill the ones we want

	@Override
	public void displayCard(int row, int col, boolean fullDraw, int selection, boolean checkEmpty) {
		Display.resetSelection();

		if (fullDraw) {
			Display.cursorPosition(row, 1);
			Display.printColorDesign(Util.readDesignString("energy_card"), MAIN_COLOR, SECONDARY_COLOR, type.color, col);
		}

		Display.printRightAlignedString(type.displayName, row, col+36, type.color, Color.BLACK, selection, checkEmpty);
		Display.printLeftAlignedString(name, row+19, col+2, Color.BLACK, MAIN_COLOR, selection, checkEmpty);
		Display.printWrappedString(description, row+20, col+2, Color.BLACK, MAIN_COLOR, 5, 34, selection, checkEmpty);
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
				Display.printSimpleString("The Energy card's type.", row, col);
				type = Util.requestEnergyTypeInput(row+1, col, true);
				break;
			case 1:
				fillName(row, col);
				break;
			case 2:
				fillDescription(5, 34, row, col);
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
		return type.color;
	}

}
