package modul4;

public class OpcioIncorrectaException extends RuntimeException {

	public OpcioIncorrectaException() {}
	
	public OpcioIncorrectaException(String errorMessage) {
		super(errorMessage);
	}
	
}
