package ai.sahaj.snakeladder.exceptions;

public class ResourceNotFoundException extends FatalException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
	}

	public ResourceNotFoundException(ErrorResponse error, Throwable cause) {
		super(error, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
		super.setError(new ErrorResponse("not_found", message));
	}

}
