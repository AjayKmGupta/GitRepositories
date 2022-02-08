package ai.sahaj.snakeladder.dto.frontend;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccOrDeacceleratorDto {

	private int id;
	private AccOrDeacceleratorType accOrDeAccType;
	private PositionDto startPos;
	private PositionDto finalPosition;

}
