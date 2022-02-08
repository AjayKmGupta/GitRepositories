package ai.sahaj.snakeladder.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.exceptions.ConflictException;
import ai.sahaj.snakeladder.exceptions.ErrorResponse;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(ConflictException exception) {
		log.error("Conflict Exception:------------ ", exception);
		return new ResponseEntity<>(
				new ErrorResponse(exception.getError().getError(), exception.getError().getErrorDescription()),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(ResourceNotFoundException exception) {
		log.error("Not found Exception:------------ ", exception);
		return new ResponseEntity<>(
				new ErrorResponse(exception.getError().getError(), exception.getError().getErrorDescription()),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BadRequestException exception) {
		log.error("Bad Request Exception:------------ ", exception);
		return new ResponseEntity<>(
				new ErrorResponse(exception.getError().getError(), exception.getError().getErrorDescription()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(NumberFormatException exception) {
		log.error("Number Format Exception:------------ ", exception);
		return new ResponseEntity<>(new ErrorResponse("bad_request", "The number should be valid integer"),
				HttpStatus.BAD_REQUEST);
	}

}
