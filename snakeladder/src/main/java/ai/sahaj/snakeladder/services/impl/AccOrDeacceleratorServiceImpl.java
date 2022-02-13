package ai.sahaj.snakeladder.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.dto.backend.TakenAccOrDeAccDiff;
import ai.sahaj.snakeladder.dto.frontend.AccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.AddAccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.PositionDto;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.exceptions.ConflictException;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.repositories.AccOrDeacceleratorRepository;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;
import ai.sahaj.snakeladder.services.PositionService;

@Service
public class AccOrDeacceleratorServiceImpl implements AccOrDeacceleratorService {

	@Autowired
	private AccOrDeacceleratorRepository accOrDeaccRepo;
	@Autowired
	private PositionService posService;

	@Transactional
	@Override
	public void addAccOrDeaccelerator(List<AddAccOrDeacceleratorDto> accOrDeaccDtos) {
		List<AccOrDeaccelerator> accOrDeaccs = getAllAccOrDeaccelerators();
		accOrDeaccDtos.forEach(accOrDeaccDto -> {
			checkAccOrDeacceleratorAlreadyExists(accOrDeaccs, accOrDeaccDto);
			checkIfAccOrDeaccIsValid(accOrDeaccs, accOrDeaccDto);
			AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
			BeanUtils.copyProperties(accOrDeaccDto, accOrDeacc);
			accOrDeaccRepo.save(updateAccOrDeacceleratorEntity(accOrDeacc, accOrDeaccDto));
		});
	}

	@Override
	public void updateAccOrDeaccelerator(String accOrDeAccId, AddAccOrDeacceleratorDto accOrDeaccDto) {

		Optional<AccOrDeaccelerator> optAccOrDeacc = accOrDeaccRepo.findById(Integer.parseInt(accOrDeAccId));
		if (optAccOrDeacc.isEmpty()) {
			throw new ResourceNotFoundException("No ladder or snake exist for given id");
		}
		AccOrDeaccelerator accOrDeacc = optAccOrDeacc.get();
		checkAccOrDeacceleratorAlreadyExists(accOrDeacc, accOrDeaccDto);
		checkIfAccOrDeaccIsValid(accOrDeacc, accOrDeaccDto);
		BeanUtils.copyProperties(accOrDeaccDto, accOrDeacc);
		accOrDeaccRepo.save(updateAccOrDeacceleratorEntity(accOrDeacc, accOrDeaccDto));
	}

	private AccOrDeaccelerator updateAccOrDeacceleratorEntity(AccOrDeaccelerator accOrDeacc,
			AddAccOrDeacceleratorDto accOrDeaccDto) {
		Optional<Position> optStartPos = posService.getPositions().stream()
				.filter(p -> p.getNumber() == accOrDeaccDto.getStartPos().getNumber()).findFirst();
		Optional<Position> optFinalPos = posService.getPositions().stream()
				.filter(p -> p.getNumber() == accOrDeaccDto.getFinalPosition().getNumber()).findFirst();
		if (optStartPos.isEmpty() || optFinalPos.isEmpty()) {
			throw new ResourceNotFoundException("The given position doesn't exists");
		}
		accOrDeacc.setStartPos(optStartPos.get());
		accOrDeacc.setFinalPosition(optFinalPos.get());
		return accOrDeacc;
	}

