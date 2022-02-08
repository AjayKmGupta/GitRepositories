package ai.sahaj.snakeladder.services;

import ai.sahaj.snakeladder.dto.backend.SimulationDataDto;
import ai.sahaj.snakeladder.entity.Simulation;

public interface GameService {

	SimulationDataDto play(Simulation simulation);

}
