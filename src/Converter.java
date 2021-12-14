import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Converter {

	private String lyrics;
	private BufferedImage p;
	private Passage passage;
	private ArrayList<Passage> plist;
	private String[] lyricsList;
	private MusicSheetPanel msp;

	// used to contain click-able note-suspected area mark
	private JPanel jp;
	private int w, h;

	Converter(BufferedImage p, ArrayList<Passage> plist) {

		this.p = p;
		this.plist = plist;
		this.msp = msp;
		
		lyrics = "";
		jp = new JPanel();
		w = 800;
		h = 1000;
		jp.setPreferredSize(new Dimension(w, h));
		jp.setLayout(null);
		// jp.add(new JLabel("haha!"));
		jp.setBackground(new Color(0, 0, 0, 0));

		// removeRec(cc);
		addToLyrics();

	}

	private void addToLyrics() {
		// recommended x coordinate for each passage!
		int[] rec = new int[plist.size()];
		// index of note for each row
		int[] indexOfNote = new int[plist.size()];

		String[] notes = { "솔'", "파'", "미'", "레'", "도'", "시", "라", "솔", "파", "미", "레", "도" };
		//
		lyricsList = new String[plist.size()];
		for (int i = 0; i < lyricsList.length; i++) {
			lyricsList[i] = "";
		}
		for (int c = 2; c < p.getWidth(); c++) {
			// we must use this
			for (int r = 0; r < plist.size(); r++) {

				passage = plist.get(r);

				for (int k = 0; k < passage.getSize(); k++) {
					if (c + rec[r] >= p.getWidth()) {
						break;

					}
					if (isNote(c + rec[r], passage.getRows()[k])) {
						lyricsList[r] += notes[k * 2 + 1] + "/";
						addToLyrics(c, r, k, indexOfNote, rec, notes, h * passage.getRows()[k] / p.getHeight());
					}
					if (isNote(c + rec[r], passage.getRows()[k] - 3)) {

						lyricsList[r] += notes[k * 2] + "/";
						addToLyrics(c, r, k, indexOfNote, rec, notes, h * passage.getRows()[k] / p.getHeight() - 3);

					}

				}

			}
		}
	}

	private void addToLyrics(int c, int r, int k, int[] indexOfNote, int[] rec, String[] notes, int py) {

		int px = w * (c + rec[r]) / p.getWidth();
		NoteButton n = new NoteButton(notes[k * 2 + 1], px, py, indexOfNote[r], r);
		n.setSize(new Dimension(6, 6));
		n.setBounds(px - n.getWidth() / 2, py - n.getHeight() / 2, 10, 10);
		// jp.add(n);

		rec[r] += 10;
		indexOfNote[r]++;
	}

//  useless method
	// return true if there are enough blacks to be regarded as a note
	private boolean isNote(int x, int y) {
		boolean tf = false;

		// x+4 should be smaller than the width of sheet
		if (x + 10 < p.getWidth() && y - 2 > 0 && y + 2 < p.getHeight()) {

			if (isFullNote(x, y)) {

				tf = true;
			}
			if (tf) {

				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 4; j++) {
						// skyblue highlight
						p.setRGB(x + i, y - 2 + j, 0xFF00FFFF);
					}
				}
			}
			return tf;

		} else {
			return false;
		}
	}

	private boolean isFullNote(int x, int y) {
		// check if its inside is black
		for (int i = x; i < x + 4 + SizeManager.getFullNoteRecognizer(); i++) {
			if (!isBlack(p.getRGB(i, y))) {
				return false;
			}
		}
		for (int j = y - 2 - SizeManager.getFullNoteRecognizer(); j < y + 3
				+ SizeManager.getFullNoteRecognizer(); j++) {
			if (!isBlack(p.getRGB(x + 4 + SizeManager.getFullNoteRecognizer(), j))) {
				return false;
			}
		}
		return (isBlack(p.getRGB(x + 1, y - 1 - SizeManager.getFullNoteRecognizer()))
				&& isBlack(p.getRGB(x + 6 + SizeManager.getFullNoteRecognizer(),
						y - 1 - SizeManager.getFullNoteRecognizer()))
				&& isBlack(p.getRGB(x + 1, y + 1 + SizeManager.getFullNoteRecognizer())) && isBlack(p.getRGB(x, y)));
	}

	private boolean isBlack(int color) {
		return color < -10000000 + SizeManager.getBlackRecognizerHelper()
				&& color > -18000000 - SizeManager.getBlackRecognizerHelper();
		// 6513500 for empty note
	}

	public String getLyrics() {

		for (String s : lyricsList) {
			lyrics += s + "\n";
		}

		return lyrics;
	}

	public String[] getLyricsList() {
		return lyricsList;
	}

	public JPanel getConvertPanel() {
		return jp;
	}
}
//// return 1 if the note is on line, -1 if the note is out of line, 0 it isn't
//// note
//private int isNote(int[] rec, int r, int c, int k) {
////	if(isBlack(p.getRGB(c+rec[r], passage.getRows()[k]-2)) && isBlack(p.getRGB(c+rec[r], passage.getRows()[k]+2))) {
////		int x = c+rec[r];
////		int y = passage.getRows()[k];
////	
////	return 1;
////	
////}else if(isBlack(p.getRGB(c+rec[r], passage.getRows()[k]-2))&& isBlack(p.getRGB(c+rec[r], passage.getRows()[k]-5))) {
////	int x = c+rec[r];
////	int y = passage.getRows()[k]-3;
////
////	return -1;
////}else {
////	return 0;
////}
//	if (isBlack(p.getRGB(c + rec[r], passage.getRows()[k] - 2))
//			&& isBlack(p.getRGB(c + rec[r], passage.getRows()[k] + 2))) {
//		int x = c + rec[r];
//		int y = passage.getRows()[k];
//
//		return 1;
//
//	} else if (isBlack(p.getRGB(c + rec[r], passage.getRows()[k] - 2))
//			&& isBlack(p.getRGB(c + rec[r], passage.getRows()[k] - 5))) {
//		int x = c + rec[r];
//		int y = passage.getRows()[k] - 3;
//
//		return -1;
//	} else {
//		return 0;
//	}
//
//}
//
//private boolean isEmptyNote(int x, int y) {
//	boolean req = isBlack(p.getRGB(x + 7, y)) && isBlack(p.getRGB(x, y)) && isBlack(p.getRGB(x + 2, y - 2))
//			&& isBlack(p.getRGB(x + 4, y + 2))
//
//			&& !isBlack(p.getRGB(x, y - 2));
//	if (!req) {
//		return false;
//	} else {
//		boolean tf = false;
//		for (int j = x - 2; j < x + 2; j++) {
//			boolean blackline = true;
//			for (int i = y+2; i < y + 15; i++) {
//				if (!isBlack(p.getRGB(j, i))) {
//					blackline = false;
//					System.out.println("not black:"+x+","+y +" j:"+j +" i:"+i+", color:"+p.getRGB(j,i));
//					//break;
//				}else {
//					System.out.println("black:"+x+","+y +" j:"+j +" i:"+i+", color:"+p.getRGB(j,i));
//				}
//			}
//			if (blackline) {
//				tf = true;
//				break;
//			}
//		}
//		boolean tf2 = false;
//
//		for (int j = x + 7; j < x + 11; j++) {
//			boolean blackline = true;
//			for (int i = y-1; i > y - 15; i--) {
////			p.setRGB(j,i, 0x00FF00);
//				if (!isBlack(p.getRGB(j, i))) {
//					blackline = false;
////					System.out.println("not black:"+x+","+y +" j:"+j +" i:"+i+", color:"+p.getRGB(j,i));
//					break;
//				}else {
////					System.out.println("black:"+x+","+y +" j:"+j +" i:"+i+", color:"+p.getRGB(j,i));
//					
//				}
//			}
//			if (blackline) {
//				tf2 = true;
//				break;
//			}
//		}
//		// boolean tf3 =tf2;
//		boolean tf3 = tf ^ tf2;
//		if (tf) {
//			for (int i = y; i < y + 15; i++) {
//				p.setRGB(x - 1, i, 0x00aa00);
//			}
//		}
//		if (tf2) {
//			for (int i = y; i > y - 15; i--) {
//				p.setRGB(x + 8, i, 0xaa00aa);
//			}
//		}
//		return req && tf3;
//	}
//isBlack(p.getRGB(x + 7, y))
//	&& isBlack(p.getRGB(x, y)) 
//	&& isBlack(p.getRGB(x +2, y-2)) 
//	&& isBlack(p.getRGB(x +4, y+2))
//	&& !isBlack(p.getRGB(x +3, y-1))
//	&& (!isBlack(p.getRGB(x +4, y+1)) || !isBlack(p.getRGB(x +1, y)))
//	

//}

// remove rectangle connecting notes one another
//private void removeRec(int cc) {
//	for (int c = cc; c < p.getWidth(); c += 2) {
//		for (int r = 0; r < plist.size(); r++) {
//			for (int k = 0; k < plist.get(r).getSize(); k++) {
//				//
//				if (p.getRGB(c, plist.get(r).getRows()[k] + 2) < -1000000
//						&& p.getRGB(c, plist.get(r).getRows()[k] + 5) < -1000000) {
//					if (p.getRGB(c + 8, plist.get(r).getRows()[k] + 2) < -1000000
//							&& p.getRGB(c + 4, plist.get(r).getRows()[k] + 2) < -1000000) {
//
//						for (int w = plist.get(r).getRows()[k] + 2; w < (plist.get(r).getRows()[k] + 2) + 6; w++) {
//							for (int q = c; q < c + 16; q++) {
//								p.setRGB(q, w, -1);
//
//							}
//						}
//
//					}
//				}
//			}
//		}
//	}
//
//}
