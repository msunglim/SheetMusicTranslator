import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class MyScanner {

	private int w,h;
	private BufferedImage pic;

	private Converter converter;
	private ArrayList<Passage> passageList;
	private int r,c;
//
//	private MusicSheetPanel msp;//used for click-able note-suspected area. added to here in order to pass it to converter..
	public MyScanner(BufferedImage p, int w, int h) {
		//System.out.println(p.getWidth());
		this.w=w;
		this.h=h;
		this.pic= p;
		//
		

		scan(pic);

		converter = new Converter(p, passageList);
		
	}


	//scan the music sheet and get the darkest color. and then highlight line-suspected area
	public void scan( BufferedImage p) {
	
		
		Set <Integer > redLine=  new HashSet<>();
		
		for(int r = 75; r < p.getHeight(); r++) {
			int require = 500;
			for(int c = 0; c< p.getWidth(); c++) {
				
		//	System.out.println("req: "+ require);
				//recoginze black 
				if(p.getRGB(c,r)<=-1000000 ) {
					require--;
					if(require == 0) {
						//p.setRGB(c,r,0xFF0000);
						
						redLine.add(r);
						r+=2;  //the number added to r should be less than 6 but more than at least 1.
					//	System.out.println(r);
						continue;
					}
				}
			}
		}
		
		
			//System.out.println("row: "+ row.toString());
		
	
		//highlight all lines
		for(int r= 0; r< redLine.size(); r++) {
			//System.out.println(redLine.toArray()[r]);
			for(int c=  0; c<p.getWidth(); c++) {
				//p.setRGB(c, (int)redLine.toArray()[r],0xFF0000);
			}
		}
		
		List<Integer> lineList = new ArrayList(redLine); // 
		Collections.sort(lineList);

		//passageList is sorted redLine
		 passageList = new ArrayList<>();

		//one Passage has 5 row point 
		//System.out.println("size: "+ lineList.size());
		for(int i = 0; i < lineList.size()/5; i++) {
			Passage pass = new Passage();

			for(int j =0; j<5; j++) {
			    
		//		System.out.println("i:" +i + " , j: "+ j +  " 5xi + j: " + (5*i+j));
				//if(j!=0 && (int)lineList.toArray()[j-1]+2< (int)lineList.toArray()[j]) {
					pass.add((int)lineList.toArray()[5*i+j ]);	
				//}
				
			}
			passageList.add(pass);
		}
		
	}
	
//	
	public void setImage(BufferedImage p) {
		this.pic= p;
	}
	public BufferedImage getImage() {
		return pic;
	}
	
	public Converter getConverter() {
		return converter;
	}
	
}




//
////with the darkest number, assume color of lines is lighter than notes. Highlight pixels which has brighter color than the darkest color by acceptable range.  
//private void detectLines() {
//	//find the darkest color which would be black
//			int darkest = 0;
//			for(int i =0; i<w;i++) {
//				for(int j=0; j<h; j++) {
//
//					if(darkest > p.getRGB(i,j)) {
//						darkest =p.getRGB(i,j);
//					}
//				}
//			}
//			
//			
//	for(int i =0; i<w;i++) {
//		for(int j=0; j<h; j++) {
//			int range = darkest - p.getRGB(i,j);
//			//10000000 all notes
//			//100000000 all blacks are s
//			if(range >-10000000 && range <-5000000 ) {
//
//				//		System.out.println("i "+ i +", j"+j);
//
//				p.setRGB(i,j,0xFF0000);
//				//		System.out.println(new Color(p.getRGB(i,j)));
//
//
//			}
//		}
//	}
//}
//
////among the highlight pixels, highlight horizontally flat (which is a line).
//private void highlightLines() {
//
////
////	System.out.println("P.GET684833: " + Integer.toBinaryString(p.getRGB(684,833)));
////	System.out.println("P.GET684883 EQUAL: " + (p.getRGB(684,833)==0b11111111111111110000000000000000));
////	System.out.println("notes color "+ darkest);
//
//	//45 150 ~ 750 670
//	Set redLine = new HashSet();
//	for(int h= 0; h<this.h; h++) {
//
//		
//		//mercy: even if there is some black things on this coordinates, if the amount of them is less than mercy, it would consider this row as a line. 
//		int mercy = 10; //*****this would be a good idea that let user edit count number with being aware of increasing probability of getting error. 
//		int count = 0 ;
//		for(int w = 0; w<this.w; w++) {
//			//System.out.println("c "+c +", r "+r);
//			if(p.getRGB(w,h)!=0b11111111111111110000000000000000) {
//				mercy--;
//				//System.out.println("w:"+ w + ", mercy: "+ mercy);
//				
//				if(mercy<0) {
//				count=0;
//				mercy = 10;
//				}
//				//						System.out.println("?");
//			}else {
//				count++;
//				if(count >=50 ) {  //*****this would be a good idea that let user edit count number with being aware of increasing probability of getting error. 
//					//							System.out.println("count" + count + "r"+ h + " ,c "+w);
//
//					redLine.add(h);
//				}		
//			}
//
//		}
//	}
//	//sorted list
//	List<Integer> lineList = new ArrayList(redLine); // 
//	Collections.sort(lineList); // 
//	System.out.println(lineList);
////
////	Iterator it= redLine.iterator();
////	while(it.hasNext()) {
////		//System.out.println("r: "+ it.next());
////	}
//	for(int j = 0; j<lineList.size();j++) {
//		for(int i=0; i < 600; i++) {
//			p.setRGB(i+100,lineList.get(j), 0x00FF00);
//
//		}
//	}
//}

