package exceptions;

public class InvalidNumberException extends Exception{

	private static final long serialVersionUID = 1;
	private String message;
	
	public InvalidNumberException(int option) {
		super();
		switch(option) {
			case 1:
				message="The number of mirrors exceed the number of grids";
				break;
			case 2:
				message="The number of columns can not be greater than 26";
				break;
		}
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
