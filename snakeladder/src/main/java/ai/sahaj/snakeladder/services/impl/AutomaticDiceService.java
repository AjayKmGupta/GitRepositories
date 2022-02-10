package ai.sahaj.snakeladder.services.impl;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.services.DiceService;
import lombok.extern.slf4j.Slf4j;

@Service("autoDiceService")
@Slf4j
public class AutomaticDiceService implements DiceService {

	@Override
	public Dice getDiceValue() {
		log.trace("Rolling dice");
		Dice dice = new Dice(ThreadLocalRandom.current().nextInt(Dice.MIN_VAL, Dice.MAX_VAL + 1));
		log.trace("Value after rolling the dice: {}", dice);
		return dice;
	}

}
