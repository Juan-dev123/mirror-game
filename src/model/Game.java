package model;

import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;

public class Game {
	private Player root;
	private Board board;
	
	/**
	 * Creates an object type Game
	 */
	public Game() {
		
	}
	
	/**
	 * Creates a board
	 * @param columnsP The number of columns
	 * @param rowsP The number of rows
	 * @param mirrorsP The number of mirrors
	 * @throws InvalidNumberException If the number of columns is greater than 26 or if the number of mirrors is greter than the number of grids
	 * @throws NegativeNumberException If the numbers are negatives
	 */
	public void createBoard(int columns, int rows, int mirrors) throws InvalidNumberException, NegativeNumberException {
		board = new Board(columns, rows, mirrors);
	}
	
	/**
	 * Creates a player
	 * @param nickname The nickname
	 * @param score The score
	 * @param columns The columns of the board 
	 * @param rows The rows of the board
	 * @param mirrors The mirrors of the board
	 */
	public void addPlayer(String nickname, int score, int columns, int rows, int mirrors) {
		Player playerToAdd = new Player(nickname, score, columns, rows, mirrors);
		if(root==null){
			root=playerToAdd;
		}else{
			addPlayer(root, playerToAdd);
		}
	}

	/**
	 * Adds a player in the binary tree
	 * @param root The current root
	 * @param playerToAdd The player to add
	 */
	private void addPlayer(Player root, Player playerToAdd){
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

	/**
	 * Returns the root of the binary tree of players
	 * @return The root
	 */
	public Player getRoot() {
		return root;
	}

	/**
	 * Returns the board
	 * @return The board
	 */
	public Board getBoard() {
		return board;
	}
}
