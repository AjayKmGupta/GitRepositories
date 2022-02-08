package ai.sahaj.snakeladder.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String error;
	private String errorDescription;

}
