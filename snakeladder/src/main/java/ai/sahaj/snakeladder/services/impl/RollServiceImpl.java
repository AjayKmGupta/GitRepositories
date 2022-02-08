package ai.sahaj.snakeladder.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.repositories.RollRepository;
import ai.sahaj.snakeladder.services.RollService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RollServiceImpl implements RollService {

	@Autowired
	private RollRepository rollRepo;

	@Override
	public Roll roll() {
		Dice dice = null;
		Roll roll = new Roll(UUID.randomUUID().toString());
		while (dice == null || dice.getFaceValue() == Dice.MAX_VAL) {
			dice = getDiceValue();
			roll.setNoOfRolls(roll.getNoOfRolls() + 1);
			roll.setFaceValue(dice.getFaceValue());
		}
		log.info("Roll value for this turn: {}", roll);
		return roll;
	}

	private Dice getDiceValue() {
		log.trace("Rolling dice");
		Dice dice = new Dice(ThreadLocalRandom.current().nextInt(Dice.MIN_VAL, Dice.MAX_VAL + 1));
		log.trace("Value after rolling the dice: {}", dice);
		return dice;
	}

	@Override
	public void saveAllRolls(List<Roll> rolls) {
		rollRepo.saveAll(rolls);
	}

}
