package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	/**
	 * Shows a menu on console
	 */
	public static void main(String[] args) {
		try{
			Menu menu = new Menu();
			menu.showMenu();
		}catch(FileNotFoundException fne){
			System.out.println("The file was not found");
		}catch(IOException ioe){
			System.out.println("There were problems loading the players");
		}catch(ClassNotFoundException cnf){
			System.out.println("There were problems converting the file to a Player class");
		}
		
	}

}
