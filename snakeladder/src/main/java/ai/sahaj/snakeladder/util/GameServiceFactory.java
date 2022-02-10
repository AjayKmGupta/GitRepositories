package ai.sahaj.snakeladder.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ai.sahaj.snakeladder.constants.SimulationMode;
import ai.sahaj.snakeladder.services.GameService;

@Component
public class GameServiceFactory {

	private GameService autoModeGameService;

	private GameService manualModeGameService;

	@Qualifier("autoModeGameService")
	@Autowired
	public void setAutoModeGameService(GameService autoModeGameService) {
		this.autoModeGameService = autoModeGameService;
	}

	@Qualifier("manualModeGameService")
	@Autowired
	public void setManualModeGameService(GameService manualModeGameService) {
		this.manualModeGameService = manualModeGameService;
	}

	public GameService getGameService(SimulationMode simulationMode) {
		GameService gameService = null;
		switch (simulationMode) {
		case AUTO:
			gameService = autoModeGameService;
			break;
		case MANUAL:
			gameService = manualModeGameService;
			break;
		default:
			gameService = autoModeGameService;
		}
		return gameService;
	}
}
