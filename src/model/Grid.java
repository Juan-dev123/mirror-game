package model;

public class Grid {
	
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
	
	public Grid(int mirror) {
		typeMirror = Mirror.values()[mirror];
		mirrorVisible = false;
		mirrorPartiallyVisible = false;
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

	public boolean isMirrorVisible(){
		return mirrorVisible;
	}

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
					}else if(getTypeMirror()==1){
						message="[\\]";
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

	public String toString(String content){
		String message="["+content+"]";
		return message;
	}
	
	
}
