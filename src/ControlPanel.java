import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	ControlPanel(MyPanel panel, MusicSheetPanel mp, MyScanner sc) {

		JButton rescan = new JButton("Rescan");
		rescan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				File chosenFile = new File("musicSheet3.jpg");
				BufferedImage newPicture = mp.getImage();
//				
				for (int x = 0; x < newPicture.getWidth(); x++) {
					for (int y = 0; y < newPicture.getHeight(); y++) {
						if (newPicture.getRGB(x, y) > -20000000 && newPicture.getRGB(x, y) < -15000000) {

							newPicture.setRGB(x, y, 0xFF000000);
						}
					}
				}

//				try {
//					newPicture = ImageIO.read(chosenFile);
//
//
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				if (newPicture != null) {

					// sc.setImage(newPicture);

					// BufferedImage newImage = sc.getImage();

					MyScanner newScanner = new MyScanner(newPicture, newPicture.getWidth(), newPicture.getHeight());

					BufferedImage newImage = newScanner.getImage();

					mp.getMouseLocation().setImage(newImage);
					panel.refreshLyrics(newScanner.getConverter().getLyrics());

				}
			}
		});

		JButton ibr = new JButton("Increase BlackRecognizer");
		ibr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setBlackRecognizerHelper(SizeManager.getBlackRecognizerHelper() + 1000000);
			}

		});
		JButton dbr = new JButton("Decrease BlackRecognizer");
		dbr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setBlackRecognizerHelper(SizeManager.getBlackRecognizerHelper() - 1000000);
			}

		});
		JButton ios = new JButton("Increase OvalSize");
		ios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setOvalSize(SizeManager.getOvalSize() + 1);
			}

		});
		JButton dos = new JButton("Decrease OvalSize");
		dos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setOvalSize(SizeManager.getOvalSize() - 1);
			}

		});

		JButton inr = new JButton("Increase NoteRecognizer");
		JButton dnr = new JButton("Decrease NoteRecognizer");
		inr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setFullNoteRecognizer(SizeManager.getFullNoteRecognizer() + 1);
			}

		});
		dnr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SizeManager.setFullNoteRecognizer(SizeManager.getFullNoteRecognizer() - 1);
			}

		});

		JButton prev = new JButton("<<");
		JButton next = new JButton(">>");
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mp.getPh().prev();
			}

		});

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mp.getPh().next();
			}

		});
		add(prev);
		add(next);
		add(rescan);
		add(ibr);
		add(dbr);
		add(ios);
		add(dos);
		add(inr);
		add(dnr);

	}
}
