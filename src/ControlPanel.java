import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
		
		JButton help = new JButton("?");

		String howtouse = "\r\n"
				+ "이 프로그램을 사용하는 방법에 대해 알려드립니다.\n"
				+ "1. 스캔할 악보를 고릅니다. 그러면 1차적으로 프로그램이 악보를 스캔합니다.\r\n"
				+ "2. 하늘색 사각형이 모든 4분음표 또는 8분음표위에 떠있나요? 맞다면 3번으로.. 아니라면..\r\n"
				+ "2-1. BlackRecognizer의 수치를 조절하며 rescan을 눌러주세요. 이 값을 올릴수록\n 검은색으로부터 먼 색깔들을 더 잘 인식합니다. 그러나 음표가 아닌것들도 인식해버릴 수 있습니다. \r\n"
				+ "2-2. NoteRecognizer의 수치를 조절하며 rescan을 눌러주세요. 이 값을 올릴수록 더 넓은 영역이 검은색이어야 음표라고 인식합니다.\n [기본적으로 이 프로그램은 일정영역이 검은색이라면 음표로 인식하는 공식을 가지고 있습니다.] \r\n"
				+ "3. 2분음표와 4분음표가 악보상에 있다면 수동으로 (기술적한계로 죄송합니다!!) 찝어주셔야합니다. \r\n"
				+ "마우스를 음표위에 올리고 좌클릭을 하시면 검은색으로 마킹합니다. 모든 2분음표/4분음표 위에 검은색 점을 찍으신 뒤 rescan을 눌러주세요.\r\n"
				+ "4. 음표가 아닌것 위에 하늘색 사각형이 표시되나요? 맞다면 7번으로...아니라면...\n해당영역에서 마우스 우클릭을 하시면 흰색으로 지울 수 있습니다. 이상한 곳들을 전부 지우신 뒤 rescan을 눌러주세요.\r\n"
				+ "4-1. ovalSize를 조절하여 팬크기를 늘리거나 줄일 수 있습니다.\n 넓은 영역을 지우고 싶으실땐 사이즈를 크게하시고 좁은 공간을 세밀하게 지우실 때는 사이즈를 작게해주세요.\r\n"
				+ "5.우측하단에 확대된 악보가 있습니다. 세밀한 작업을 하실때 눈빠지게 왼쪽보지마시고 오른쪽에 확대된 이미지로 편하게 작업하세요.\r\n"
				+ "6. 모든 변화는 << 와 >>버튼으로 취소하거나 복구 할 수 있습니다. \r\n"
				+ "7. 오른쪽 상단에 글로 번역된 계이름들이 있습니다. 그대로 복사하셔서 칼림바음표변환기에 붙여넣기하시면 칼림바용계이름으로 변환시킬 수 있습니다. \r\n"
				+ "\r\n"
				+ "\r\n"
				+ "자세한 코드는 https://github.com/msunglim/KalimbaMusicSheet\r\n"
				+ "Progress and Service 	  \r\n"
				 ;
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame f = new JFrame();
				f.setTitle("How to Use");
				f.add(new JTextArea(howtouse));
				f.setSize(1000,400);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
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
		add(help);
	}
}
