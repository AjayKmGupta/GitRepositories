package ai.sahaj.snakeladder.services.impl;

import static ai.sahaj.snakeladder.constants.SnakeLadderConstants.BOARD_FINAL_POS;
import static ai.sahaj.snakeladder.constants.SnakeLadderConstants.BOARD_START_POS;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ai.sahaj.snakeladder.dto.frontend.PositionDto;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.exceptions.ConflictException;
import ai.sahaj.snakeladder.repositories.PositionRepository;
import ai.sahaj.snakeladder.services.PositionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionRepository positionRepo;

	@Override
	public void createPositions() {
		if (!CollectionUtils.isEmpty(positionRepo.findAll())) {
			throw new ConflictException("Position on the board is already created");
		}
		Set<Position> positions = IntStream.rangeClosed(BOARD_START_POS, BOARD_FINAL_POS).mapToObj(Position::new)
				.collect(Collectors.toSet());
		positionRepo.saveAll(positions);

	}

	@Override
	public void removeAllPositions() {
		positionRepo.deleteAll();
	}

	@Override
	public List<PositionDto> getPositionsDto() {
		return positionRepo.findAll().stream().map(p -> new PositionDto(p.getId(), p.getNumber()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Position> getPositions() {
		return positionRepo.findAll();
	}

	@Override
	public Optional<Position> getPositionByValue(int boardStartPos) {
		log.debug("Getting position for number: {}", boardStartPos);
		return positionRepo.findByNumber(boardStartPos);
	}
}
