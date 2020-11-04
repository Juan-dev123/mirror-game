package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;

public class Game {
	public static final String DATA_PATH = "data/";

	private Player root;
	private Board board;

	/**
	 * Creates an object type Game
	 * @throws IOException If an I/O error occurs while reading the file
	 * @throws ClassNotFoundException If there is a problem converting the file to a class Player
	 * @throws FileNotFoundException If the file is not found
	 */
	public Game() throws FileNotFoundException, ClassNotFoundException, IOException {
		loadData();
	}

	/**
	 * Creates a board
	 * @param columnsP The number of columns
	 * @param rowsP    The number of rows
	 * @param mirrorsP The number of mirrors
	 * @throws InvalidNumberException  If the number of columns is greater than 26 or if the number of mirrors is greter than the number of grids
	 * @throws NegativeNumberException If the numbers are negatives
	 */
	public void createBoard(int columns, int rows, int mirrors) throws InvalidNumberException, NegativeNumberException {
		board = new Board(columns, rows, mirrors);
	}

	/**
	 * Creates a player
	 * 
	 * @param nickname The nickname
	 * @param score    The score
	 * @param columns  The columns of the board
	 * @param rows     The rows of the board
	 * @param mirrors  The mirrors of the board
	 */
	public void addPlayer(String nickname, int score, int columns, int rows, int mirrors) {
		Player playerToAdd = new Player(nickname, score, columns, rows, mirrors);
		if (root == null) {
			root = playerToAdd;
		} else {
			addPlayer(root, playerToAdd);
		}
	}

	/**
	 * Adds a player in the binary tree
	 * @param root The current root
	 * @param playerToAdd The player to add
	 */
	private void addPlayer(Player root, Player playerToAdd) {
		if (playerToAdd.compareTo(root) > 0) {
			if (root.getRight() == null) {
				root.setRight(playerToAdd);
				playerToAdd.setFather(root);
			} else {
				addPlayer(root.getRight(), playerToAdd);
			}
		} else {
			if (root.getLeft() == null) {
				root.setLeft(playerToAdd);
				playerToAdd.setFather(root);
			} else {
				addPlayer(root.getLeft(), playerToAdd);
			}
		}
	}

	/**
	 * Saves the data of the players
	 * @throws FileNotFoundException If the file is not found
	 * @throws IOException If an I/O error occurs while saving the file
	 */
	public void saveData() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_PATH+"players.mor"));
		oos.writeObject(root);
		oos.close();
	}

	/**
	 * Loads the data of the players
	 * @throws FileNotFoundException If the file is not found
	 * @throws IOException If an I/O error occurs while reading the file
	 * @throws ClassNotFoundException If there is a problem converting the file to a class Player
	 */
	public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(DATA_PATH+"players.mor");
		if(file.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			root = (Player)ois.readObject();
			ois.close();
		}
	}

	/**
	 * Returns the list of players in ascending order
	 * @return The list of players
	 */
	public String printPlayers(){
		String players;
		if(root==null){
			players="There are no players to show";
		}else{
			players=printPlayers("", root);
		}
		return players;
	}

	/**
	 * Returns the list of players in ascending order
	 * @param players The list
	 * @param temporalRoot The temporal root
	 * @return The list of players
	 */
	private String printPlayers(String players, Player temporalRoot){
		if(temporalRoot.getLeft()!=null){
			players = printPlayers(players, temporalRoot.getLeft());
		}
		players+=temporalRoot.toString();
		if(temporalRoot.getRight()!=null){
			players = printPlayers(players, temporalRoot.getRight());
		}
		return players;
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
