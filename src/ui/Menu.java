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
		}catch(InvalidNumberException ine) {
			System.out.println(ine.getMessage());
			
		}catch(NegativeNumberException nne) {
			System.out.println(nne.getMessage());
		}
		
		
		
		
		
		
		
	}
}
