package askhsh1;

import java.util.PriorityQueue;

public class Astar {
	
	/* closed Set */
	private static int[][] closedSet; 
	/* Search frontier */ 
	private static PriorityQueue<Node_t> searchFrontier; 
	private static int numberOfextensions = 0;

	
	
	public static int astar(int matrix[][],int size,int sx, int sy, int g1x, int g1y,int g2x, int g2y) {
		
		int cost = 0;
		boolean done = false;
		Node_t start, node;
		
		startup(size);
		
		start = new Node_t(sx, sy, 0, null); // start - root node
		
		/* insert starting node in Search frontier */
		searchFrontier.add(start);
		start.setCost(g1x, g1y, g2x, g2y);
		
		
			while(!searchFrontier.isEmpty()) {
				/* get node with minimum height from Search frontier */
				node = searchFrontier.remove();
				
				/* check if node is in closed set*/
					
				if(nodeNotInClosedSet(node.getX(),node.getY())) {
					/* extend node */
					numberOfextensions ++;
					/* check if node is a final state*/
					if((node.getX()==g1x && node.getY()==g1y)||(node.getX()==g2x && node.getY()==g2y)) {
						cost = node.getHeight(); 
						System.out.println("Cost = "+ cost);
						node.printPath(); 
						System.out.println("Number of extensions: "+ numberOfextensions);
						done = true; // found solution
						break;
					}
					/* find children-nodes and add them to the frontier */
					
						/* i-1 , j-1 */
					if(cellIsFree(matrix,size,node.getX()-1,node.getY()-1)) {
						Node_t child = new  Node_t(node.getX()-1,node.getY()-1,node.getHeight()+1,node);
						child.setCost(g1x, g1y, g2x, g2y);
						searchFrontier.add(child);
					}
						/* i , j-1 */
					if(cellIsFree(matrix,size,node.getX(),node.getY()-1)) {
						Node_t child = new  Node_t(node.getX(),node.getY()-1,node.getHeight()+1,node);
						child.setCost(g1x, g1y, g2x, g2y);
						searchFrontier.add(child);
					}
						/* i+1 , j-1 */
					if(cellIsFree(matrix,size,node.getX()+1,node.getY()-1)) {
						Node_t child = new  Node_t(node.getX()+1,node.getY()-1,node.getHeight()+1,node);
						child.setCost(g1x, g1y, g2x, g2y);
						searchFrontier.add(child);
					}
						/* i-1 , j */
					if(cellIsFree(matrix,size,node.getX()-1,node.getY())) {
						Node_t child = new  Node_t(node.getX()-1,node.getY(),node.getHeight()+1,node);
						child.setCost(g1x, g1y, g2x, g2y);
						searchFrontier.add(child);
					}
						/* i+1,j */
					if(cellIsFree(matrix,size,node.getX()+1,node.getY())) {
						Node_t child = new  Node_t(node.getX()+1,node.getY(),node.getHeight()+1,node);
						child.setCost(g1x, g1y, g2x, g2y);
						searchFrontier.add(child);
					}
						/* i-1,j+1 */
					if(cellIsFree(matrix,size,node.getX()-1,node.getY()+1)) {
						Node_t child = new  Node_t(node.getX()-1,node.getY()+1,node.getHeight()+1,node);
						child.setCost(g1x, g1y, g2x, g2y);
						searchFrontier.add(child);
					}
						/* i,j+1 */
					if(cellIsFree(matrix,size,node.getX(),node.getY()+1)) {
						Node_t child = new  Node_t(node.getX(),node.getY()+1,node.getHeight()+1,node);
						child.setCost(g1x, g1y, g2x, g2y);
						searchFrontier.add(child);
					}
					
						/* i+1,j+1 */
					if(cellIsFree(matrix,size,node.getX()+1,node.getY()+1)) {
						Node_t child = new  Node_t(node.getX()+1,node.getY()+1,node.getHeight()+1,node);
						child.setCost(g1x, g1y, g2x, g2y);
						searchFrontier.add(child);
					}
					
					/* add node to closed set */
					addToClosedSet(node.getX(),node.getY());
				}
				
				
				
			}
			if(done == false){
				System.out.println("Can't find solution.");
			}
				
		return cost;
	}
	
	private static boolean cellIsFree(int matrix[][],int N,int x,int y) {
		
		if(x >= 0 && x < N && y >= 0 && y < N && matrix[y][x] == 0 && nodeNotInClosedSet(x,y)){
			return true;
		}
		
		return false;
	}
	
	private static boolean nodeNotInClosedSet(int x,int y) {
		
		if(closedSet[y][x] == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static void addToClosedSet(int x,int y) {
		closedSet[y][x] = 1;
	}
	
	
	
	private static void startup(int N) {
		
		closedSet = new int[N][N];
		searchFrontier = new PriorityQueue<Node_t>();
		
	}
	
}
