package lab2.pcg.deck.card;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.enums.EnergyType;
import lab2.pcg.deck.enums.PokemonStage;

import java.awt.*;


public class PokemonCard extends Card {
	
	public int hp = 0;
	public PokemonStage stage = PokemonStage.BASIC;
	public String evolvesFrom = "";
	public EnergyType type = EnergyType.COLORLESS;
	
	// No non-default constructor needed, as we always set the default values on creation and then cherry pick fill the ones we want
	
	@Override
	public void displayCard(int row, int col, boolean fullDraw, int selection) {
		Display.resetSelection();
		
		if (fullDraw) {
			Display.cursorPosition(row, 1);
			Display.printColorDesign(Util.readDesignString("pokemon_card"), MAIN_COLOR, SECONDARY_COLOR, type.color, col);
		}
		
		Display.printRightAlignedString(type.displayName, row, col+36, type.color, Color.BLACK, selection);
		Display.printLeftAlignedString(name, row+1, col+1, Color.BLACK, type.color, selection);
		Display.printRightAlignedString("HP " + hp, row+1, col+36, Color.BLACK, type.color, selection);
		Display.printLeftAlignedString(stage.displayName, row+2, col+2, Color.BLACK, MAIN_COLOR, selection);
		Display.printLeftAlignedString(evolvesFrom.length() == 0 ? "" : "←" + evolvesFrom, row+2, col+2+stage.displayName.length(), Color.BLACK, MAIN_COLOR, selection);
		Display.printWrappedString(description, row+13, col+2, Color.BLACK, type.color, 12, 34, selection);
		Display.printRightAlignedString(cardNumber + "/", row+25, col+36-expansionSymbol.length(), Color.BLACK, MAIN_COLOR, selection);
		Display.printRightAlignedString(expansionSymbol, row+25, col+36, Color.BLACK, MAIN_COLOR, selection);
	}
	
	@Override
	public int getNumberOfFields() {
		return 8;
	}
	
	@Override
	public void fillField(int selection, int row, int col) {
		switch (selection) {
			case 0:
				Display.printSimpleString("The Pokémon's type.", row, col);
				type = Util.requestEnergyTypeInput(row+1, col, true);
				break;
			case 1:
				fillName(row, col);
				break;
			case 2:
				Display.printSimpleString("The Pokémon's HP.", row, col);
				hp = Integer.parseInt(Util.requestDigitString(1, 3, row+1, col, true));
				break;
			case 3:
				Display.printSimpleString("The Pokémon's Stage/Evolution/Card type.", row, col);
				stage = Util.requestPokemonStageInput(row+1, col, true);
				break;
			case 4:
				Display.printSimpleString("The Pokémon's previous evolution.", row, col);
				evolvesFrom = Util.requestString(0, 20, row+1, col, true);
				break;
			case 5:
				fillDescription(12, 34, row, col);
				break;
			case 6:
				fillCardNumber(row, col);
				break;
			case 7:
				fillExpansionSymbol(row, col);
				break;
		}
	}
	
	@Override
	public Color getSearchBackground() {
		return type.color;
	}
	
}
