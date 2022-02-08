package ai.sahaj.snakeladder.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ai.sahaj.snakeladder.constants.SimulationMode;
import ai.sahaj.snakeladder.services.GameService;

@Component
public class GameServiceFactory {

	@Qualifier("autoModeGameService")
	@Autowired
	private GameService autoModeGameService;
	@Qualifier("manualModeGameService")
	@Autowired
	private GameService manualModeGameService;

	public GameService getGameService(SimulationMode simulationMode) {
		GameService gameService = null;
		switch (simulationMode) {
			case AUTO:
				gameService = autoModeGameService;
				break;
			case MANUAL:
				gameService = manualModeGameService;
		}
		return gameService;
	}
}
