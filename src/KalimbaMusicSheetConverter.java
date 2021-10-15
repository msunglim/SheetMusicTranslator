import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class KalimbaMusicSheetConverter {

	public static void main(String [] args) {

		JFrame f = new JFrame();


		JLabel jl = new JLabel("변환할 악보");
		jl.setFont(new Font("Serif", Font.BOLD, 20));
		jl.setBounds(100,10,300,150);
		f.add(jl);
		
		JLabel explain = new JLabel("<html>왼쪽 빈칸에 악보에 있는 계이름을 적어주세요. 도 ~ 시, 도\'~시\', 도\"~미\" 이렇게<br> 입력해주셔야 1~7,1\'~7\',1\"~3\"형태로 변환됩니다. <br> 다 적으신뒤 변환하기 버튼을 누르시면 칼림바전용 계이름으로 나옵니다.</html>");
		explain.setFont(new Font("Serif", Font.BOLD, 20));
		explain.setBounds(50,400,850,350);
		f.add(explain);

		JTextArea jta = new JTextArea();
		jta.setBounds(100,100,300,400);


		f.add(jta);

		JLabel motto = new JLabel("Progress and Service");
		motto.setFont(new Font("Serif", Font.BOLD, 10));
		motto.setBounds(850,700,100,50);
		f.add(motto);

		JButton jb = new JButton("변환하기");
		jb.setFont(new Font("Serif", Font.BOLD, 15));
		jb.setBounds(450, 200, 100,50);
		JTextArea jtb = new JTextArea();

		
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Insert code here

				String convertedSheet = "";
				String Sheet = jta.getText();
				for(int i = 0; i< jta.getText().length();i++) {
					switch(Sheet.charAt(i)) {
					case '도': 
						if(i+1!=Sheet.length()) {
							if(Sheet.charAt(i+1)=='\'') {
								convertedSheet += "1' ";		
							}else if(Sheet.charAt(i+1)=='\"') {
								convertedSheet += "1\" ";
							}else {
								convertedSheet += "1 ";
							}

						}else {
							convertedSheet += "1 ";
						}
						break;

					case '레': if(i+1!=Sheet.length()) {
						if(Sheet.charAt(i+1)=='\'') {
							convertedSheet += "2' ";		
						}else if(Sheet.charAt(i+1)=='\"') {
							convertedSheet += "2\" ";
						}else {
							convertedSheet += "2 ";
						}

					}else {
						convertedSheet += "2 ";
					}
					break;

					case '미': if(i+1!=Sheet.length()) {
						if(Sheet.charAt(i+1)=='\'') {
							convertedSheet += "3' ";		
						}else if(Sheet.charAt(i+1)=='\"') {
							convertedSheet += "3\" ";
						}else {
							convertedSheet += "3 ";
						}

					}else {
						convertedSheet += "3 ";
					}
					break;

					case '파': if(i+1!=Sheet.length()) {
						if(Sheet.charAt(i+1)=='\'') {
							convertedSheet += "4' ";		
						}else if(Sheet.charAt(i+1)=='\"') {
							convertedSheet += "4\" ";
						}else {
							convertedSheet += "4 ";
						}

					}else {
						convertedSheet += "4 ";
					}
					break;

					case '솔': if(i+1!=Sheet.length()) {
						if(Sheet.charAt(i+1)=='\'') {
							convertedSheet += "5' ";		
						}else if(Sheet.charAt(i+1)=='\"') {
							convertedSheet += "5\" ";
						}else {
							convertedSheet += "5 ";
						}

					}else {
						convertedSheet += "5 ";
					}
					break;

					case '라': if(i+1!=Sheet.length()) {
						if(Sheet.charAt(i+1)=='\'') {
							convertedSheet += "6' ";		
						}else if(Sheet.charAt(i+1)=='\"') {
							convertedSheet += "6\" ";
						}else {
							convertedSheet += "6 ";
						}

					}else {
						convertedSheet += "6 ";
					}
					break;

					case '시': if(i+1!=Sheet.length()) {
						if(Sheet.charAt(i+1)=='\'') {
							convertedSheet += "7' ";		
						}else if(Sheet.charAt(i+1)=='\"') {
							convertedSheet += "7\" ";
						}else {
							convertedSheet += "7 ";
						}

					}else {
						convertedSheet += "7 ";
					}
					break;


					}


					if( jta.getText().charAt(i) == '\n') {
						convertedSheet+= "\n";
					}
				}
			
				jtb.setText(convertedSheet);
			}
			

		});


		f.add(jb);

		JLabel jl2 = new JLabel("변환된 악보");
		jl2.setFont(new Font("Serif", Font.BOLD, 20));
		jl2.setBounds(600,10,300,150);
		f.add(jl2);
	
		jtb.setBounds(600,100,300,400);

		f.add(jtb);

		f.setSize(1000,800);
		f.setLayout(null);
		f.setVisible(true);


