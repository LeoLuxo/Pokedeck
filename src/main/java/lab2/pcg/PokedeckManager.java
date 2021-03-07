package lab2.pcg;

import com.google.gson.Gson;
import lab2.pcg.deck.CardDeck;

import java.io.*;


public class PokedeckManager {
	
	private static final String savePath = "pokedecks/";
	private static final String extension = ".json";
	
	public static boolean deckExists(String name) {
		return getJsonFile(name).canRead();
	}
	
	public static CardDeck loadDeck(String name) {
		try {
			Gson gson = new Gson();
			FileReader reader = new FileReader(getJsonFile(name));
			return gson.fromJson(reader, CardDeck.class);
			
		} catch (FileNotFoundException ex) {
			// If the file can't be found it means that something went horribly wrong (possibly didn't check deckExists beforehand)
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public static void saveDeck(CardDeck deck) {
		File outputFile = getJsonFile(deck.name);
		outputFile.getParentFile().mkdirs();
		
		try {
			FileWriter fw = new FileWriter(outputFile);
			Gson gson = new Gson();
			fw.write(gson.toJson(deck));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createDeck(String name) {
		CardDeck deck = new CardDeck(name);
		saveDeck(deck);
	}
	
	
	
	private static String sanitizeInput(String input) {
		return input.toLowerCase().strip();
	}
	
	private static File getJsonFile(String name) {
		return new File(savePath + name + extension);
	}
	
}
