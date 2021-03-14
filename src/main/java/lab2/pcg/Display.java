package lab2.pcg;

import java.awt.*;


public class Display {
	
	private static final String ESC = "\u001B";
	private static final String CSI = ESC + "[";
	
	
	
	private static int selectionCount = 0;
	
	
	
	public static void initDisplay() {
		enableAlternativeScreenBuffer();
		hideCursor();
		resetStyle();
		eraseInDisplayFull();
		
		try {
			// Puts the unix terminal in "raw mode", where keyboard inputs are not buffered nor checked by the system. This means we can use the arrow keys or other keys
			// Doing this however is VERY. DANGEROUS. If CTRL-C input is not checked on, it will completely softlock the terminal/unix system inside the java program
			Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "stty raw -echo </dev/tty"}).waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(Display::exitDisplay));
	}
	
	public static void exitDisplay() {
		try {
			Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "stty sane </dev/tty"}).waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		disableAlternativeScreenBuffer();
		showCursor();
	}
	
	
	
	// USE CAREFULLY!!! SIDE EFFECT: Clears the scrollback line buffer of the terminal
	public static void resetTerminalSettings() {printESCCode("c");}
	
	public static void enableAlternativeScreenBuffer() {printCSICode("?1049h");}
	public static void disableAlternativeScreenBuffer() {printCSICode("?1049l");}
	
	public static void showCursor() {printCSICode("?25h");}
	public static void hideCursor() {printCSICode("?25l");}
	
	
	
	public static void eraseInDisplayFromCursor() {printCSICode("0J");}
	public static void eraseInDisplayToCursor() {printCSICode("1J");}
	public static void eraseInDisplayFull() {printCSICode("2J");}
	
	public static void eraseInLineFromCursor() {printCSICode("0K");}
	public static void eraseInLineToCursor() {printCSICode("1K");}
	public static void eraseInLineFull() {printCSICode("2K");}
	
	public static void eraseZone(int row, int col, int height, int width) {
		resetStyle();
		for (int i = 0; i < height; i++) {
			cursorPosition(row + i, col);
			for (int j = 0; j < width; j++) {
				System.out.print(" ");
			}
		}
	}
	
	
	
	public static void cursorPosition(int n, int m) {printCSICode(n + ";" + m + "H");}
	
	public static void cursorUp(int n) {printCSICode(n + "A");}
	public static void cursorDown(int n) {printCSICode(n + "B");}
	public static void cursorRight(int n) {printCSICode(n + "C");}
	public static void cursorLeft(int n) {printCSICode(n + "D");}
	
	public static void cursorNextLine(int n) {printCSICode(n + "E");}
	public static void cursorPreviousLine(int n) {printCSICode(n + "F");}
	
	public static void saveCurrentCursorPosition() {printCSICode("s");}
	public static void restoreSavedCursorPosition() {printCSICode("u");}
	
	
	
	public static void resetStyle() {
		printCSICode("0m");
		setColor(Color.WHITE, Color.BLACK);
	}
	
	public static void setForegroundColor(Color c) {printCSICode(buildColorCode(c, false));}
	public static void setBackgroundColor(Color c) {printCSICode(buildColorCode(c, true));}
	
	public static void setUnderlineOn() {printCSICode("4m");}
	public static void setUnderlineOff() {printCSICode("24m");}
	
	public static void setColor(Color fg, Color bg) {
		setForegroundColor(fg);
		setBackgroundColor(bg);
	}
	
	
	
	public static void printColorDesign(String design, Color mainColor, Color secondaryColor, Color highlightColor, int offset) {
		String offsetString = CSI+offset+"G";
		design = offsetString + design
				.replaceAll("\n", CSI+"1E" + offsetString);
		
		design = design
				.replaceAll("°M", CSI + buildColorCode(mainColor, true))
				.replaceAll("°m", CSI + buildColorCode(mainColor, false))
				.replaceAll("°S", CSI + buildColorCode(secondaryColor, true))
				.replaceAll("°s", CSI + buildColorCode(secondaryColor, false))
				.replaceAll("°H", CSI + buildColorCode(highlightColor, true))
				.replaceAll("°h", CSI + buildColorCode(highlightColor, false))
				.replaceAll("°W", CSI + buildColorCode(Color.WHITE, true))
				.replaceAll("°w", CSI + buildColorCode(Color.WHITE, false))
				.replaceAll("°B", CSI + buildColorCode(Color.BLACK, true))
				.replaceAll("°b", CSI + buildColorCode(Color.BLACK, false))
				.replaceAll("§", CSI)
				.replaceAll("@", CSI+"0m" + CSI+buildColorCode(Color.WHITE, false) + CSI+buildColorCode(Color.BLACK, true));
		
		System.out.print(design);
		System.out.flush();
	}
	
	public static void printSimpleString(String string, int row, int col) {
		cursorPosition(row, col);
		
		System.out.print(string);
		System.out.flush();
	}
	
	public static void printSimpleString(String string, int row, int col, Color fgColor, Color bgColor, boolean clearLine) {
		cursorPosition(row, col);
		if (clearLine) {
			resetStyle();
			eraseInLineFromCursor();
		}
		setColor(fgColor, bgColor);
		
		System.out.print(string);
		System.out.flush();
	}
	
	public static void printLeftAlignedString(String string, int row, int col, Color fgColor, Color bgColor, int selection) {
		checkSelection(fgColor, bgColor, selection);
		cursorPosition(row, col);
		
		System.out.print(checkEmtpy(string));
		System.out.flush();
		
		resetStyle();
	}
	
	public static void printRightAlignedString(String string, int row, int col, Color fgColor, Color bgColor, int selection) {
		string = checkEmtpy(string);
		printLeftAlignedString(string, row, col - string.length() + 1, fgColor, bgColor, selection);
	}
	
	public static void printWrappedString(String string, int row, int col, Color fgColor, Color bgColor, int maxRow, int maxCol, int selection) {
		checkSelection(fgColor, bgColor, selection);
		cursorPosition(row, col);
		
		char[] chars = checkEmtpy(string).replaceAll("\r\n", "\n").toCharArray();
		int rowOffset = 0;
		
		for (int i = 0; i < chars.length; i++) {
			if (i % maxCol < maxCol) {
				System.out.print(chars[i]);
			}
			
			if (chars[i] == '\n') {
				cursorPosition(row, col);
				rowOffset += 1;
				cursorDown(rowOffset);
			}
			
			if (rowOffset == maxRow) {
				break;
			}
		}
		System.out.flush();
		
		resetStyle();
	}
	
	
	
	public static void resetSelection() {
		selectionCount = 0;
	}
	
	private static void checkSelection(Color fgColor, Color bgColor, int selection) {
		if (selection == selectionCount++) {
			setUnderlineOn();
			fgColor = invertColor(fgColor);
			bgColor = invertColor(bgColor);
		}
		
		setColor(fgColor, bgColor);
	}
	
	private static String checkEmtpy(String string) {
		return string.isBlank() ? "....." : string;
	}
	
	
	
	// System.out only automatically flushes on newline characters, so we manually flush
	private static void printESCCode(String code) {
		System.out.print(ESC + code);
		System.out.flush();
	}
	
	private static void printCSICode(String code) {
		System.out.print(CSI + code);
		System.out.flush();
	}
	
	private static String buildColorCode(Color color, boolean background) {
		return buildColorCode(color.getRed(), color.getGreen(), color.getBlue(), background);
	}
	
	private static String buildColorCode(int r, int g, int b, boolean background) {
		return background ? "48;2;"+r+";"+g+";"+b+"m" : "38;2;"+r+";"+g+";"+b+"m";
	}
	
	private static Color invertColor(Color color) {
		return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
	}
	
}
