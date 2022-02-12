package ai.sahaj.snakeladder.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.constants.RollType;
import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.dto.backend.RollStats;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.repositories.RollRepository;
import ai.sahaj.snakeladder.services.DiceService;
import ai.sahaj.snakeladder.services.RollService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RollServiceImpl implements RollService {

	@Autowired
	private RollRepository rollRepo;

	@Override
	public Roll roll(DiceService diceServie) {
		Dice dice = null;
		Roll roll = new Roll(UUID.randomUUID().toString());
		while (dice == null || dice.getFaceValue() == Dice.MAX_VAL) {
			dice = diceServie.getDiceValue();
			roll.setNoOfRolls(roll.getNoOfRolls() + 1);
			roll.setFaceValue(dice.getFaceValue());
		}
		log.info("Roll value for this turn: {}", roll);
		return roll;
	}

	@Override
	public void saveAllRolls(List<Roll> rolls) {
		rollRepo.saveAll(rolls);
	}

	@Override
	public List<Roll> getLongestTurnRoll(String simulationId) {
		return rollRepo.getLongestTurnRoll(simulationId);
	}

	@Override
	public List<RollStats> getGameWiseRolls(String simulationId) {
		return rollRepo.getGameWiseRolls(simulationId);
	}

	@Override
	public List<Roll> getLuckyOrUnluckyRolls(String simulationId, RollType rollType) {
		return rollRepo.getLuckyOrUnluckyRolls(simulationId, rollType.ordinal());
	}

}
