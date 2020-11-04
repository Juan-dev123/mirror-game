package model;

public class Player implements Comparable<Player>{
	private String nickname;
	private int score;
	private int columns;
	private int rows;
	private int mirrors;
	
	private Player father;
	private Player left;
	private Player right;

	/**
	 * Creates an object type Player
	 * @param nicknameP The nickname
	 * @param scoreP The score
	 * @param columnsP The number of columns of the board where he or she played
	 * @param rowsP The number of rows of the board where he or she played
	 * @param mirrorsP The number of mirrors in the board where he or she played
	 */
	public Player(String nicknameP, int scoreP, int columnsP, int rowsP, int mirrorsP) {
		nickname = nicknameP;
		score = scoreP;
		columns = columnsP;
		rows = rowsP;
		mirrors = mirrorsP;
	}

	/**
	 * Returns the nickname
	 * @return The nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Returns the score
	 * @return The score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns the number of columns of the board where he or she played
	 * @return The number of columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Returns the number of rows of the board where he or she played
	 * @return The number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Returns the number of mirrors in the board where he or she played
	 * @return The number of mirrors
	 */
	public int getMirrors() {
		return mirrors;
	}

	/**
	 * Returns the father, that is, the grid that is above 
	 * @return The father
	 */
	public Player getFather() {
		return father;
	}

	/**
	 * Sets the father, that is, the grid that is above 
	 * @param father The father
	 */
	public void setFather(Player father) {
		this.father = father;
	}

	/**
	 * Returns the child located to the left
	 * @return The child located to the left
	 */
	public Player getLeft() {
		return left;
	}

	/**
	 * Sets the child located to the left
	 * @param left The child located to the left
	 */
	public void setLeft(Player left) {
		this.left = left;
	}

	/**
	 * Returns the child located to the right
	 * @return The child located to the right
	 */
	public Player getRight() {
		return right;
	}

	/**
	 * 
	 * @param right
	 */
	public void setRight(Player right) {
		this.right = right;
	}

	@Override
	public int compareTo(Player otherPlayer) {
		int comp=score-otherPlayer.getScore();
		return comp;
	}
	
	
	
	
}
