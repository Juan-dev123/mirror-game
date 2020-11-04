package model;

public class Grid {
	
	/**
	 * The types of mirrors
	 */
	enum Mirror{
		NONE,
		LEFT,
		RIGHT
	}
	
	private Grid right;
	private Grid left;
	private Grid up;
	private Grid down;
	private Mirror typeMirror;
	
	private boolean mirrorVisible; 
	private boolean mirrorPartiallyVisible;
	
	/**
	 * Creates an object type Grid
	 * @param mirror The type of mirror
	 */
	public Grid(int mirror) {
		typeMirror = Mirror.values()[mirror];
		mirrorVisible = false;
		mirrorPartiallyVisible = false;
	}

	/**
	 * Returns the grid located to the right
	 * @return The grid
	 */
	public Grid getRight() {
		return right;
	}

	/**
	 * Sets the grid located to the right
	 * @param right The grid
	 */
	public void setRight(Grid right) {
		this.right = right;
	}

	/**
	 * Returns the grid located to the left
	 * @return The grid
	 */
	public Grid getLeft() {
		return left;
	}

	/**
	 * Sets the grid located to the left 
	 * @param left The grid
	 */
	public void setLeft(Grid left) {
		this.left = left;
	}

	/**
	 * Returns the grid located above
	 * @return The grid
	 */
	public Grid getUp() {
		return up;
	}

	/**
	 * Sets the grid located above
	 * @param up The grid
	 */
	public void setUp(Grid up) {
		this.up = up;
	}

	/**
	 * Returns the grid located below
	 * @return The grid
	 */
	public Grid getDown() {
		return down;
	}

	/**
	 * Sets the grid located below
	 * @param down The grid
	 */
	public void setDown(Grid down) {
		this.down = down;
	}

	/**
	 * Returns the type of mirror
	 * @return 1 if is NONE. 2 if is LEFT. 3 if is RIGHT
	 */
	public int getTypeMirror() {
		return typeMirror.ordinal();
	}

	/**
	 * Sets the type of mirror
	 * @param typeMirror 1 if is NONE. 2 if is LEFT. 3 if is RIGHT
	 */
	public void setTypeMirror(int typeMirror) {
		this.typeMirror = Mirror.values()[typeMirror];
	}

	/**
	 * Returns the visibility of the mirror
	 * @return True if the mirror is totally visible. False if is not
	 */
	public boolean isMirrorVisible(){
		return mirrorVisible;
	}

	/**
	 * Sets the visibility of the mirror
	 * @param typeVisibility 0 if the mirror is not visible. 1 if the mirror is visible. 2 if the mirror is partially visible.
	 */
	public void setMirrorVisibility(int typeVisibility){
		switch(typeVisibility){
			case 0:
				mirrorVisible=false;
				mirrorPartiallyVisible=false;
				break;
			case 1:
				mirrorVisible=true;
				break;
			case 2:
				mirrorPartiallyVisible=true;;
				break;
		}
	}

	/**
	 * Returns the visibility of the mirror
	 * @return 0 if the mirror is not visible. 1 if the mirror is visible. 2 if the mirror is partially visible.
	 */
	public int getMirrorVisibility(){
		if(mirrorVisible){
			return 1;
		}else if(mirrorPartiallyVisible){
			return 2;
		}else{
			return 0;
		}
	}

	/**
	 * Returns a visual representation of a grid
	 * @param selected If the grid was selected
	 * @param inclination The supposed inclination of the mirror. 1 if the mirror is inclined to the left. 2 if the mirror is inclined to the right
	 * @return A visual representation of a grid
	 */
	public String toString(boolean selected, int inclination){
		String message="";
		if(selected){
			switch(inclination){
				//Left
				case 1:
					if(getTypeMirror()==0){
						message="[X]";
					}else if(getTypeMirror()==1){
						message="[\\]";
						mirrorVisible=true;
					}else{
						message="[*]";
						mirrorPartiallyVisible=true;
					}
					break;
				//Right
				case 2:
					if(getTypeMirror()==0){
						message="[X]";
					}else if(getTypeMirror()==2){
						message="[/]";
						mirrorVisible=true;
					}else{
						message="[*]";
						mirrorPartiallyVisible=true;
					}
					break;
			}
		}else{
			if(mirrorVisible){
				if(getTypeMirror()==1){
					message="[\\]";
				}else{
					message="[/]";
				}
			}else if(mirrorPartiallyVisible){
				message="[*]";
			}else{
				message="[ ]";
			}
		}
		return message;
	}

	/**
	 * Returns a visual representation of a grid with a content in the middle
	 * @param content The content
	 * @return A visual representation of a grid with a content
	 */
	public String toString(String content){
		String message="["+content+"]";
		return message;
	}
	
	
}
