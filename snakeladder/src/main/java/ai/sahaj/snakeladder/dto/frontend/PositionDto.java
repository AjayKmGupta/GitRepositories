package ai.sahaj.snakeladder.dto.frontend;

import org.springframework.util.ObjectUtils;

import ai.sahaj.snakeladder.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PositionDto {

	private int id;
	private int number;

	public PositionDto(Position position) {
		if (!ObjectUtils.isEmpty(position)) {
			this.id = position.getId();
			this.number = position.getNumber();
		}
	}

}
