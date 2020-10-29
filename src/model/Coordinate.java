package model;

public class Coordinate {
	private int column;
	private int row;
	
	public Coordinate(int c, int r) {
		column = c;
		row = r;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	
}
