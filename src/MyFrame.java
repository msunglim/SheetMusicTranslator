import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MyFrame extends JFrame{

	private JFrame f;
	private MyPanel p;
	
	public MyFrame() throws IOException {
		super();
		
		p = new MyPanel();
		p.setBounds(100,100,600,600);
		add(p);

		
		setTitle("MusicSheetScanner");
		setIconImage(new ImageIcon("icon.PNG").getImage());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1500,800);
		setLocationRelativeTo(null);
		setVisible(true);
		

	}
	
	
}
