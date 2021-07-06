package modul4;

public class PlatInexistentException extends RuntimeException {

	public PlatInexistentException() {}
	
	public PlatInexistentException(String errorMessage) {
		super(errorMessage);
	}
	
}
