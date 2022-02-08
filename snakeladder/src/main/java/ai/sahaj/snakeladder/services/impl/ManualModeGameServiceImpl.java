package ai.sahaj.snakeladder.services.impl;

import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.dto.backend.SimulationDataDto;
import ai.sahaj.snakeladder.entity.Simulation;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.services.GameService;

@Service("manualModeGameService")
public class ManualModeGameServiceImpl implements GameService {

	@Override
	public SimulationDataDto play(Simulation simulation) {
		throw new BadRequestException("Manual mode will be supported soon");
	}

}
