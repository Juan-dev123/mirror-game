package exceptions;

public class NegativeNumberException extends Exception{
	private static final long serialVersionUID = 1;
	String message;
	
	public NegativeNumberException(int option) {
		super();
		switch(option) {
			//The number of mirrors is negative
			case 1:
				message="The number of mirrors have to be major than zero";
				break;
			//The number of columns is negative
			case 2:
				message="The number of columns have to be major than zero";
				break;
			//The number of rows is negative
			case 3:
				message="The number of rows have to be major than zero";
				break;
		}
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
