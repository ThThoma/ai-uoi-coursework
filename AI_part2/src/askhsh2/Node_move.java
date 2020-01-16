package askhsh2;

import java.util.ArrayList;

public class Node_move {

	
	private String player;
	private int value; 
	private int whiteCubes;
	private int blackCubes;
	private Node_move parent;
	private ArrayList<Node_move> children = new ArrayList<Node_move>();
	
	
	//constructor
	public Node_move(String player,int whites,int blacks,Node_move parent) {
		this.player = player;
		this.whiteCubes = whites;
		this.blackCubes = blacks;
		this.parent = parent;
	}
	
	public ArrayList<Node_move> getChildren() {
		return this.children;
	}
	
	public void setChildren(ArrayList<Node_move> chi) {
		 this.children = chi;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
	public String getPlayer() {
		return this.player;
	}
	public int getNRofWhites() {
		return this.whiteCubes;
	}
	public int getNRofBlacks() {
		return this.blackCubes;
	}
	public Node_move getParent() {
		return this.parent;
	}
}
