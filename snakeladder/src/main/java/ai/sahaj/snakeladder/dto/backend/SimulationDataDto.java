package ai.sahaj.snakeladder.dto.backend;

import java.util.List;

import ai.sahaj.snakeladder.entity.Player;
import ai.sahaj.snakeladder.entity.Roll;
import lombok.Data;

@Data
public class SimulationDataDto {

	private Player winner;
	private List<Roll> rolls;

}
