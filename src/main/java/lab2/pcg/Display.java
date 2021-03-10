package lab2.pcg;

import java.awt.*;


public class Display {
	
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
	
	public static void setForegroundColor(Color c) {setForegroundColor(c.getRed(), c.getGreen(), c.getBlue());}
	public static void setBackgroundColor(Color c) {setBackgroundColor(c.getRed(), c.getGreen(), c.getBlue());}
	public static void setForegroundColor(int r, int g, int b) {printCSICode("38;2;"+r+";"+g+";"+b+"m");}
	public static void setBackgroundColor(int r, int g, int b) {printCSICode("48;2;"+r+";"+g+";"+b+"m");}
	
	
	
	private static void printESCCode(String code) {
		System.out.print("\u001B" + code);
		System.out.flush();
	}
	
	private static void printCSICode(String code) {
		System.out.print("\u001B[" + code);
		System.out.flush();
	}
	
}
