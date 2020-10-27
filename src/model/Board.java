package model;

public class Board {
	
	int columns;
	int rows;
	int mirrors;
	int mirrorsAdded;
	private Grid firstGrid;
	
	public Board(int columnsP, int rowsP, int mirrorsP) {
		columns = columnsP;
		rows = rowsP;
		mirrors = mirrorsP;
		mirrorsAdded=0;
		firstGrid=createGrid();
		createBoard(1, null);
	}
	
	private void createBoard(Integer column, Grid last) {
		if(columns==1) {
			createRow(1, firstGrid);
		}else if(column<columns) {
			if(column==1) {
				Grid last1 = createRow(1, firstGrid);
				Grid grid = createGrid();
				
				Grid last2 = createRow(1, grid);
				linkRows(rows, last1, last2);
				createBoard(column++, last2);
			}else {
				Grid grid = createGrid();
				Grid last2 = createRow(1, grid);
				linkRows(rows, last, last2);
				createBoard(column++, last2);
			}
		}
	}
	
	private Grid createRow(Integer row, Grid last) {
		if(row>0) {
			Grid nextGrid = createGrid();
			last.setDown(nextGrid);
			createRow(row--, nextGrid);
		}
		return last;
	} 
	
	private void linkRows(Integer row, Grid last1, Grid last2) {
		if(row<=rows && last2!=null) {
			last1.setRight(last2);
			last2.setLeft(last1);
			linkRows(row++, last1.getUp(), last2.getUp());
		}
	}
	
	private Grid createGrid() {
		Grid grid;
		if(mirrorsAdded<mirrors) {
			int typeOfMirror = (int)Math.floor(Math.random()*(4));
			grid=new Grid(typeOfMirror);
			if(typeOfMirror!=0) {
				mirrorsAdded++;
			}
		}else {
			grid = new Grid(0);
		}
		return grid;
	}
	
	
}
