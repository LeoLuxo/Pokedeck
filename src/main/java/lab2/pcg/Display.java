package lab2.pcg;

public class Display {
	
	public static void initDisplay() {
		enableAlternativeScreenBuffer();
		disableLineWrap();
		hideCursor();
		setOriginAbsolute();
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			disableAlternativeScreenBuffer();
//			enableLineWrap();
//			showCursor();
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
	
	
	private static void printESCCode(String code) {
		System.out.print("\u001B" + code);
		System.out.flush();
	}
	
	private static void printCSICode(String code) {
		System.out.print("\u001B[" + code);
		System.out.flush();
	}
	
}
