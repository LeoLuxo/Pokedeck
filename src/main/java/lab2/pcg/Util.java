package lab2.pcg;

import lab2.pcg.deck.enums.EnergyType;
import lab2.pcg.deck.enums.Expansion;
import lab2.pcg.deck.enums.TrainerType;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;


public class Util {
	
	
	
	public static final Color QUERY_BG_COLOR = new Color(200, 200, 200);
	public static final Color QUERY_FG_COLOR = new Color(0, 0, 0);
	
	
	
	public static String requestString(int minLength, int maxLength, int row, int col) {
		String charWhitelist = " _-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder currentInput = new StringBuilder();
		
		while (true) {
			Display.setColor(QUERY_FG_COLOR, QUERY_BG_COLOR);
			Display.cursorPosition(row, col);
			System.out.printf("  %-" + (maxLength+1) + "s", "");
			Display.cursorPosition(row, col);
			System.out.print("> " + currentInput.toString() + "_");
			
			char input = (char) requestSingleChar();
			if (currentInput.length() < maxLength && charWhitelist.indexOf(input) >= 0) {
				currentInput.append(input);
			} else if (currentInput.length() > 0 && (input == 8 || input == 127)) { // BACKSPACE or DEL
				currentInput.deleteCharAt(currentInput.length()-1);
			} else if (currentInput.length() >= minLength && input == 13) { // ENTER
				break;
			} else if (input == 27) { // ESC, clear the characters from the buffer that we don't want (arrow keys, etc.)
				if (requestSingleChar() == 91)
					requestSingleChar();
			}
		}
		
		Display.eraseZone(row, col, 1, maxLength+3);
		return currentInput.toString();
	}
	
	
	
	public static int requestMultiChoiceInput(String[] options, int row, int col) {
		String query = "Select one option:";
		
		int maxLength = query.length() - 4;
		for (String s : options) {
			maxLength = Math.max(maxLength, s.length());
		}
		
		Display.setColor(QUERY_FG_COLOR, QUERY_BG_COLOR);
		Display.cursorPosition(row, col);
		Display.setUnderlineOn();
		System.out.print(query);
		Display.setUnderlineOff();
		
		int selected = 0;
		
		while (true) {
			Display.setColor(QUERY_FG_COLOR, QUERY_BG_COLOR);
			for (int i = 0; i < options.length; i++) {
				Display.cursorPosition(row+i+1, col);
				
				if (i == selected)
					System.out.printf("► %-" + maxLength + "s ◄", options[i]);
				else
					System.out.printf("  %-" + maxLength + "s  ", options[i]);
			}
			
			int input = requestSingleChar();
			if (input == 27) { // ESC
				requestSingleChar();
				int arrowKey = requestSingleChar();
				
				if (arrowKey == 65) { // A -> UP
					selected = Math.max(selected - 1, 0);
				} else if (arrowKey == 66) { // B -> DOWN
					selected = Math.min(selected + 1, options.length - 1);
				}
				
			} else if (input == 13) { // ENTER
				break;
			}
		}
		
		Display.eraseZone(row, col, options.length+1, maxLength+4);
		return selected;
	}
	
	
	
	public static int requestSingleChar() {
		Display.setColor(Color.BLACK, Color.BLACK);
		Display.cursorPosition(1, 1);
		
		try {
			int input = System.in.read();
			if (input == 3) {
				// 3 is ETX (end of text), which means the CTRL-C (^C) input, so we forcibly close the program as the terminal will not do it automatically in "raw mode"
				System.exit(0);
			}
			
			Display.eraseInLineToCursor();
			
			return input;
		} catch (IOException e) {
			System.exit(0);
			return 0;
		}
	}
	
	
	
	public static String readDesignString(String name) {
		try {
			return Files.readString(Path.of("art_assets/" + name + ".txt"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
