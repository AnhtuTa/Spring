package hello.exception;

public class BankTransactionException extends Exception {
	private static final long serialVersionUID = -3231595151639790280L;

	public BankTransactionException(String message) {
		super(message);
	}
}
