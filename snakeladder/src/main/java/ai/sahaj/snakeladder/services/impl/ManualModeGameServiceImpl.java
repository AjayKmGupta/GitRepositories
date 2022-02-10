package ai.sahaj.snakeladder.services.impl;

import ai.sahaj.snakeladder.dto.backend.TrackGameMovement;
import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.entity.Simulation;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.services.DiceService;

public class ManualModeGameServiceImpl extends GameServiceImpl {

	@Override
	public TrackGameMovement play(Simulation simulation, Game game, DiceService diceServie) {
		throw new BadRequestException("Manual mode will be supported soon");
	}

}
