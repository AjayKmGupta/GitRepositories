package ai.sahaj.snakeladder.dto.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

	private Long id;
	private String name;
	private String emailId;
}
