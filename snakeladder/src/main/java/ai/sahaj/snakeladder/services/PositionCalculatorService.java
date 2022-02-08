package ai.sahaj.snakeladder.services;

import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Roll;

public interface PositionCalculatorService {

	void updateMovement(TrackMovement trackMovement, Roll roll, Board board);

}
