package ai.sahaj.snakeladder.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.constants.RollType;
import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.dto.backend.TrackGameMovement;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.GameAccOrDeacclerator;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;
import ai.sahaj.snakeladder.services.LuckyUnluckyRollService;
import ai.sahaj.snakeladder.services.PositionCalculatorService;
import ai.sahaj.snakeladder.services.PositionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PositionCalculatorServiceImpl implements PositionCalculatorService {

	@Autowired
	private AccOrDeacceleratorService accOrDeaccService;
	@Autowired
	private LuckyUnluckyRollService luckyOrUnluckyRollService;
	@Autowired
	private PositionService posService;

	@Override
	public TrackMovement updateMovement(TrackGameMovement trackGameMovement, int count, Roll roll) {

		TrackMovement trackMovement = trackGameMovement.getTrackMovement().get(count);

		int currentPos = trackMovement.getPosition() == null ? 0 : trackMovement.getPosition().getNumber();
		int posToBeMoved = currentPos + (roll.getNoOfRolls() - 1) * Dice.MAX_VAL + roll.getFaceValue();
		Optional<Position> optPos = posService.getPositionByValue(posToBeMoved);
		luckyOrUnluckyRollService.setLuckyOrUnluckyPositionThresholdCount(posToBeMoved, trackMovement);
		if (optPos.isPresent()) {
			Optional<AccOrDeaccelerator> accOrDeacc = accOrDeaccService.getAccOrDeaccByStartPosition(optPos.get());
			if (accOrDeacc.isPresent()) {
				log.debug("Logging snake or ladder: {}", accOrDeacc.get());
				GameAccOrDeacclerator gameAccOrDeacc = new GameAccOrDeacclerator(UUID.randomUUID().toString(),
						trackGameMovement.getGame(), accOrDeacc.get());
				trackGameMovement.getGameAccsOrDeaccs().add(gameAccOrDeacc);
				int accOrDeaccVal = getAcceleratedOrDeaccVal(accOrDeacc.get());
				luckyOrUnluckyRollService.setLuckyOrUnluckyRoll(accOrDeacc.get(), roll);
				int effPosToBeMoved = posToBeMoved + accOrDeaccVal;
				Optional<Position> optEffPos = posService.getPositionByValue(effPosToBeMoved);
				luckyOrUnluckyRollService.setLuckyOrUnluckyPositionThresholdCount(effPosToBeMoved, trackMovement);
				if (optEffPos.isPresent()) {
					trackMovement.setPosition(optEffPos.get());
				}
			} else {
				List<AccOrDeaccelerator> vicinityAccOrDeaccs = accOrDeaccService
						.getVicinityAccOrDeaccByStartPosition(optPos.get(), AccOrDeacceleratorType.SNAKE);
				if (!CollectionUtils.isEmpty(vicinityAccOrDeaccs)) {
					roll.setRollType(RollType.LUCKY);
				}
				trackMovement.setPosition(optPos.get());
			}
		}
		log.debug("Updated positions: {} for player: {}", trackMovement.getPosition().getNumber(),
				trackMovement.getPlayer().getName());
		return trackMovement;
	}

	private int getAcceleratedOrDeaccVal(AccOrDeaccelerator accOrDeaccelerator) {
		return accOrDeaccelerator.getFinalPosition().getNumber() - accOrDeaccelerator.getStartPos().getNumber();
	}

}
