package lab2.pcg.deck.card;

import lab2.pcg.Display;
import lab2.pcg.Util;
import lab2.pcg.deck.enums.Expansion;

import java.awt.*;


public abstract class Card {
	
	protected static final Color MAIN_COLOR = new Color(198, 195, 193);
	protected static final Color SECONDARY_COLOR = new Color(135, 135, 135);
	
	
	
	public String name = "";
	public String description = "";
	
	public int cardNumber = 0;
	public String expansionSymbol = "BS";
	
	// No non-default constructor needed, as we always set the default values on creation and then cherry pick fill the ones we want
	
	public abstract void displayCard(int row, int col, boolean fullDraw, int selection);
	
	public abstract void getTypePrefix(int selection, int row, int col);
	
	public abstract int getNumberOfFields();
	public abstract void fillField(int selection, int row, int col);
	
	
	
	protected void fillName(int row, int col) {
		Display.printSimpleString("The card's name.", row, col);
		name = Util.requestString(0, 27, row+1, col, true);
	}
	
	protected void fillDescription(int lines, int chars, int row, int col) {
		Display.printSimpleString("The card's description.", row, col);
		Display.printSimpleString("(max " + lines + " lines)", row+1, col);
		for (int i = 0; i < lines; i++) {
			Display.printSimpleString(String.format("%-"+(chars+2)+"s", ""), row+2+i, col, Util.QUERY_FG_COLOR, Util.QUERY_BG_COLOR, false);
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < lines; i++) {
			builder.append(Util.requestString(0, chars, row + 2 + i, col, false)).append("\n");
		}
		
		description = builder.toString().strip();
	}
	
	protected void fillCardNumber(int row, int col) {
		Display.printSimpleString("The card's number.", row, col);
		cardNumber = Integer.parseInt(Util.requestDigitString(1, 3, row+1, col, true));
	}
	
	protected void fillExpansionSymbol(int row, int col) {
		Display.printSimpleString("The card's expansion.", row, col);
		expansionSymbol = Util.requestString(2, 3, row+1, col, true);
	}
	
}
