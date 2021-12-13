import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NoteButton extends JButton{

	private int x,y;
	private String s;
	private int index,row ; //index of note 
	
	NoteButton(String s, int x, int y, int index, int row){
		super(s);
		this.x= x;
		this.y= y;
		this.s = s;
		this.index = index;
		this.row = row;
		this.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LyricsPanel.highlight(index,row);
			}
		});
	}
	
}
