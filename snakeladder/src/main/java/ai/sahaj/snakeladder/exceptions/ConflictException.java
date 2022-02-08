package ai.sahaj.snakeladder.exceptions;

public class ConflictException extends FatalException {

	private static final long serialVersionUID = 1L;

	public ConflictException() {
	}

	public ConflictException(ErrorResponse error, Throwable cause) {
		super(error, cause);
	}

	public ConflictException(String message) {
		super(message);
		super.setError(new ErrorResponse("conflict", message));
	}

}