//
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("Please type, all the notes in the music sheet line by line\n악보에 있는 모든 계이름을 입력해주세요 ex)도, 도',도: = 1, 1', 1\"\n입력을 마치셨으면 -1을 입력해주세요. ");
//
//		ArrayList<String> list = new ArrayList<>();
//
//		list.add(sc.nextLine());
//		while(!list.get(list.size()-1).equals("-1")) {
//
//			System.out.println("Please type the notes in the next line:");
//
//			list.add(sc.nextLine());
//
//
//		}
//
//
//		//do re mi pa sol ra si do' re' mi' pa' sol' ra' si' do: re: mi:  도부터 미:까지임
//		for(String s: list) {
//			if(s.equals("-1")) {
//				break;
//			}
//			System.out.println("기존 악보: "+ s);
//			String converted = "";
//			for(int i=0; i< s.length(); i++) {
//				switch(s.charAt(i)) {
//				case '도': 
//					if(i+1!=s.length()) {
//						if(s.charAt(i+1)=='\'') {
//							converted += "1' ";		
//						}else if(s.charAt(i+1)=='\"') {
//							converted += "1\" ";
//						}else {
//							converted += "1 ";
//						}
//
//					}else {
//						converted += "1 ";
//					}
//					break;
//
//				case '레': if(i+1!=s.length()) {
//					if(s.charAt(i+1)=='\'') {
//						converted += "2' ";		
//					}else if(s.charAt(i+1)=='\"') {
//						converted += "2\" ";
//					}else {
//						converted += "2 ";
//					}
//
//				}else {
//					converted += "2 ";
//				}
//				break;
//
//				case '미': if(i+1!=s.length()) {
//					if(s.charAt(i+1)=='\'') {
//						converted += "3' ";		
//					}else if(s.charAt(i+1)=='\"') {
//						converted += "3\" ";
//					}else {
//						converted += "3 ";
//					}
//
//				}else {
//					converted += "3 ";
//				}
//				break;
//
//				case '파': if(i+1!=s.length()) {
//					if(s.charAt(i+1)=='\'') {
//						converted += "4' ";		
//					}else if(s.charAt(i+1)=='\"') {
//						converted += "4\" ";
//					}else {
//						converted += "4 ";
//					}
//
//				}else {
//					converted += "4 ";
//				}
//				break;
//
//				case '솔': if(i+1!=s.length()) {
//					if(s.charAt(i+1)=='\'') {
//						converted += "5' ";		
//					}else if(s.charAt(i+1)=='\"') {
//						converted += "5\" ";
//					}else {
//						converted += "5 ";
//					}
//
//				}else {
//					converted += "5 ";
//				}
//				break;
//
//				case '라': if(i+1!=s.length()) {
//					if(s.charAt(i+1)=='\'') {
//						converted += "6' ";		
//					}else if(s.charAt(i+1)=='\"') {
//						converted += "6\" ";
//					}else {
//						converted += "6 ";
//					}
//
//				}else {
//					converted += "6 ";
//				}
//				break;
//
//				case '시': if(i+1!=s.length()) {
//					if(s.charAt(i+1)=='\'') {
//						converted += "7' ";		
//					}else if(s.charAt(i+1)=='\"') {
//						converted += "7\" ";
//					}else {
//						converted += "7 ";
//					}
//
//				}else {
//					converted += "7 ";
//				}
//				break;
//
//
//				}
//			}
//
//			System.out.println("변환된악보:  "+converted );
//		}



	}
}
