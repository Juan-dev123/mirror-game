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
	
	public String shootLaser(int column, int row, int direction) {
		Grid start = findGrid(column, row, firstGrid);
		Coordinate end = shootLaser(start, column, row, direction);
		Coordinate positionStart = new Coordinate(column, row);
		String board = printBoard(positionStart, end);
		return board; //QUITARRRRR
	}
	
	private Coordinate shootLaser(Grid nextGrid, int column, int row, int direction) {
		switch(nextGrid.getTypeMirror()) {
		//Left
		case 1:
			switch(direction) {
			//up
			case 1:
				direction=4;
				break;
			//down
			case 2:
				direction=3;
				break;
			//left
			case 3:
				direction=2;
				break;
			//right
			case 4:
				direction=1;
				break;
			}
			break;
		//Right
		case 2:
			switch(direction) {
			//up
			case 1:
				direction=3;
				break;
			//down
			case 2:
				direction=4;
				break;
			//left
			case 3:
				direction=1;
				break;
			//right
			case 4:
				direction=2;
				break;
			}
			break;
		}
		switch(direction) {
		//up
		case 1:
			if(nextGrid.getUp()!=null) {
				return shootLaser(nextGrid.getUp(), column, --row, direction);
			}else {
				Coordinate coordinate= new Coordinate(column, row);
				return coordinate;
			}
		//down
		case 2:
			if(nextGrid.getDown()!=null) {
				return shootLaser(nextGrid.getDown(), column, ++row, direction);
			}else {
				Coordinate coordinate= new Coordinate(column, row);
				return coordinate;
			}
		//left
		case 3:
			if(nextGrid.getLeft()!=null) {
				return shootLaser(nextGrid.getLeft(), --column, row, direction);
			}else {
				Coordinate coordinate= new Coordinate(column, row);
				return coordinate;
			}
		//right
		default:
			if(nextGrid.getRight()!=null) {
				return shootLaser(nextGrid.getRight(), ++column, row, direction);
			}else {
				Coordinate coordinate= new Coordinate(column, row);
				return coordinate;
			}
		}
	}
	
	private Grid findGrid(int column, int row, Grid grid) {
		if(row>1) {
			return findGrid(column, --row, grid.getDown());
		}else if(column>1) {
			return findGrid(--column, row, grid.getRight());
		}else {
			return grid;
		}
	}
	
	public String printBoard(Coordinate start, Coordinate end) {
		String board="";
		for(int i=1; i<=rows; i++) {
			for(int j=1; j<=columns; j++) {
				if(j==start.getColumn() && i==start.getRow()) {
					board+="[S]";
				}else if(j==end.getColumn() && i==end.getRow()){
					board+="[E]";
				}else {
					board+="[ ]";
				}
				
			}
			board+="\n";
		}
		return board;
	}
	
	public String printBoard() {
		String board="";
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				board+="[ ]";
			}
			board+="\n";
		}
		return board;
	}
	
	//FALTA TERMINARRRRRRRRRRRRRRRRRRRRRR
	public boolean isACorner(int column, int row) {
		boolean isACorner = false;
		Grid grid=findGrid(column, row, firstGrid);
		if(grid.getUp()==null && grid.getLeft()==null) {
			isACorner = true;
		}
		return isACorner;
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
