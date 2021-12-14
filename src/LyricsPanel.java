import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class LyricsPanel extends JTextArea {
	static Highlighter hl;
	static HighlightPainter p;
	static String[] slist;
	static String[] s;

	LyricsPanel(String s, String[] slist) {
		super(s);
		this.s = s.split(" ");
		setPreferredSize(new Dimension(450, 300));
		hl = this.getHighlighter();
		p = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
		this.slist = slist;
	}

	public static void highlight(int i, int r) {
		int pos = 0;
		int index = 0;

		for (int j = 0; j < r; j++) {
			pos += slist[j].length() + 1;
			index += slist[j].split(" ").length;
		}

		// index-=r;
		for (int k = 0; k < i; k++) {
			//the reason (r!=0) is added is there isn't \n in front of slist.
			if (k == 0 && r!=0) {
				pos += s[k + index].length();
			} else {
				pos += s[k + index].length() + 1;
			}

		}
		try {
			hl.addHighlight(pos, pos + s[index + i].length(), p);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
