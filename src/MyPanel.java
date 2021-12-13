import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MyPanel extends JPanel {

	private MusicSheetPanel mp;

	private static JLabel showRGB;
	
	private LyricsPanel textArea;
	public MyPanel() throws IOException {
		super();

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 1000));


		mp = new MusicSheetPanel();

		JScrollPane scroll = new JScrollPane(mp);
		scroll.setAutoscrolls(true);
		add(scroll, BorderLayout.CENTER);


		//  add(picture);


		JButton increase = new JButton("Increase");
		JButton decrease = new JButton("Decrease");
		//		
		//		add(increase);
		//		add(decrease);


		showRGB = new JLabel("RBG: ");

		showRGB.setHorizontalAlignment(SwingConstants.LEFT);
		showRGB.setVerticalAlignment(SwingConstants.CENTER);
		showRGB.setBounds(0, 100, 100,100);
		add(showRGB,BorderLayout.SOUTH);

		textArea = new LyricsPanel(mp.getScanner().getConverter().getLyrics(),mp.getScanner().getConverter().getLyricsList());
		//scroll panel for lyrics
		JScrollPane scroll2 = new JScrollPane(textArea);
		scroll2.setAutoscrolls(true);
		add(scroll2, BorderLayout.EAST);
		
		ControlPanel cp = new ControlPanel(this, mp, mp.getScanner());
		add( cp, BorderLayout.NORTH);


	}
	public void refreshLyrics(String s) {
		textArea.setText(s);
	}
	public static void showRGBatCursor(int rgb, int x, int y) {
		showRGB.setText("RGB: "+rgb +"   x: " + x +"    y: "+ y);

	}

}