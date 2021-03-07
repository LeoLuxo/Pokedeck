package lab2.pcg;

import java.util.function.Function;


public class Util {
	
	public static String requestNonEmptyInput() {
		return requestInput(s -> !s.equals(""));
	}
	
	public static String requestYesNoInput() {
		return requestInput(s -> s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n"));
	}
	
	
	
	public static String sanitizeDeckNameInput(String input) {
		return input.strip().replaceAll("\\W+", "_");
	}
	
	
	
	private static String requestInput(Function<String, Boolean> condition) {
		while (true) {
			String input = Pokedeck.scanner.nextLine();
			
			if (condition.apply(input)) {
				return input;
			}
		}
	}
	
}
