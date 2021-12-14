import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class PictureHistory {

	private Stack<BufferedImage> prev;
	private Stack<BufferedImage> next;
	private BufferedImage curr;
	private MusicSheetPanel mp;

	PictureHistory(MusicSheetPanel mp, BufferedImage curr) {
		prev = new Stack();
		next = new Stack();
		this.curr = curr;
		this.mp = mp;
	}

	public BufferedImage getCurr() {

		return curr;

	}

	public BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}

	public void setCurr(BufferedImage curr) {
		BufferedImage old = curr;
		prev.add(old);
	

		this.curr = copyImage(curr);
		mp.setImage(curr, curr.getWidth(), curr.getHeight());


	}

	public void prev() {
		next.add(curr);
		if (prev.size() != 0) {
			curr = prev.pop();
		}

	}

	public void next() {
		
		prev.add(curr);
		if (next.size() != 0) {
			this.curr = next.pop();
		}
	}
}
