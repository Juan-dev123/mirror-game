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
	
	public Player(String nicknameP, int scoreP, int columnsP, int rowsP, int mirrorsP) {
		nickname = nicknameP;
		score = scoreP;
		columns = columnsP;
		rows = rowsP;
		mirrors = mirrorsP;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getMirrors() {
		return mirrors;
	}

	public void setMirrors(int mirrors) {
		this.mirrors = mirrors;
	}

	public Player getFather() {
		return father;
	}

	public void setFather(Player father) {
		this.father = father;
	}

	public Player getLeft() {
		return left;
	}

	public void setLeft(Player left) {
		this.left = left;
	}

	public Player getRight() {
		return right;
	}

	public void setRight(Player right) {
		this.right = right;
	}

	@Override
	public int compareTo(Player otherPlayer) {
		int comp=score-otherPlayer.getScore();
		return comp;
	}
	
	
	
	
}
