package ai.sahaj.snakeladder.dto.frontend;

import java.util.List;

import ai.sahaj.snakeladder.constants.SimulationMode;
import lombok.Data;

@Data
public class AddSimulationDto {

	private String name;
	private SimulationMode simulationMode;
	private int simulationCount;
	private SimulationAPIBoardDto board;
	private List<PlayerDto> players;
}
