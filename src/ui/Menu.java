package ui;

import java.util.Scanner;

import exceptions.InvalidGridException;
import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;
import model.Game;

public class Menu {
	private Scanner read;
	private Game mirrorGame;
	
	public Menu() {
		read = new Scanner(System.in);
		mirrorGame = new Game();
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
				int score = play();
				if(score!=1){
					System.out.println("Your score is: "+score);
				}else{
					System.out.println("The data was not entered correctly so there is no score");
				}
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
	
	public int play() {
		String[] info = read.nextLine().split(" ");
		if(info.length!=4) {
			System.out.println("Enter the information in a correct way");
			return play();
		}
		String name=info[0];
		int score=-1;
		try {
			int columns = Integer.parseInt(info[1]);
			int rows = Integer.parseInt(info[2]);
			int mirrors = Integer.parseInt(info[3]);
			mirrorGame.createBoard(columns, rows, mirrors);
			System.out.println(mirrorGame.getBoard().printBoard());
			shootLaser(mirrorGame, name);
			score = mirrorGame.getBoard().getScore();
		}catch(InvalidNumberException ine) {
			System.out.println(ine.getMessage());
		}catch(NegativeNumberException nne) {
			System.out.println(nne.getMessage());
		}catch(NumberFormatException nfe) {
			System.out.println("Enter a valid number");
		}
		return score;
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
	
	public void shootLaser(Game game, String name) {
		String coordinate = read.nextLine().toUpperCase();
		int length = coordinate.length();
		if(coordinate.equalsIgnoreCase("cheat")){
			System.out.println("---------------------------------");
			System.out.println(game.getBoard().showMirrors());
			System.out.println("---------------------------------");
			System.out.println(name+": "+game.getBoard().getMirrorsAdded()+" mirrors remaining");
			System.out.println(game.getBoard().printBoard());
			shootLaser(game, name);
		}
		if(!coordinate.equalsIgnoreCase("menu")) {
			if(coordinate.charAt(0)!='L'){
				if(coordinate.charAt(length-1)=='V' || coordinate.charAt(length-1)=='H') {
					if(Character.isLetter(coordinate.charAt(length-2))) {
						if(length<3) {
							System.out.println("Invalid coordenate. Enter it again");
							shootLaser(game, name);
						}else {
							int column = coordinate.charAt(length-2)-64;
							try{
								int row = Integer.parseInt(coordinate.substring(0, length-2));
								int direction = getDirection(column, row, game.getBoard().getColumns(), game.getBoard().getRows(), coordinate.charAt(length-1));
								System.out.println(name+": "+game.getBoard().getMirrorsAdded()+" mirrors remaining");
								System.out.println(game.getBoard().shootLaser(column, row, direction));
								shootLaser(game, name);
							}catch(NumberFormatException nfe) {
								System.out.println("Invalid coordenate. Enter it again");
								shootLaser(game, name);
							}catch(InvalidGridException ige) {
								System.out.println(ige.getMessage());
								shootLaser(game, name);
							}	
						}
					}else {
						int column = coordinate.charAt(length-1)-64;
						try{
							int row = Integer.parseInt(coordinate.substring(0, length-1));
							if(game.getBoard().isACorner(column, row)) {
								System.out.println("Is missing the direction (V/H)");
								shootLaser(game, name);
							}else {
								int direction = getDirection(column, row, game.getBoard().getColumns(), game.getBoard().getRows());
								System.out.println(name+": "+game.getBoard().getMirrorsAdded()+" mirrors remaining");
								System.out.println(game.getBoard().shootLaser(column, row, direction));
								shootLaser(game, name);
							}
						}catch(NumberFormatException nfe){
							System.out.println("Invalid coordenate. Enter it again");
							shootLaser(game, name);
						}catch(InvalidGridException ige) {
							System.out.println(ige.getMessage());
							shootLaser(game, name);
						}
						
					}	
				}else {
					int column = coordinate.charAt(length-1)-64;
					int row = Integer.parseInt(coordinate.substring(0, length-1));
					if(game.getBoard().isACorner(column, row)) {
						System.out.println("Is missing the direction (V/H)");
						shootLaser(game, name);
					}else {
						int direction = getDirection(column, row, game.getBoard().getColumns(), game.getBoard().getRows());
						System.out.println(name+": "+game.getBoard().getMirrorsAdded()+" mirrors remaining");
						try {
							System.out.println(game.getBoard().shootLaser(column, row, direction));
							shootLaser(game, name);
						}catch(InvalidGridException ige) {
							System.out.println(ige.getMessage());
							shootLaser(game, name);
						}
					}
				}
			}else{ //if the first letter is L
				exposeAMirror(game, coordinate, length, name);
			}
		}
	}

	private void exposeAMirror(Game game, String coordinate, int length, String name){
		int column = coordinate.charAt(length-2)-64;
		try{
			int row = Integer.parseInt(coordinate.substring(1,length-2));
			if(coordinate.charAt(length-1)!='R' && coordinate.charAt(length-1)!='L'){
				System.out.println("Invalid inclination. It was expected R or L, but was "+coordinate.charAt(length-1));
				shootLaser(game, name);
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
			System.out.println(game.getBoard().exposeAMirror(column, row, inclination, name));
			if(game.getBoard().getMirrorsAdded()==0){
				System.out.println("Congratulations you have won!");
				showMenu();
			}else{
				shootLaser(game, name);
			}
		}catch(NumberFormatException nfe){
			System.out.println("Invalid coordenate. Enter it again");
			shootLaser(game, name);
		}
		

	}
}

