package ai.sahaj.snakeladder.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;
import ai.sahaj.snakeladder.services.PositionCalculatorService;
import ai.sahaj.snakeladder.services.PositionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PositionCalculatorServiceImpl implements PositionCalculatorService {

	@Autowired
	private AccOrDeacceleratorService accOrDeaccService;

	@Autowired
	private PositionService posService;

	@Override
	public void updateMovement(TrackMovement trackMovement, Roll roll, Board board) {

		int currentPos = trackMovement.getPosition() == null ? 0 : trackMovement.getPosition().getNumber();
		int posToBeMoved = currentPos + (roll.getNoOfRolls() - 1) * Dice.MAX_VAL + roll.getFaceValue();
		Optional<Position> optPos = posService.getPositionByValue(posToBeMoved);
		if (optPos.isPresent()) {
			Optional<AccOrDeaccelerator> accOrDeaccStartPos = accOrDeaccService
					.getAccOrDeaccByStartPosition(optPos.get());
			if (accOrDeaccStartPos.isPresent()) {
				int accOrDeaccVal = getAcceleratedOrDeaccVal(accOrDeaccStartPos.get());
				int effPosToBeMoved = posToBeMoved + accOrDeaccVal;
				Optional<Position> optEffPos = posService.getPositionByValue(effPosToBeMoved);
				if (optEffPos.isPresent()) {
					trackMovement.setPosition(optEffPos.get());
				}
			} else {
				trackMovement.setPosition(optPos.get());
			}
		}
		log.info("Updated positions: {} for player: {}", trackMovement.getPosition().getNumber(), trackMovement.getPlayer().getName());
	}

	private int getAcceleratedOrDeaccVal(AccOrDeaccelerator accOrDeaccelerator) {
		return accOrDeaccelerator.getFinalPosition().getNumber() - accOrDeaccelerator.getStartPos().getNumber();
	}

}
