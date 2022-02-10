package ai.sahaj.snakeladder.services;

import java.util.List;

import ai.sahaj.snakeladder.entity.Roll;

public interface RollService {

	Roll roll(DiceService diceService);

	void saveAllRolls(List<Roll> rolls);

}
