package ai.sahaj.snakeladder.services.impl;

import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.services.DiceService;
import lombok.extern.slf4j.Slf4j;

@Service("manualDiceService")
@Slf4j
public class ManualDiceService implements DiceService {

	@Override
	public Dice getDiceValue() {
		throw new BadRequestException("Manual mode will be supported soon");
	}

}
