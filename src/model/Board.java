package model;
import exceptions.InvalidGridException;
import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;

public class Board {
	
	private int columns;
	private int rows;
	private int mirrors;
	private int mirrorsAdded;
	private boolean cheatActivated;
	private int maxScore;
	private int score;
	private Grid firstGrid;
	
	/**
	 * Creates a new object type Board
	 * @param columnsP The number of columns
	 * @param rowsP The number of rows
	 * @param mirrorsP The number of mirrors
	 * @throws InvalidNumberException If the number of columns is greater than 26 or if the number of mirrors is greter than the number of grids
	 * @throws NegativeNumberException If the numbers are negatives
	 */
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
		cheatActivated = false;
		maxScore=(100*mirrors)+(columns*rows);
		score=0;
	}
	
	/**
	 * Creates a board
	 * @param column The current column
	 * @param last The last grid
	 */
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
	
	/**
	 * Creates a column
	 * @param row The current row
	 * @param last The last gird
	 * @return The last grid created
	 */
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
	
	/**
	 * Returns the first grid of a row
	 * @param row The row
	 * @param first The first grid in a row
	 * @return The grid
	 */
	private Grid getBeginningOfRow(int row, Grid first) {
		if(row>1) {
			return getBeginningOfRow(--row, first.getDown());
		}
		return first;
	}
	
	/**
	 * Links two rows
	 * @param row The current row 
	 * @param last1 The last grid of one row
	 * @param last2 The last grid of the other row
	 */
	private void linkRows(int row, Grid last1, Grid last2) {
		if(row>=1) {
			last1.setRight(last2);
			last2.setLeft(last1);
			linkRows(--row, last1.getUp(), last2.getUp());
		}
	}
	
	/**
	 * Creates a grid
	 * @return The grid
	 */
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
	
	/**
	 * Adds the missing mirrors
	 * @param next The next grid
	 * @param row The current row
	 * @param column The current column
	 */
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
	
	/**
	 * Shoots a laser in the board
	 * @param column The column
	 * @param row The row
	 * @param direction The direction of the laser
	 * @return A visual representation of the board. The grid where the laser entered has an S, the grid where the laser came out has an E. If the grid exits where it enters, so the grid has an J
	 * @throws InvalidGridException If the grid is in the center
	 */
	public String shootLaser(int column, int row, int direction) throws InvalidGridException{
		Grid start = findGrid(column, row, firstGrid);
		if(start.getDown()!=null && start.getUp()!=null && start.getLeft()!=null && start.getRight()!=null) {
			throw new InvalidGridException();
		}
		Grid end = shootLaser(start, direction);
		Grid positionStart = findGrid(column, row, firstGrid);
		String board = printBoard(positionStart, end);
		score--;
		return board;
	}
	
	/**
	 * Shoots a laser in a grid
	 * @param nextGrid The grid
	 * @param direction The direction
	 * @return The grid where the laser came out
	 */
	private Grid shootLaser(Grid nextGrid, int direction) {
		switch(nextGrid.getTypeMirror()) {
		//Left
		case 1:
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
		//Right
		case 2:
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
		}
		switch(direction) {
		//up
		case 1:
			if(nextGrid.getUp()!=null) {
				return shootLaser(nextGrid.getUp(), direction);
			}else {
				return nextGrid;
			}
		//down
		case 2:
			if(nextGrid.getDown()!=null) {
				return shootLaser(nextGrid.getDown(), direction);
			}else {
				return nextGrid;
			}
		//left
		case 3:
			if(nextGrid.getLeft()!=null) {
				return shootLaser(nextGrid.getLeft(), direction);
			}else {
				return nextGrid;
			}
		//right
		default:
			if(nextGrid.getRight()!=null) {
				return shootLaser(nextGrid.getRight(), direction);
			}else {
				return nextGrid;
			}
		}
	}
	
	/**
	 * Finds a grid in the board given the column and the row
	 * @param column The column
	 * @param row The row
	 * @param grid The current grid
	 * @return The searched grid
	 */
	private Grid findGrid(int column, int row, Grid grid) {
		if(row>1) {
			return findGrid(column, --row, grid.getDown());
		}else if(column>1) {
			return findGrid(--column, row, grid.getRight());
		}else {
			return grid;
		}
	}

	/**
	 * Creates a visual representation of the board after the laser passed through it
	 * @param start The grid where the laser entered
	 * @param end The grid where the laser came out
	 * @return A visual representation of the board. The grid where the laser entered has an S, the grid where the laser came out has an E. If the grid exits where it enters, so the grid has an J
	 */
	private String printBoard(Grid start, Grid end) {
		String board=printBoard("", 1, 1, firstGrid, start, end);
		return board;
	}

	/**
	 * Creates a visual representation of the board after the laser passed through it
	 * @param board The visual representation of the board
	 * @param row The current row
	 * @param column The current column
	 * @param nextGrid The current grid
	 * @param start The grid where the laser entered
	 * @param end The grid where the laser came out
	 * @return
	 */
	public String printBoard(String board, int row, int column, Grid nextGrid, Grid start, Grid end) {
		if(column<=columns){
			if(nextGrid==start){
				if(end==start){
					board+=nextGrid.toString("J");
				}else{
					board+=nextGrid.toString("S");
				}
			}else if(nextGrid==end){
				board+=nextGrid.toString("E");
			}else{
				board+=nextGrid.toString(false, 0); 
			}
			return printBoard(board, row, ++column, nextGrid.getRight(), start, end);
		}else if(row<rows){
			row++;
			board+="\n";
			return printBoard(board, row, 1, getBeginningOfRow(row, firstGrid), start, end);
		}else{
			return board;
		}
	}
	/**
	 * Creates a visual representation of the board
	 * @return The visual representation of the board
	 */
	public String printBoard(){
		String board=printBoard("", 1, 1, firstGrid);
		return board;
	}

	/**
	 * Creates a visual representation of the board
	 * @param board The visual representation of the board
	 * @param row The current row
	 * @param column The current column
	 * @param nextGrid The current grid
	 * @return The visual representation of the board
	 */
	public String printBoard(String board, int row, int column, Grid nextGrid) {
		if(column<=columns){
			board+=nextGrid.toString(false, 0); 
			return printBoard(board, row, ++column, nextGrid.getRight());
		}else if(row<rows){
			row++;
			board+="\n";
			return printBoard(board, row, 1, getBeginningOfRow(row, firstGrid));
		}else{
			return board;
		}
	}
	
	/**
	 * Creates a visual representation of the board when the user chooses a grid that may have a mirror
	 * @param board The visual representation of the board
	 * @param row The current row
	 * @param column The current column
	 * @param nextGrid The current grid
	 * @param selected The grid chosen
	 * @param inclination The inclination of the mirror
	 * @return A visual representation of the board. If the selected grid does not have a mirror, then it will be marked with an X. If the selected grid has a mirror but the inclination is not the same as the user says, then it will be marked with *. If the selected grid has a mirror and the inclination is the same as the user says, then it will be marked with / or \
	 */
	private String printBoard(String board, int row, int column, Grid nextGrid, Grid selected, int inclination) {
		if(column<=columns){
			if(selected==nextGrid){
				board+=nextGrid.toString(true, inclination);
			}else{
				board+=nextGrid.toString(false, 0); 
			}
			return printBoard(board, row, ++column, nextGrid.getRight(), selected, inclination);
		}else if(row<rows){
			row++;
			board+="\n";
			return printBoard(board, row, 1, getBeginningOfRow(row, firstGrid), selected, inclination);
		}else{
			return board;
		}
	}

	/**
	 * Creates a visual representation of the board when the user chooses a grid that may have a mirror
	 * @param column The column of the grid chosen
	 * @param row The row of the grid chosen
	 * @param inclination The inclination of the mirror
	 * @param name The nickname of the player
	 * @return The nickname of the user and the mirrors remaining and a visual representation of the board. If the selected grid does not have a mirror, then it will be marked with an X. If the selected grid has a mirror but the inclination is not the same as the user says, then it will be marked with *. If the selected grid has a mirror and the inclination is the same as the user says, then it will be marked with / or \ 
	 */
	public String exposeAMirror(int column, int row, int inclination, String name){
		Grid gridToExpose = findGrid(column, row, firstGrid);
		boolean beforedReveled = gridToExpose.isMirrorVisible();
		String board = printBoard("", 1, 1, firstGrid, gridToExpose, inclination);
		if(gridToExpose.isMirrorVisible() && !beforedReveled){
			mirrorsAdded--;
		}else{
			score-=10;
		}
		String message=name+": "+mirrorsAdded+" mirrors\n";
		return message+board;
	}

	/**
	 * Evaluates if a grid is a corner given its coordinates
	 * @param column The column where is the grid
	 * @param row The row where is the grid
	 * @return True if the grid is a corner. False if it is not
	 */
	public boolean isACorner(int column, int row) {
		boolean isACorner = false;
		Grid grid=findGrid(column, row, firstGrid);
		if(grid.getUp()==null && grid.getLeft()==null) {
			isACorner = true;
		}else if(grid.getUp()==null && grid.getRight()==null) {
			isACorner = true;
		}else if(grid.getDown()==null && grid.getLeft()==null) {
			isACorner = true;
		}else if(grid.getRight()==null && grid.getDown()==null) {
			isACorner = true;
		}
		return isACorner;
	}

	/**
	 * Creates a visual representation of the board with all the mirrors visible
	 * @return The visual representation of the board
	 */
	public String showMirrors(){
		String board = showMirrors("",1, 1, firstGrid);
		cheatActivated = true;
		return board;
	}

	/**
	 * Creates a visual representation of the board with all the mirrors visible
	 * @param board The visual representation of the board
	 * @param row The current row
	 * @param column The current column
	 * @param nextGrid The current grid
	 * @return The visual representation of the board
	 */
	private String showMirrors(String board,int row, int column, Grid nextGrid){
		if(column<=columns){
			if(nextGrid.getTypeMirror()!=0){
				int originalVisibility = nextGrid.getMirrorVisibility();
				nextGrid.setMirrorVisibility(1);
				board+=nextGrid.toString(false, 0);
				nextGrid.setMirrorVisibility(originalVisibility);
			}else{
				board+=nextGrid.toString(false, 0);
			}
			return showMirrors(board, row, ++column, nextGrid.getRight());
		}else if(row<rows){
			row++;
			board+="\n";
			return showMirrors(board, row, 1, getBeginningOfRow(row, firstGrid));
		}else{
			return board;
		}
	}

	/**
	 * Returns the number of columns of the board
	 * @return The number of columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Returns the number of rows of the board
	 * @return The number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Returns the number of mirrors in the board
	 * @return The number of mirrors
	 */
	public int getMirrors() {
		return mirrors;
	}

	/**
	 * Returns the number of mirrors that the user still has to find
	 * @return The number of mirrors
	 */
	public int getMirrorsAdded() {
		return mirrorsAdded;
	}

	/**
	 * Returns the first grid
	 * @return The first grid
	 */
	public Grid getFirstGrid() {
		return firstGrid;
	}
	
	/**
	 * Returns the score that the player obtained
	 * @return
	 */
	public int getScore(){
		int mirrorValue = maxScore/mirrors;
		mirrorValue*=mirrors-mirrorsAdded;
		int finalScore = score;
		finalScore+=mirrorValue;
		if(cheatActivated || score<0){
			score = 0;
		}
		return finalScore;
	}


}
