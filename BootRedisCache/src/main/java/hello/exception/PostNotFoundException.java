package hello.exception;

public class PostNotFoundException extends Exception {

	private static final long serialVersionUID = -7378906672147787816L;

	public PostNotFoundException(String message) {
		super(message);
	}
}
