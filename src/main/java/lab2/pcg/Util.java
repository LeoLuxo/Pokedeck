package lab2.pcg;

public class Util {
	
	public static String sanitizeDeckNameInput(String input) {
		return input.strip().replaceAll("\\W+", "_");
	}
	
}
