package ai.sahaj.snakeladder.services;

import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Roll;

public interface LuckyUnluckyRollService {

	void setLuckyOrUnluckyPositionThreshold(int posToBeMoved, TrackMovement trackMovement);

	void setLuckyOrUnluckyRoll(AccOrDeaccelerator accOrDeacc, Roll roll);

	void setLuckyOrUnluckyRoll(TrackMovement trackMovement, Roll roll);

}
