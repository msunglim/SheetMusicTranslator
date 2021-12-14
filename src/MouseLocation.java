import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class MouseLocation implements MouseListener, MouseMotionListener {

	private BufferedImage img;
	private MusicSheetPanel ml;

	private static int mouseR, mouseC;
	// x,y coordinates for pixel of image
	private static int r, c;

	MouseLocation(MusicSheetPanel ml, BufferedImage img) {
		this.img = img;
		this.ml = ml;
		r = 0;
		c = 0;
	}

	public void setImage(BufferedImage img) {
		this.img = img;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1) {
			ml.draw(e.getX(), e.getY(), 0xFF000000);
			// ml.getImage().setRGB(e.getX(),e.getY(),0xFF0000);
			// ml.removeListener();
		}else if(e.getButton() ==MouseEvent.BUTTON3){
			//right click 
			ml.draw(e.getX(), e.getY(), 0xFFFFFFFF);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ml.getPh().setCurr(ml.getImage());
		
		mouseR = e.getY();
		mouseC = e.getX();
		if (e.getButton() == MouseEvent.BUTTON1) {
			ml.draw(e.getX(), e.getY(), 0xFF000000);
			// ml.getImage().setRGB(e.getX(),e.getY(),0xFF0000);
			// ml.removeListener();
		}else if(e.getButton() ==MouseEvent.BUTTON3){
			//right click 
			ml.draw(e.getX(), e.getY(), 0xFFFFFFFF);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	//	System.out.println(MouseEvent.BUTTON1_DOWN_MASK);
		// TODO Auto-generated method stub
		mouseR = e.getY();
		mouseC = e.getX();
		if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
		
			ml.draw(e.getX(), e.getY(), 0xFF000000);
			// ml.getImage().setRGB(e.getX(),e.getY(),0xFF0000);
			// ml.removeListener();
		}else if(e.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK){
			//right click 
			ml.draw(e.getX(), e.getY(), 0xFFFFFFFF);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		try {

			// System.out.println("x"+ e.getX()+", y:"+e.getY() + ", imgW "+img.getWidth()+
			// ", imgH:"+img.getHeight());
			// you are dividing with the same number..
			// coordinate for image? no panel
			
			r = (e.getY() * img.getHeight()) / ml.getImage().getHeight();
			c = (img.getWidth() * e.getX()) / ml.getImage().getWidth();

			int color = img.getRGB(c, r);
			ml.showColor(color);
			mouseR = e.getY();
			mouseC = e.getX();
			MyPanel.showRGBatCursor(color, c, r);
		} catch (Exception ex) {

		}
	}

	public static int getR() {
		return r;
	}

	public static int getC() {
		return c;
	}

	public static int getMouseR() {
		return mouseR;
	}

	public static int getMouseC() {
		return mouseC;
	}
}
