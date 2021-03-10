package lab2.pcg;

import java.awt.*;


public class Display {
	
	private static final String ESC = "\u001B";
	private static final String CSI = ESC + "[";
	
	
	
	public static void initDisplay() {
		enableAlternativeScreenBuffer();
		disableLineWrap();
		hideCursor();
		setOriginAbsolute();
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			disableAlternativeScreenBuffer();
			resetTerminalSettings();
		}));
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
	
	
	
	public static void cursorPosition(int n, int m) {printCSICode(n + ";" + m + "H");}
	public static void horizontalVerticalPosition(int n, int m) {printCSICode(n + ";" + m + "f");}
	
	public static void cursorNextLine(int n) {printCSICode(n + "E");}
	public static void cursorPreviousLine(int n) {printCSICode(n + "F");}
	
	public static void saveCurrentCursorPosition() {printCSICode("s");}
	public static void restoreSavedCursorPosition() {printCSICode("u");}
	
	
	
	public static void resetStyle() {printCSICode("0m");}
	
	public static void setForegroundColor(Color c) {printCSICode(buildColorCode(c, false));}
	public static void setBackgroundColor(Color c) {printCSICode(buildColorCode(c, true));}
	public static void setForegroundColor(int r, int g, int b) {printCSICode(buildColorCode(r, g, b, false));}
	public static void setBackgroundColor(int r, int g, int b) {printCSICode(buildColorCode(r, g, b, true));}
	
	
	
	public static void printColorDesign(String design, Color highlightColor) {
		design = design
				.replaceAll("€", buildColorCode(highlightColor, false))
				.replaceAll("ç", buildColorCode(highlightColor, true))
				.replaceAll("§", CSI);
		System.out.print(design);
		System.out.flush();
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
	
}
