package model;

public class Grid {
	
	enum Mirror{
		NONE,
		LEFT,
		RIGTH
	}
	
	private Grid right;
	private Grid left;
	private Grid up;
	private Grid down;
	private Mirror typeMirror;
	
	public Grid(int mirror) {
		typeMirror = Mirror.values()[mirror];
	}

	public Grid getRight() {
		return right;
	}

	public void setRight(Grid right) {
		this.right = right;
	}

	public Grid getLeft() {
		return left;
	}

	public void setLeft(Grid left) {
		this.left = left;
	}

	public Grid getUp() {
		return up;
	}

	public void setUp(Grid up) {
		this.up = up;
	}

	public Grid getDown() {
		return down;
	}

	public void setDown(Grid down) {
		this.down = down;
	}

	public int getTypeMirror() {
		return typeMirror.ordinal();
	}

	public void setTypeMirror(int typeMirror) {
		this.typeMirror = Mirror.values()[typeMirror];
	}
	
	
	
}
