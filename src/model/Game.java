package model;

import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;

public class Game {
	private Player root;
	private Board board;
	
	public Game() {
		
	}
	
	public void createBoard(int columns, int rows, int mirrors) throws InvalidNumberException, NegativeNumberException {
		board = new Board(columns, rows, mirrors);
	}
	
	public void addPlayer(String nickname, int score, int columns, int rows, int mirrors) {
		Player playerToAdd = new Player(nickname, score, columns, rows, mirrors);
		if(root==null){
			root=playerToAdd;
		}else{
			addPlayer(root, playerToAdd);
		}
	}

	public void addPlayer(Player root, Player playerToAdd){
		if(playerToAdd.compareTo(root)>0){
			if(root.getRight()==null){
				root.setRight(playerToAdd);
			}else{
				addPlayer(root.getRight(), playerToAdd);
			}
		}else{
			if(root.getLeft()==null){
				root.setLeft(playerToAdd);
			}else{
				addPlayer(root.getLeft(), playerToAdd);
			}
		}
	}

	public Player getRoot() {
		return root;
	}

	public void setRoot(Player root) {
		this.root = root;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
