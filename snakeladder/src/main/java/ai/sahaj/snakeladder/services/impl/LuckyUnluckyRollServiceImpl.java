package ai.sahaj.snakeladder.services.impl;

import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.constants.RollType;
import ai.sahaj.snakeladder.constants.SnakeLadderConstants;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.services.LuckyUnluckyRollService;

@Service
public class LuckyUnluckyRollServiceImpl implements LuckyUnluckyRollService {

	@Override
	public void setLuckyOrUnluckyPositionThresholdCount(int posToBeMoved, TrackMovement trackMovement) {
		if (posToBeMoved >= SnakeLadderConstants.BOARD_LUCKY_THRESHOLD
				&& posToBeMoved <= SnakeLadderConstants.BOARD_FINAL_POS) {
			trackMovement.setWinLuckyThrsldCount(trackMovement.getWinLuckyThrsldCount() + 1);
		}
	}

	@Override
	public void setLuckyOrUnluckyRoll(AccOrDeaccelerator accOrDeacc, Roll roll) {

		if (accOrDeacc.getAccOrDeAccType() == AccOrDeacceleratorType.LADDER) {
			roll.setRollType(RollType.LUCKY);
		} else {
			roll.setRollType(RollType.UNLUCKY);
		}

	}

	@Override
	public void setLuckyOrUnluckyRoll(TrackMovement trackMovement, Roll roll) {
		if (trackMovement.getWinLuckyThrsldCount() == 2) {
			roll.setRollType(RollType.LUCKY);
		}
	}

}
