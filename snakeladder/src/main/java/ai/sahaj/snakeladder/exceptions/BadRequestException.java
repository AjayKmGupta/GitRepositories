package ai.sahaj.snakeladder.exceptions;

import org.springframework.validation.Errors;

public class BadRequestException extends FatalException {

	private Errors errors;

	private static final long serialVersionUID = 1L;

	public BadRequestException() {
	}

	public BadRequestException(ErrorResponse error, Throwable cause) {
		super(error, cause);
	}

	public BadRequestException(String message) {
		super(message);
		super.setError(new ErrorResponse("bad_request", message));
	}

	public BadRequestException(String message, Errors errors) {
		super(message);
		super.setError(new ErrorResponse("bad_request", message));
		this.errors = errors;
	}

}
