package ai.sahaj.snakeladder.services;

import java.util.List;

import ai.sahaj.snakeladder.constants.RollType;
import ai.sahaj.snakeladder.dto.backend.RollStats;
import ai.sahaj.snakeladder.entity.Roll;

public interface RollService {

	Roll roll(DiceService diceService);

	void saveAllRolls(List<Roll> rolls);

	List<Roll> getLongestTurnRoll(String simulationId);

	List<RollStats> getGameWiseRolls(String simulationId);

	List<Roll> getLuckyOrUnluckyRolls(String simulationId, RollType rollType);

}