	@Override
	public Set<AccOrDeacceleratorDto> getAccOrDeaccelerator(String boardId) {
		Board board = new Board();
		board.setId(Integer.parseInt(boardId));
		List<AccOrDeaccelerator> accOrDeaccs = accOrDeaccRepo.findByBoard(board);
		return accOrDeaccs.stream().map(accOrDeacc -> new AccOrDeacceleratorDto(accOrDeacc.getId(),
				accOrDeacc.getAccOrDeAccType(),
				new PositionDto(accOrDeacc.getStartPos().getId(), accOrDeacc.getStartPos().getNumber()),
				new PositionDto(accOrDeacc.getFinalPosition().getId(), accOrDeacc.getFinalPosition().getNumber())))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<AccOrDeacceleratorDto> getAccOrDeaccelerator() {
		return getAccOrDeaccDtosFromAccOrDeacc(accOrDeaccRepo.findAll());
	}

	@Override
	public Set<AccOrDeacceleratorDto> getAccOrDeaccDtosFromAccOrDeacc(List<AccOrDeaccelerator> accOrDeaccs) {
		return accOrDeaccs.stream().map(accOrDeacc -> new AccOrDeacceleratorDto(accOrDeacc.getId(),
				accOrDeacc.getAccOrDeAccType(),
				new PositionDto(accOrDeacc.getStartPos().getId(), accOrDeacc.getStartPos().getNumber()),
				new PositionDto(accOrDeacc.getFinalPosition().getId(), accOrDeacc.getFinalPosition().getNumber())))
				.collect(Collectors.toSet());
	}

	private void checkAccOrDeacceleratorAlreadyExists(List<AccOrDeaccelerator> accOrDeaccs,
			AddAccOrDeacceleratorDto accOrDeaccDto) {
		for (AccOrDeaccelerator accOrDeacc : accOrDeaccs) {
			checkAccOrDeacceleratorAlreadyExists(accOrDeacc, accOrDeaccDto);
		}
	}

	private void checkAccOrDeacceleratorAlreadyExists(AccOrDeaccelerator accOrDeacc,
			AddAccOrDeacceleratorDto accOrDeaccDto) {
		if (accOrDeacc.getAccOrDeAccType() == accOrDeaccDto.getAccOrDeAccType()
				&& accOrDeacc.getStartPos().getNumber() == accOrDeaccDto.getStartPos().getNumber()
				&& accOrDeacc.getFinalPosition().getNumber() == accOrDeaccDto.getFinalPosition().getNumber()) {
			throw new ConflictException(accOrDeaccDto.getAccOrDeAccType() + " already exists");
		}
	}

	private void checkIfAccOrDeaccIsValid(List<AccOrDeaccelerator> accOrDeaccs,
			AddAccOrDeacceleratorDto accOrDeaccDto) {
		for (AccOrDeaccelerator accOrDeacc : accOrDeaccs) {
			checkIfAccOrDeaccIsValid(accOrDeacc, accOrDeaccDto);
		}

	}

	private void checkIfAccOrDeaccIsValid(AccOrDeaccelerator accOrDeacc, AddAccOrDeacceleratorDto accOrDeaccDto) {

		if (Objects.isNull(accOrDeaccDto) || Objects.isNull(accOrDeaccDto.getStartPos())
				|| Objects.isNull(accOrDeaccDto.getFinalPosition())) {
			throw new BadRequestException("All accelerators and its positions are required");
		}
		if (accOrDeaccDto.getAccOrDeAccType() == AccOrDeacceleratorType.LADDER
				&& accOrDeaccDto.getStartPos().getNumber() > accOrDeaccDto.getFinalPosition().getNumber()) {
			throw new BadRequestException("Ladder start position should be less than the final position");
		}

		if (accOrDeaccDto.getAccOrDeAccType() == AccOrDeacceleratorType.SNAKE
				&& accOrDeaccDto.getStartPos().getNumber() < accOrDeaccDto.getFinalPosition().getNumber()) {
			throw new BadRequestException("Snake start position should be greater than the final position");
		}

		if (accOrDeacc.getStartPos().getNumber() == accOrDeaccDto.getStartPos().getNumber()) {
			throw new BadRequestException("Ladder and snake can't have same start point");
		}

		if (accOrDeaccDto.getAccOrDeAccType() == AccOrDeacceleratorType.LADDER
				&& accOrDeacc.getAccOrDeAccType() == AccOrDeacceleratorType.SNAKE
				&& accOrDeaccDto.getFinalPosition().getNumber() == accOrDeacc.getStartPos().getNumber()) {
			throw new BadRequestException("Ladder can't have final position at snake start point");
		}
	}

	@Override
	public List<AccOrDeaccelerator> getAllAccOrDeaccelerators() {
		return accOrDeaccRepo.findAll();
	}

	@Override
	public Optional<AccOrDeaccelerator> getAccOrDeaccByStartPosition(Position position) {
		return accOrDeaccRepo.findByStartPos(position);
	}

	@Override
	public List<AccOrDeaccelerator> getVicinityAccOrDeaccByStartPosition(Position position,
			AccOrDeacceleratorType accOrDeaccType) {
		return accOrDeaccRepo.findByVicinityStartPos(position.getNumber() - 2, position.getNumber() + 2,
				accOrDeaccType.ordinal());
	}

	@Override
	public List<TakenAccOrDeAccDiff> getSimulationWiseSnakeOrLadderTaken(String simulationId,
			AccOrDeacceleratorType accOrDeaccType) {
		return accOrDeaccRepo.getSimulationWiseSnakeOrLadderTaken(simulationId, accOrDeaccType.ordinal());
	}

}
