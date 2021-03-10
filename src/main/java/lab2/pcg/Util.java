package lab2.pcg;

import lab2.pcg.deck.enums.EnergyType;
import lab2.pcg.deck.enums.Expansion;
import lab2.pcg.deck.enums.TrainerType;

import java.util.function.Function;


public class Util {
	
	// Ugly input code only temporary
	public static String requestAnyString() {
		return requestInput(s -> true);
	}
	
	public static String requestNonEmptyString() {
		return requestInput(s -> !s.equals(""));
	}
	
	public static boolean requestYesNoBoolean() {
		return requestInput(s -> s.equalsIgnoreCase("y") || s.equalsIgnoreCase("n")).equalsIgnoreCase("y");
	}
	
	public static int requestInteger() {
		return requestRangedInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	public static int requestRangedInteger(int min, int max) {
		return Integer.parseInt(requestInput(s -> {
			if (s.matches("-?\\d+")) {
				int i = Integer.parseInt(s);
				if (i >= min && i <= max)
					return true;
			}
			return false;
		}));
	}
	
	public static Expansion requestExpansion() {
		return Expansion.valueOf(requestInput(s -> {
			try {
				Expansion.valueOf(s);
				return true;
			} catch (IllegalArgumentException ex) {
				return false;
			}
		}));
	}
	
	public static EnergyType requestEnergyType() {
		return EnergyType.valueOf(requestInput(s -> {
			try {
				EnergyType.valueOf(s);
				return true;
			} catch (IllegalArgumentException ex) {
				return false;
			}
		}));
	}
	
	public static TrainerType requestTrainerType() {
		return TrainerType.valueOf(requestInput(s -> {
			try {
				TrainerType.valueOf(s);
				return true;
			} catch (IllegalArgumentException ex) {
				return false;
			}
		}));
	}
	
	
	
	public static String sanitizeDeckNameInput(String input) {
		return input.strip().replaceAll("\\W+", "_");
	}
	
	
	
	private static String requestInput(Function<String, Boolean> condition) {
		while (true) {
			String input = Pokedeck.scanner.nextLine();
			
			if (condition.apply(input)) {
				Display.horizontalVerticalPosition(1, 1);
				return input;
			}
		}
	}
	
}
