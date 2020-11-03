package model;

import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;

public class Game {
	private Player firstPlace;
	private Board board;
	
	public Game() {
		
	}
	
	public void createBoard(int columns, int rows, int mirrors) throws InvalidNumberException, NegativeNumberException {
		board = new Board(columns, rows, mirrors);
	}

	public Player getFirstPlace() {
		return firstPlace;
	}

	public void setFirstPlace(Player firstPlace) {
		this.firstPlace = firstPlace;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
