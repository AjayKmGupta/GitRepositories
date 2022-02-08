package ai.sahaj.snakeladder.dto.frontend;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddAccOrDeacceleratorDto {

	private AccOrDeacceleratorType accOrDeAccType;
	private AddPositionDto startPos;
	private AddPositionDto finalPosition;
}
