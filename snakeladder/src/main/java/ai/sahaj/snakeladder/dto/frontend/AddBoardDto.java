package ai.sahaj.snakeladder.dto.frontend;

import java.util.Set;

import lombok.Data;

@Data
public class AddBoardDto {

	private String name;
	private Set<AccOrDeacceleratorDto> accOrDeaccs;
}
