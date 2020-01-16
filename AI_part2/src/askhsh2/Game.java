package askhsh2;

import java.util.Scanner;

public class Game {
	

	public static void main(String[] args) {
		
		int white = 4; //NR of white cubes
		int black = 2; // NR of black cubes
		int player_move = 0; // 1 - 4 of available moves
		int previousNRofwhites = white;
		int[] newMoves = {0,white,black}; // magic array, knows if MIN is allowed to move and new whites/blacks
		Node_move maxsMove = null;
		Scanner scanner = new Scanner(System.in);
		
		
		
		System.out.println("Game Start");
		printBoard(newMoves[1],newMoves[2]); // white,black
		
		while(!isFinalMove(newMoves[1],newMoves[2])) {
			
			previousNRofwhites = newMoves[1];
			/*MAX plays*/
			Node_move root = new Node_move("MAX",newMoves[1],newMoves[2],null);
			//call Minimax
			Minimax.dfs(root);
			maxsMove = Minimax.findMAXmove(root);
			newMoves[0] = 0; //not sure
			newMoves[1] = maxsMove.getNRofWhites();
			newMoves[2] = maxsMove.getNRofBlacks();
			System.out.println("MAX made a move.");
			printBoard(newMoves[1],newMoves[2]);
			if(isFinalMove(newMoves[1],newMoves[2])) {
				if(previousNRofwhites == 0) {
					System.out.println("Game ended: TIE");
				}else {
					System.out.println("Game ended: MAX WON");
				}
				break;
			}
			
			/* MIN plays */
			previousNRofwhites = newMoves[1];
			printMoves(newMoves[1],newMoves[2]); // for min to see available moves
			while(newMoves[0] == 0) {
				/* get move from user */
				player_move = 0; // restart move
				while(player_move < 1 || player_move >4) {
					System.out.println("Make your move:");
					player_move = scanner.nextInt();
				}
				newMoves = playerMoves(newMoves[1],newMoves[2],player_move);
				// if is final move -> find if MIN won
				System.out.println("MIN made a move.");
				printBoard(newMoves[1],newMoves[2]);
			}
			if(isFinalMove(newMoves[1],newMoves[2])) {
				if(previousNRofwhites == 0) {
					System.out.println("Game ended: TIE");
				}else {
					System.out.println("Game ended: MIN WON");
				}
			}
			
		}
		
		
		//close scanner, we are done
		scanner.close();
		
	}

	
	
	public static void printBoard(int white,int black) {
		System.out.println("--Cubes--");
		System.out.println("-White: " + white);
		System.out.println("-Black: " + black);
		System.out.println("---------");
	}
	
	public static void printMoves(int white,int black) {
	
		System.out.println("Available moves:");
		if(white>0) {
			System.out.println("1. Remove 1 White Cube");
		}
		if(black>0) {
			System.out.println("2. Remove 1 Black Cube");
		}
		if(white>1 && black>0) {
			System.out.println("3. Remove 2 White Cubes & 1 Black Cube");
		}
		if(white>0 && black>1) {
			System.out.println("4. Remove 1 White Cube & 2 Black Cubes");
		}
		// function should not be called if game is finished
		
	}
	
	/* input: NRofwhite, NRofblack, player_move{1-4} 
	 * output: array[] = {0 = false , new_white, new_black}
	 */
	public static int[] playerMoves(int white,int black,int player_move) {
		
		int[] arr = {0,white,black};
		
		 if(player_move == 1) {
			if(white>0){
				arr[0] = 1; // true - valid move
				arr[1] = white - 1;
			}
		}
		if(player_move == 2) {
			if(black>0) {
				arr[0] = 1; // valid move
				arr[2] = black - 1;
			}
		}
		if(player_move == 3) {
			if(white>1 && black>0) {
				arr[0] = 1; // valid move
				arr[1] = white - 2;
				arr[2] = black -1;
			}
		}
		if(player_move == 4) {
			if(white>0 && black>1) {
				arr[0] = 1; // valid move
				arr[1] = white - 1;
				arr[2] = black - 2;
			}
		}
		return arr;
	}
	
	public static boolean isFinalMove(int white,int black) {
		if(white == 0 && black == 0) {
			return true;
		}
		return false;
	}
	
}
