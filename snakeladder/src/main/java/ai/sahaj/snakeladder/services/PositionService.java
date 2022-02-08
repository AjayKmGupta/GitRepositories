package ai.sahaj.snakeladder.services;

import java.util.List;
import java.util.Optional;

import ai.sahaj.snakeladder.dto.frontend.PositionDto;
import ai.sahaj.snakeladder.entity.Position;

public interface PositionService {

	void createPositions();

	void removeAllPositions();

	List<PositionDto> getPositionsDto();

	List<Position> getPositions();

	Optional<Position> getPositionByValue(int boardStartPos);

}
