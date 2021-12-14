
public class SizeManager {
	private static int blackRecognizerHelper = 0; //the higher BRH is, the higher chance of detecting blackish color 
	private static int ovalSize = 10;
	private static int fullNoteRecognizer =0; //the higher FUR is, the wider area the program scans 
	
	public static int getBlackRecognizerHelper() {
		return blackRecognizerHelper;
	}
	public static int getOvalSize() {
		return ovalSize;
	}
	public static int getFullNoteRecognizer() {
		return fullNoteRecognizer;
	}
	public static void setBlackRecognizerHelper(int n) {
		blackRecognizerHelper = n;
	}
	public static void setOvalSize(int n) {
		ovalSize = n;
	}
	public static void setFullNoteRecognizer(int n) {
		fullNoteRecognizer = n;
	}
}
