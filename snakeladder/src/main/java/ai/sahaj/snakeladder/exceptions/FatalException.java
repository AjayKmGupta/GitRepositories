package ai.sahaj.snakeladder.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FatalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorResponse error;

	public FatalException() {
	}

	public FatalException(ErrorResponse error, Throwable cause) {
		super(error.getErrorDescription(), cause);
		this.error = error;
	}

	public FatalException(String message) {
		super(message);
	}

}
