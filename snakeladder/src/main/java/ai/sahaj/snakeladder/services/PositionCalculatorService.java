package ai.sahaj.snakeladder.services;

import ai.sahaj.snakeladder.dto.backend.TrackGameMovement;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.Roll;

public interface PositionCalculatorService {

	TrackMovement updateMovement(TrackGameMovement trackGameMovement, int count, Roll roll);

}
