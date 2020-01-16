package askhsh1;

public class Node_t implements Comparable<Node_t>{
	
	private int height;
	private int h_n = 0;
	private int x;
	private int y;
	private Node_t parent;
	
	

	/* constructor */
	public Node_t(int x,int y, int height, Node_t par) {
		
		this.x = x;
		this.y = y;
		this.height = height;
		this.parent = par;
		
	}
	
	public Node_t getParent() {
		return this.parent;
	}
	public int getHeight() {
		return height;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
	/* for A* */
	public void setCost(int g1x,int g1y,int g2x,int g2y) {
		this.h_n = euretic(g1x,g1y,g2x,g2y);
	}
	
	/* for A* */
	private int euretic(int g1x,int g1y,int g2x,int g2y) {
		int h,
			abs_x1,abs_y1,max1,
			abs_x2,abs_y2,max2;
		
		abs_x1 = abs(this.getX()-g1x);
		abs_y1 = abs(this.getY()-g1y);
		/* get max of |g1x-nx| and |g1y-ny| */
		if(abs_x1>abs_y1){
			max1 = abs_x1;
		}else{
			max1 = abs_y1;
		}
		
		abs_x2 = abs(this.getX()-g2x);
		abs_y2 = abs(this.getY()-g2y);
		/* get max of |g1x-nx| and |g1y-ny| */
		if(abs_x2>abs_y2){
			max2 = abs_x2;
		}else{
			max2 = abs_y2;
		}
		
		/* return min of euretic from the 2 exits*/
		
		if(max1 <= max2) {
			h = max1;
		}else {
			h = max2;
		}
		
		return h;
	}
	
	private int abs(int value){

		int abs;

		if(value>=0){
			abs = value;
		}else{
			abs = value * (-1);
		}
		return abs;
	}
	
	public boolean isSame(Node_t node) {
		
		if(this.x == node.x && this.y == node.y && this.parent == node.parent) {
			return true;
		}
			
		return false;
	}
	public String toString() {
		
		return "(" + this.x + "," + this.y + ")" ;
	}
	
	public void printPath() {
		
		Node_t temp = this; 
		
		
		while(temp.parent != null ) {
			
			System.out.print(temp.toString() + "<-" );
			temp = temp.parent;
		}
		System.out.print(temp.toString()); // this will print starting node
		System.out.println();
	}

	@Override
	public int compareTo(Node_t o) {
		
		int myCost, oCost;
		
		myCost = this.height + this.h_n;
		oCost = o.height + o.h_n;
		
		if(myCost == oCost) {
			return 0;
		}else if(myCost > oCost){
			return 1;
		}else {
			return -1;
		}
		
	}
	


}
