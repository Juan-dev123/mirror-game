package ui;

import java.util.Scanner;

import exceptions.InvalidGridException;
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
		try {
			int columns = Integer.parseInt(info[1]);
			int rows = Integer.parseInt(info[2]);
			int mirrors = Integer.parseInt(info[3]);
			board = new Board(columns, rows, mirrors);
			System.out.println(board.printBoard());
			shootLaser(board, name);
		}catch(InvalidNumberException ine) {
			System.out.println(ine.getMessage());
		}catch(NegativeNumberException nne) {
			System.out.println(nne.getMessage());
		}catch(NumberFormatException nfe) {
			System.out.println("Enter a valid number");
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
	
	public int getDirection(int column, int row, int columns, int rows, char direction1) {
		int direction2;
		if(row==1) {
			if(column==1) {
				if(direction1=='V') {
					direction2=2;
				}else {
					direction2=4;
				}
			}else {
				if(direction1=='V') {
					direction2=2;
				}else {
					direction2=3;
				}
			}
		}else if(row==rows) {
			if(columns==1) {
				if(direction1=='V') {
					direction2=1;
				}else {
					direction2=4;
				}
			}else {
				if(direction1=='V') {
					direction2=1;
				}else {
					direction2=3;
				}
			}
			direction2=1;
		}else {
			direction2=-1;
		}
		return direction2;
	}
	
	public void shootLaser(Board board, String name) {
		String coordinate = read.nextLine().toUpperCase();
		int length = coordinate.length();
		if(!coordinate.equalsIgnoreCase("menu")) {
			if(coordinate.charAt(0)!='L'){
				if(coordinate.charAt(length-1)=='V' || coordinate.charAt(length-1)=='H') {
					if(Character.isLetter(coordinate.charAt(length-2))) {
						if(length<3) {
							System.out.println("Invalid coordenate. Enter it again");
							shootLaser(board, name);
						}else {
							int column = coordinate.charAt(length-2)-64;
							try{
								int row = Integer.parseInt(coordinate.substring(0, length-2));
								int direction = getDirection(column, row, board.getColumns(), board.getRows(), coordinate.charAt(length-1));
								System.out.println(name+": "+board.getMirrorsAdded()+" mirrors remaining");
								System.out.println(board.shootLaser(column, row, direction));
								shootLaser(board, name);
							}catch(NumberFormatException nfe) {
								System.out.println("Invalid coordenate. Enter it again");
								shootLaser(board, name);
							}catch(InvalidGridException ige) {
								System.out.println(ige.getMessage());
								shootLaser(board, name);
							}	
						}
					}else {
						int column = coordinate.charAt(length-1)-64;
						try{
							int row = Integer.parseInt(coordinate.substring(0, length-1));
							if(board.isACorner(column, row)) {
								System.out.println("Is missing the direction (V/H)");
								shootLaser(board, name);
							}else {
								int direction = getDirection(column, row, board.getColumns(), board.getRows());
								System.out.println(name+": "+board.getMirrorsAdded()+" mirrors remaining");
								System.out.println(board.shootLaser(column, row, direction));
								shootLaser(board, name);
							}
						}catch(NumberFormatException nfe){
							System.out.println("Invalid coordenate. Enter it again");
							shootLaser(board, name);
						}catch(InvalidGridException ige) {
							System.out.println(ige.getMessage());
							shootLaser(board, name);
						}
						
					}	
				}else {
					int column = coordinate.charAt(length-1)-64;
					int row = Integer.parseInt(coordinate.substring(0, length-1));
					if(board.isACorner(column, row)) {
						System.out.println("Is missing the direction (V/H)");
						shootLaser(board, name);
					}else {
						int direction = getDirection(column, row, board.getColumns(), board.getRows());
						System.out.println(name+": "+board.getMirrorsAdded()+" mirrors remaining");
						try {
							System.out.println(board.shootLaser(column, row, direction));
							shootLaser(board, name);
						}catch(InvalidGridException ige) {
							System.out.println(ige.getMessage());
							shootLaser(board, name);
						}
					}
				}
			}else{ //if the first letter is L
				exposeAMirror(coordinate, length, name);
			}
		}
	}

	private void exposeAMirror(String coordinate, int length, String name){
		int column = coordinate.charAt(length-2)-64;
		try{
			int row = Integer.parseInt(coordinate.substring(1,length-2));
			if(coordinate.charAt(length-1)!='R' && coordinate.charAt(length-1)!='L'){
				System.out.println("Invalid inclination. It was expected R or L, but was "+coordinate.charAt(length-1));
				shootLaser(board, name);
			}
			int inclination=0;
			switch(coordinate.charAt(length-1)){
				case 'R':
					inclination=2;
					break;
				case 'L':
					inclination=1;
					break;
			}
			System.out.println(board.exposeAMirror(column, row, inclination, name));
			if(board.getMirrorsAdded()==0){
				System.out.println("Congratulations you have won!");
				showMenu();
			}else{
				shootLaser(board, name);
			}
		}catch(NumberFormatException nfe){
			System.out.println("Invalid coordenate. Enter it again");
			shootLaser(board, name);
		}
		

	}
}

