package lab2.pcg.ui;


import lab2.pcg.Pokedeck;
import lab2.pcg.Util;


public class MainMenu {

	public static void mainMenuSelector() {
		System.out.println("Currently loaded deck: " + Pokedeck.loadedDeck.name);
		System.out.println("What would you like to do?\n1) Add a card\n2) View/Search collection");
		
		int result = Util.requestOneTwoInput();
		
		if (result == 1) {
			
		} else {
		
		}
	}
	

}
