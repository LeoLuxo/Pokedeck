package lab2.pcg.deck.card;

import lab2.pcg.deck.enums.Expansion;

import java.awt.*;


public abstract class Card {
	
	protected static final Color MAIN_COLOR = new Color(198, 195, 193);
	protected static final Color SECONDARY_COLOR = new Color(135, 135, 135);
	
	
	
	public String name = "";
	public String description = "";
	
	public int cardNumber = 0;
	public Expansion expansionSymbol = Expansion.NONE;
	
	// No non-default constructor needed, as we always set the default values on creation and then cherry pick fill the ones we want
	
	public abstract void displayCard(int row, int col, boolean fullDraw);
	
}
