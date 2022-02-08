package ai.sahaj.snakeladder.dto.frontend;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardDto {

	private Integer id;
	private String name;
	private PositionDto startPos;
	private PositionDto finalPos;
	private Set<AccOrDeacceleratorDto> accOrDeAccelerators;
}
