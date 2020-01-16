package askhsh2;

import java.util.ArrayList;

public class Minimax {

	/*return child with same value as root*/
	public static Node_move findMAXmove(Node_move root) { 
		
		Node_move nextMove = null;
		
		
		for(Node_move child: root.getChildren()) {
			if(child.getValue() == root.getValue()) {
				nextMove = child;
				//return nextMove;
				
			}
		}
		
		return nextMove; 
	}
	
	
	
	
	public static void dfs(Node_move node) {
		
		generateChildren(node);
		
		for(Node_move child: node.getChildren()) {
			dfs(child);
		}
		
		getValue(node);
	}
	
	
	
	/* generate children for node */
	public static void generateChildren(Node_move node) {
		int white,black;
		String nextPlayer;
		
		if(node.getPlayer().equals("MAX")) {
			nextPlayer = "MIN";
		}else {
			nextPlayer = "MAX";
		}
		white = node.getNRofWhites();
		black = node.getNRofBlacks();
		ArrayList<Node_move> children = new ArrayList<Node_move>();
		
		
		if(white > 0) {
			Node_move child = new Node_move(nextPlayer,white-1,black,node); 
			children.add(child);
		}
		if(black > 0) {
			Node_move child = new Node_move(nextPlayer,white,black-1,node); 
			children.add(child);
		}
		if(white>1 && black>0) {
			Node_move child = new Node_move(nextPlayer,white-2,black-1,node); 
			children.add(child);
		}
		if(white>0 && black>1) {
			Node_move child = new Node_move(nextPlayer,white-1,black-2,node); 
			children.add(child);
		}
		if(white == 0 && black ==0) {
			// node is final state
			// probably do nothing
		}
		// give node his children
		node.setChildren(children);
	}
	
	
	
	/* node gets value from his children */
	public static void getValue(Node_move node) {
		
		int max,min;
		
		if(node.getChildren().isEmpty()) {
			//is final state
			if(node.getPlayer().equals("MAX")) {
				
				if(node.getParent().getNRofWhites()==1 && node.getParent().getNRofBlacks() == 0) {
					node.setValue(-10);
				}
				if(node.getParent().getNRofWhites()==0 && node.getParent().getNRofBlacks() == 1) {
					node.setValue(0);
				}
				if(node.getParent().getNRofWhites()==1 && node.getParent().getNRofBlacks() == 2) {
					node.setValue(-10);
				}
				if(node.getParent().getNRofWhites()==2 && node.getParent().getNRofBlacks() == 1) {
					node.setValue(-10);
				}
			}else { // node.getPlayer = MIN
				if(node.getParent().getNRofWhites()==1 && node.getParent().getNRofBlacks() == 0) {
					node.setValue(10);
				}
				if(node.getParent().getNRofWhites()==0 && node.getParent().getNRofBlacks() == 1) {
					node.setValue(0);
				}
				if(node.getParent().getNRofWhites()==1 && node.getParent().getNRofBlacks() == 2) {
					node.setValue(10);
				}
				if(node.getParent().getNRofWhites()==2 && node.getParent().getNRofBlacks() == 1) {
					node.setValue(10);
				}
				
			}
			
		}else {
			if(node.getPlayer().equals("MAX")) {
				//get max value of my children
				max = node.getChildren().get(0).getValue();
				for(Node_move child: node.getChildren()) {
					if(child.getValue() >= max) {
						max = child.getValue();
					}
				}
				node.setValue(max);
			}else { // MIN
				//get min value of my children
				min = node.getChildren().get(0).getValue();
				for(Node_move child: node.getChildren()) {
					if(child.getValue() <= min) {
						min = child.getValue();
					}
				}
				node.setValue(min);
			}
		}
	}
	
	
	
	
}
