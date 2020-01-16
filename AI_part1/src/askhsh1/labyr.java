package askhsh1;


import java.util.Scanner;

public class labyr {
	
	

	public static void main(String[] args) {
		
		int N = 30; // size NxN
		double p = 0.3; // probability for blocked cells 
		double r; // for random
		
		
		
		Scanner scanner = new Scanner(System.in);
		 
		int sx = N + 1,
			sy = N + 1,
			g1x = N + 1,
			g1y = N + 1,
			g2x = N + 1,
			g2y = N + 1;
		int[][] labyr= new int[N][N]; 
		
		/* randomize blocks in labyrinth */
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				r = Math.random();
				if( r <= p ) {
					labyr[i][j] = 1; 	// robot cant go to this cell. 
				} else {
					labyr[i][j] = 0;	// this cell is ok to go to.
				}
			}
		}
		
		/* let user see labyrinth */
		printLabyrinth(labyr,N);
		
		/* Get user input */

			/* get start */
		System.out.println("Give the starting position for the robot.");
		while(sx >= N){
			System.out.println("Give x:");
			sx = scanner.nextInt();
		}
		while(sy >= N){
			System.out.println("Give y:");
			sy = scanner.nextInt();
			if(labyr[sy][sx] == 1){
				System.out.println("This cell is blocked.\n");
				sy = N + 1; // ask user for different y 
			}
		}
		
			/* get exits */
		System.out.println("Give the exits of the labyrinth.\n");
			/* get G1 */
		while(g1x >= N){
			System.out.println("Give x of G1:\n");
			g1x = scanner.nextInt();
		}
		while(g1y >= N){
			System.out.println("Give y of G1:");
			g1y = scanner.nextInt();
			if(labyr[g1y][g1x] == 1){
				System.out.println("This cell is blocked.");
				g1y = N + 1; // ask user for different y
			}
		}
			/* get G2 */
		while(g2x >= N){
			System.out.println("Give x of G2:");
			g2x = scanner.nextInt();
		}
		while(g2y >= N){
			System.out.println("Give y of G2:");
			g2y = scanner.nextInt();
			if(labyr[g2y][g2x] == 1){
				System.out.println("This cell is blocked.");
				g2y = N + 1; // ask user for different y 
			}
		}	
		
		scanner.close();
		
		printPositions(sx,sy,g1x,g1y,g2x,g2y);
		printLabyrinth(labyr,N);
		
		/* we are done with user */
		
		/* Call UCS for G1, G2 */
		System.out.println("UCS:");
		UniformCostSearch.UCS(labyr,N,sx,sy,g1x,g1y,g2x,g2y);
		System.out.println("- - - - - -");
		
		
		System.out.println();
		
		/* Call A* for G1, G2 */
		System.out.println("A*:");
		Astar.astar(labyr,N,sx,sy,g1x,g1y,g2x,g2y);
		System.out.println("- - - - - -");
		

	}

		
	public static void printLabyrinth(int matrix[][],int size) {
		
		System.out.println("The labyrinth is: ");
		System.out.print("  ");
		for(int i = 0; i< size; i++){
			System.out.print(" " + i + "  ");
		}
		System.out.println();
		
		for(int i=0;i<size;i++) {
			System.out.print(i + "|");
			for(int j=0;j<size;j++) {
				System.out.print(" " + matrix[i][j] + " |");
			}
			System.out.println();
		}
		
	}
	public static void printPositions(int sx,int sy,int g1x,int g1y,int g2x,int g2y){
		System.out.println("Robot starts at: (" + sx + "," + sy + ")");
		System.out.println("Exits are located at: G1(" + g1x + "," + g1y + ") ,G2(" + g2x + "," + g2y + ")");
	}

}
