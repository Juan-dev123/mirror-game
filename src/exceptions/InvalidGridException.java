package exceptions;

public class InvalidGridException extends Exception{
	
	private static final long serialVersionUID = 1;
	
	public InvalidGridException() {
		super("You can not shoot a laser from that grid");
	}

}
