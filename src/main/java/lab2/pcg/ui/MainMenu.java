package lab2.pcg.ui;


import lab2.pcg.Display;
import lab2.pcg.Pokedeck;
import lab2.pcg.Util;


public class MainMenu {

	public static void mainMenuSelector() {
		while (true) {
			Display.cursorPosition(4, 2);
			System.out.println("Currently loaded deck: " + Pokedeck.loadedDeck.name);
			System.out.println("What would you like to do?\n1) Add a card\n2) View/Search collection\n3) Switch deck");
			
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
