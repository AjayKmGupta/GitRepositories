package ai.sahaj.snakeladder.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ai.sahaj.snakeladder.constants.SimulationMode;
import ai.sahaj.snakeladder.services.DiceService;

@Component
public class DiceServiceFactory {

	// autoDiceService

	@Qualifier("autoDiceService")
	@Autowired
	private DiceService autoDiceService;
	
	@Qualifier("manualDiceService")
	@Autowired
	private DiceService manualDiceService;

	public DiceService getDiceService(SimulationMode simulationMode) {
		DiceService diceService = null;
		switch (simulationMode) {
		case AUTO:
			diceService = autoDiceService;
			break;
		case MANUAL:
			diceService = manualDiceService;
			break;
		default:
			diceService = autoDiceService;
		}
		return diceService;
	}
}
