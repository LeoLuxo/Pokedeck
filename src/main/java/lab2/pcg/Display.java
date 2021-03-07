package lab2.pcg;

public class Display {
	
	public static void CursorNextLine(int n) {
		printCSICode(n + "E");
	}
	
	public static void CursorPreviousLine(int n) {
		printCSICode(n + "F");
	}
	
	public static void SaveCurrentCursorPosition() {
//		System.out.print("\u001B7");
		printCSICode("s");
	}
	
	public static void RestoreSavedCursorPosition() {
//		System.out.print("\u001B8");
		printCSICode("u");
	}
	
	
	
	private static void printCSICode(String code) {
		System.out.print("\u001B[" + code);
		System.out.flush();
	}
	
}
