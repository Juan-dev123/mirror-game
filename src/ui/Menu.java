package ui;

import java.util.Scanner;

import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;
import model.Board;

public class Menu {
	private Scanner read;
	private Board board;
	
	public Menu() {
		read = new Scanner(System.in);
	}
	
	public void showMenu() {
		System.out.println("What do you want to do?");
		System.out.println("1 Play");
		System.out.println("2 Show the leaderboard");
		System.out.println("3 Exit");
		int option = Integer.parseInt(read.nextLine());
		runOption(option);
		
	}
	
	public void runOption(int option) {
		if(option!=3) {
			switch(option) {
			case 1:
				play();
				break;
			case 2:
				break;
			default:
				System.out.println("Enter a valid number");
				break;
			}
			showMenu();
		}
		System.out.println("Goodbye...");
	}
	
	public void play() {
		String[] info = read.nextLine().split(" ");
		if(info.length!=4) {
			System.out.println("Enter the information in a correct way");
			play();
		}
		String name=info[0];
		int columns = Integer.parseInt(info[1]);
		int rows = Integer.parseInt(info[2]);
		int mirrors = Integer.parseInt(info[3]);
		try {
			board = new Board(columns, rows, mirrors);
			System.out.println(board.printBoard());
			String coordinate = read.nextLine();
			int length = coordinate.length();
			if(length>2) {
				if(coordinate.charAt(length)-2=='V' || coordinate.charAt(length)-2=='H') {
					
				}
			}else {
				int column = coordinate.charAt(length-1)-64;
				int row = Integer.parseInt(coordinate.substring(0, length-1));
				int direction = getDirection(column, row, columns, rows);
				System.out.println(board.shootLaser(column, row, direction));
			}
		}catch(InvalidNumberException ine) {
			System.out.println(ine.getMessage());
			
		}catch(NegativeNumberException nne) {
			System.out.println(nne.getMessage());
		}
	}
	
	public int getDirection(int column, int row, int columns, int rows) {
		int direction;
		if(row==1) {
			direction=2;
		}else if(row==rows) {
			direction=1;
		}else if(column==1) {
			direction=4;
		}else {
			direction=3;
		}
		return direction;
	}
}
