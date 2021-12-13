
public class Passage {

	private int [] rows;
	private int size;
	Passage(){
		
		rows = new int[6];
		size= 0;
	}
	
	public void add(int copy) {
		//rows[size] = n;
		rows[size]= copy;
		size++;
		if(size ==5) {
			rows[size] = copy+7;
			size++;
		}
		
	}
	
	public int [] getRows() {
		return rows;
	}
	public int getSize() {
		return size;
	}
}
