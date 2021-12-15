import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MagnifyingPanel extends JPanel {

	private MusicSheetPanel mp;

	MagnifyingPanel(MusicSheetPanel mp) {
		super();
		this.mp = mp;

	}
	public void setMp(MusicSheetPanel mp) {
		this.mp = mp;
	}
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		if (mp.getScanner() != null) {
			BufferedImage img = mp.getG();
			// Image tmp = img.getScaledInstance(1500, 1500, Image.SCALE_SMOOTH);
			int tto = 225;
			int width = 450;
			int x = (MouseLocation.getMouseC() + tto < img.getWidth())
					? (MouseLocation.getMouseC() - tto >= 0) ? MouseLocation.getMouseC() - tto : 0
					: img.getWidth() - tto * 2;
			int y = (MouseLocation.getMouseR() + tto < img.getHeight())
					? (MouseLocation.getMouseR() - tto >= 0) ? MouseLocation.getMouseR() - tto : 0
					: img.getHeight() - tto * 2;

			// System.out.printf("%d , %d , %d, %d, mouseX:%d mouseY:%d\n",img.getWidth(),
			// img.getHeight(), x,y, MouseLocation.getMouseC(), MouseLocation.getMouseR());
			BufferedImage subimg = img.getSubimage(x, y, width, width);

			// w/4 (imgwidth - w- (x max - w/2))? x-(x max - w/2): w/4:x
			int xx = (x > 112) ? (x > img.getWidth() - width - (img.getWidth() - tto * 2 - width / 2))
					? x - (img.getWidth() - tto * 2 - width / 2)
					: width / 4 : x;
			int yy = (y > 112) ? (y > img.getHeight() - width - (img.getWidth() - tto * 2 - width / 2))
					? y - (img.getHeight() - tto * 2 - width / 2)
					: width / 4 : y;
			// y-(y max - w/2
//		System.out.printf("%d, %d, %d\n",x, MouseLocation.getMouseC()-tto,img.getWidth()-tto*2 <- this is x max);

			Image tmp = subimg.getSubimage(xx, yy, width / 2, width / 2);
			tmp = tmp.getScaledInstance(width, width, Image.SCALE_FAST);
			BufferedImage dimg = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = dimg.createGraphics();
			g2d.drawImage(tmp, 0, 0, null);
			g2d.dispose();
			img = dimg;

			g.drawImage(img, 0, 0, null);
			
		}
		validate();
		repaint();
	}
}
