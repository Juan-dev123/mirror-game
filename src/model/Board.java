package model;
import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;

public class Board {
	
	int columns;
	int rows;
	int mirrors;
	int mirrorsAdded;
	private Grid firstGrid;
	
	public Board(int columnsP, int rowsP, int mirrorsP) throws InvalidNumberException, NegativeNumberException{
		columns = columnsP;
		rows = rowsP;
		mirrors = mirrorsP;
		mirrorsAdded=0;
		if(columns<1) {
			throw new NegativeNumberException(2);
		}else if(columns>26) {
			throw new InvalidNumberException(2);
		}else if(rows<1) {
			throw new NegativeNumberException(3);
		}else if(mirrors<1) {
			throw new NegativeNumberException(1);
		}else if(mirrors>columns*rows) {
			throw new InvalidNumberException(1);
		}
		firstGrid=createGrid();
		createBoard(1, null);
		if(mirrorsAdded<mirrors) {
			addMirrors(firstGrid, 1, 1);
		}
	}
	
	private void createBoard(Integer column, Grid last) {
		if(columns==1) {
			createColumn(1, firstGrid);
		}else if(column<columns) {
			if(column==1) {
				Grid last1 = createColumn(rows, firstGrid);
				Grid grid = createGrid();
				Grid last2 = createColumn(rows, grid);
				linkRows(rows, last1, last2);
				createBoard(++column, last2);
			}else {
				Grid grid = createGrid();
				Grid last2 = createColumn(rows, grid);
				linkRows(rows, last, last2);
				createBoard(++column, last2);
			}
		}
	}
	
	private Grid createColumn(Integer row, Grid last) {
		if(row==1) {
			return last;
		}else {
			Grid nextGrid = createGrid();
			last.setDown(nextGrid);
			nextGrid.setUp(last);
			return createColumn(--row, nextGrid);
		}
		
	} 
	
	private Grid getBeginningOfRow(Integer row, Grid first) {
		if(row>1) {
			getBeginningOfRow(--row, first.getDown());
		}
		return first;
	}
	
	private void linkRows(Integer row, Grid last1, Grid last2) {
		if(row>=1) {
			last1.setRight(last2);
			last2.setLeft(last1);
			linkRows(--row, last1.getUp(), last2.getUp());
		}
	}
	
	private Grid createGrid() {
		Grid grid;
		if(mirrorsAdded<mirrors) {
			int typeOfMirror = (int)Math.floor(Math.random()*(3));
			grid=new Grid(typeOfMirror);
			if(typeOfMirror!=0) {
				mirrorsAdded++;
			}
		}else {
			grid = new Grid(0);
		}
		return grid;
	}
	
	private void addMirrors(Grid next, int row, int column) {
		if((row<=rows && column<=columns) || mirrorsAdded<mirrors) {
			if(column==columns) {
				addMirrors(getBeginningOfRow(--row, firstGrid), --row, 1);
			}else if(next.getTypeMirror()==0) {
				int typeOfMirror = (int)Math.floor(Math.random()*(2)+1);
				next.setTypeMirror(typeOfMirror);
				mirrorsAdded++;
				addMirrors(next.getRight(), row, ++column);
			}
		}
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

	public int getMirrorsAdded() {
		return mirrorsAdded;
	}

	public void setMirrorsAdded(int mirrorsAdded) {
		this.mirrorsAdded = mirrorsAdded;
	}

	public Grid getFirstGrid() {
		return firstGrid;
	}

	public void setFirstGrid(Grid firstGrid) {
		this.firstGrid = firstGrid;
	}
	
}
