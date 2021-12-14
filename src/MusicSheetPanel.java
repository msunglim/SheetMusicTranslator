import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MusicSheetPanel extends JPanel {

	private BufferedImage picture;

	private MouseLocation ml;

	private JPanel sample;

	private MyScanner ms;

	private BufferedImage imgg;

	MusicSheetPanel() throws IOException {
		
//		JFileChooser chooser= new JFileChooser();
//
//		int choice = chooser.showOpenDialog(chooser);
//
//		if (choice != JFileChooser.APPROVE_OPTION) return;
//		File chosenFile = chooser.getSelectedFile();

		// this is temporary image file.
		File chosenFile = new File("musicSheet1.jpg");
		picture = ImageIO.read(chosenFile);

		setLayout(null);

		setImage(picture, 800, 1000);

		ms = new MyScanner(picture, picture.getWidth(), picture.getHeight());

		JPanel cp = ms.getConverter().getConvertPanel();
		cp.setBounds(0, 0, 800, 1000);
		add(cp);

		picture = ms.getImage();
		ml = new MouseLocation(this, picture);
		addMouseListener(ml);
		addMouseMotionListener(ml);

		// set vertical scroll bar length as much as music sheet's height
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(new Dimension(300, picture.getHeight()));

		// Sample color you point on
		sample = new JPanel();
		sample.setBackground(Color.GREEN);
		sample.setPreferredSize(new Dimension(25, 25));
		sample.setBorder(BorderFactory.createLineBorder(Color.black));
		add(sample);
		
		 imgg =  new BufferedImage(800, 1000, BufferedImage.TYPE_INT_RGB);

	}

	public void setPicture(BufferedImage p) {
		picture = p;
	}

	public void showColor(int n) {
		int r = n & 0xFF0000, g = n & 0xFF00, b = n & 0xFF;

		sample.setBackground(new Color(n | g | b));

	}

	public void removeListener() {

		removeMouseListener(ml);
		removeMouseMotionListener(ml);

	}

	// set buffered Image and resize. also set JLabel img
	public void setImage(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		picture = dimg;

	}

	public BufferedImage getImage() {
		return picture;
	}

	public void draw(int px, int py, int color) {
//		for (int c = x - 10; c < x + 10; c++) {
//			for (int r = y - 10; r < y + 10; r++) {
//				picture.setRGB(c, r, color);
//
//			}
//		}
		// draw the ellipse
		for (int i = 0; i <= 360; i++) {
			for (int j = 0; j < (SizeManager.getOvalSize()*3)/5; j++) {
				for (int k = 0; k < (SizeManager.getOvalSize())/2; k++) {

					double x, y;
					x = j * Math.sin(Math.toRadians(i));
					y = k * Math.cos(Math.toRadians(i));

					if (i != 0) {
						// draw a line joining previous and new point .
//                g.drawLine((int)px + cx, (int)py + cy,
//                                (int)x + cx, (int)y + cy);
						int cx = (int) x + px;
						int cy = (int) y + py;
						if (cx >= 0 && cy >= 0 && cx < picture.getWidth() && cy < picture.getHeight()) {
							picture.setRGB(cx, cy, color);
						}
					}

					// store the previous points
					// px = x;
					// py = y;

				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics gCopy = imgg.getGraphics();
	
		drawOnGraphics(g);
		drawOnGraphics(gCopy);

	}
	private void drawOnGraphics(Graphics g) {
		if (picture != null) {

			g.drawImage(picture, 0, 0, this);
			g.setColor(Color.BLACK);
			g.drawLine(0, MouseLocation.getMouseR(), picture.getWidth(), MouseLocation.getMouseR());
			g.drawLine(MouseLocation.getMouseC(), 0, MouseLocation.getMouseC(), picture.getHeight());
			g.fillOval(MouseLocation.getMouseC() - (SizeManager.getOvalSize())/2, MouseLocation.getMouseR() - ((SizeManager.getOvalSize()*4)/5)/2, SizeManager.getOvalSize(), (SizeManager.getOvalSize()*4)/5);
			}

		validate();
		repaint();
	}

	public BufferedImage getG() {
		return imgg;

	}

	public MyScanner getScanner() {
		return ms;
	}

	public MouseLocation getMouseLocation() {
		return ml;
	}

}
