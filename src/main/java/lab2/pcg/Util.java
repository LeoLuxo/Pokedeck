package lab2.pcg;

import java.util.function.Function;


public class Util {
	
	public static String requestNonEmptyInput() {
		return requestInput(s -> !s.equals(""));
	}
	
	public static boolean requestYesNoInput() {
		return requestInput(s -> s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")).equalsIgnoreCase("y");
	}
	
	// Ugly input code only temporary
	public static int requestOneTwoInput() {
		return Integer.parseInt(requestInput(s -> s.equalsIgnoreCase("1") || s.equalsIgnoreCase("2")));
	}
	
	public static int requestOneTwoThreeInput() {
		return Integer.parseInt(requestInput(s -> s.equalsIgnoreCase("1") || s.equalsIgnoreCase("2") || s.equalsIgnoreCase("3")));
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
