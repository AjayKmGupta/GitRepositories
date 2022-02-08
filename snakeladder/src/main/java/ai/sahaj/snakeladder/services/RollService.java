package ai.sahaj.snakeladder.services;

import java.util.List;

import ai.sahaj.snakeladder.entity.Roll;

public interface RollService {

	Roll roll();

	void saveAllRolls(List<Roll> rolls);

}
