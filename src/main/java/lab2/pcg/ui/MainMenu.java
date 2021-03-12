package lab2.pcg.ui;


import lab2.pcg.Display;
import lab2.pcg.Pokedeck;
import lab2.pcg.Util;

import java.awt.*;


public class MainMenu {

	public static void mainMenuSelector() {
		while (true) {
			Display.printSimpleString("Currently loaded deck: " + Pokedeck.loadedDeck.name, 4, 2, Color.WHITE, Color.BLACK, true);
			Display.printSimpleString("What would you like to do?", 5, 2, true);
			
			int result = Util.requestMultiChoiceInput(new String[]{"Add a card", "View/Search collection", "Switch deck"}, 6, 2);
			
			if (result == 0) {
				CardCreator.designCard();
			} else if (result == 1) {
			
			} else {
				return;
			}
		}
	}
	

}
