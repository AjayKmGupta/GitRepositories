package ai.sahaj.snakeladder.dto.frontend;

import java.util.Set;

import ai.sahaj.snakeladder.constants.SimulationMode;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SimulationDto {

	private String id;
	private String name;
	private SimulationMode simulationMode;
	private int simulationCount;
	private BoardDto board;
	private Set<PlayerDto> players;
}
