package lab2.pcg;

import java.awt.*;


public class Display {
	
	private static final String ESC = "\u001B";
	private static final String CSI = ESC + "[";
	
	
	// Due to the nature of the console-based rendering, everything is always drawn sequencially.
	// We can take advantage of this and keep the selection stage global.
	private static int selectionCount = 0;
	
	
	
	public static void initDisplay() {
		enableAlternativeScreenBuffer();
		disableLineWrap();
		hideCursor();
		setOriginAbsolute();
		
		Runtime.getRuntime().addShutdownHook(new Thread(Display::exitDisplay));
	}
	
	public static void exitDisplay() {
		disableAlternativeScreenBuffer();
		resetTerminalSettings();
	}
	
	
	
	public static void resetTerminalSettings() {printESCCode("c");}
	
	public static void setOriginRelative() {printCSICode("?6h");}
	public static void setOriginAbsolute() {printCSICode("?6l");}
	
	// Doesn't seem to work on the WLS Ubuntu terminal
	public static void enableLineWrap() {printCSICode("7h");}
	public static void disableLineWrap() {printCSICode("7l");}
	
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
	
	
	
	public static void cursorPosition(int n, int m) {printCSICode(n + ";" + m + "H");}
	
	public static void cursorUp(int n) {printCSICode(n + "A");}
	public static void cursorDown(int n) {printCSICode(n + "B");}
	public static void cursorRight(int n) {printCSICode(n + "C");}
	public static void cursorLeft(int n) {printCSICode(n + "D");}
	
	public static void cursorNextLine(int n) {printCSICode(n + "E");}
	public static void cursorPreviousLine(int n) {printCSICode(n + "F");}
	
	public static void saveCurrentCursorPosition() {printCSICode("s");}
	public static void restoreSavedCursorPosition() {printCSICode("u");}
	
	
	
	public static void resetStyle() {printCSICode("0m");}
	
	public static void setForegroundColor(Color c) {printCSICode(buildColorCode(c, false));}
	public static void setBackgroundColor(Color c) {printCSICode(buildColorCode(c, true));}
	public static void setForegroundColor(int r, int g, int b) {printCSICode(buildColorCode(r, g, b, false));}
	public static void setBackgroundColor(int r, int g, int b) {printCSICode(buildColorCode(r, g, b, true));}
	
	public static void setUnderlineOn() {printCSICode("4m");}
	public static void setUnderlineOff() {printCSICode("24m");}
	
	
	
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
				.replaceAll("@", CSI + "0m");
		
		System.out.print(design);
		System.out.flush();
	}
	
	public static void printLeftAlignedString(String string, int row, int col, Color fgColor, Color bgColor, int selection) {
		checkSelection(fgColor, bgColor, selection);
		cursorPosition(row, col);
		
		System.out.print(string);
		System.out.flush();
		
		resetStyle();
	}
	
	public static void printRightAlignedString(String string, int row, int col, Color fgColor, Color bgColor, int selection) {
		printLeftAlignedString(string, row, col - string.length() + 1, fgColor, bgColor, selection);
	}
	
	public static void printWrappedString(String string, int row, int col, Color fgColor, Color bgColor, int maxRow, int maxCol, int selection) {
		checkSelection(fgColor, bgColor, selection);
		cursorPosition(row, col);
		
		char[] chars = string.toCharArray();
		int rowOffset = 0;
		
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
			
			if (i % maxCol == maxCol-1) {
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
		
		setForegroundColor(fgColor);
		setBackgroundColor(bgColor);
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
