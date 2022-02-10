package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.dto.frontend.AccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.AddAccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.AddPositionDto;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.exceptions.ConflictException;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.repositories.AccOrDeacceleratorRepository;
import ai.sahaj.snakeladder.services.PositionService;
import ai.sahaj.snakeladder.services.impl.AccOrDeacceleratorServiceImpl;
import ai.sahaj.snakeladder.util.test.TestUtil;

@ExtendWith(SpringExtension.class)
class AccOrDeacceleratorServiceImplTest {

	@InjectMocks
	private AccOrDeacceleratorServiceImpl accOrDeaccService;

	@Mock
	private AccOrDeacceleratorRepository accOrDeaccRepo;
	@Mock
	private PositionService posService;

	@Test
	void testAddAccOrDeaccelerator() {
		List<AddAccOrDeacceleratorDto> accOrDeaccDtos = new ArrayList<>();
		AddAccOrDeacceleratorDto addAccOrDeaccDto1 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto1.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		AddPositionDto startPosDto1 = new AddPositionDto();
		startPosDto1.setNumber(34);
		addAccOrDeaccDto1.setStartPos(startPosDto1);
		AddPositionDto finalPosDto1 = new AddPositionDto();
		finalPosDto1.setNumber(13);
		addAccOrDeaccDto1.setFinalPosition(finalPosDto1);
		AddAccOrDeacceleratorDto addAccOrDeaccDto2 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto2.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		AddPositionDto startPosDto2 = new AddPositionDto();
		startPosDto2.setNumber(4);
		addAccOrDeaccDto2.setStartPos(startPosDto2);
		AddPositionDto finalPosDto2 = new AddPositionDto();
		finalPosDto2.setNumber(25);
		addAccOrDeaccDto2.setFinalPosition(finalPosDto2);

		accOrDeaccDtos.add(addAccOrDeaccDto1);
		accOrDeaccDtos.add(addAccOrDeaccDto2);

		Mockito.when(accOrDeaccRepo.findAll()).thenReturn(new ArrayList<AccOrDeaccelerator>());
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());
		accOrDeaccService.addAccOrDeaccelerator(accOrDeaccDtos);
		Mockito.verify(accOrDeaccRepo).findAll();
	}

	@Test
	void testAddAccOrDeacceleratorWithOutPositions() {
		List<AddAccOrDeacceleratorDto> accOrDeaccDtos = new ArrayList<>();
		AddAccOrDeacceleratorDto addAccOrDeaccDto1 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto1.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		AddPositionDto startPosDto1 = new AddPositionDto();
		startPosDto1.setNumber(34);
		addAccOrDeaccDto1.setStartPos(startPosDto1);
		AddPositionDto finalPosDto1 = new AddPositionDto();
		finalPosDto1.setNumber(13);
		addAccOrDeaccDto1.setFinalPosition(finalPosDto1);
		AddAccOrDeacceleratorDto addAccOrDeaccDto2 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto2.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		AddPositionDto startPosDto2 = new AddPositionDto();
		startPosDto2.setNumber(4);
		addAccOrDeaccDto2.setStartPos(startPosDto2);
		AddPositionDto finalPosDto2 = new AddPositionDto();
		finalPosDto2.setNumber(25);
		addAccOrDeaccDto2.setFinalPosition(finalPosDto2);

		accOrDeaccDtos.add(addAccOrDeaccDto1);
		accOrDeaccDtos.add(addAccOrDeaccDto2);

		Mockito.when(accOrDeaccRepo.findAll()).thenReturn(new ArrayList<AccOrDeaccelerator>());
		Mockito.when(posService.getPositions()).thenReturn(new ArrayList<Position>());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			accOrDeaccService.addAccOrDeaccelerator(accOrDeaccDtos);
		});
		assertEquals("The given position doesn't exists", thrown.getMessage());
	}

	@Test
	void testAddAccOrDeacceleratorAlreadyExists() {
		List<AddAccOrDeacceleratorDto> accOrDeaccDtos = new ArrayList<>();
		List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();

		AddAccOrDeacceleratorDto addAccOrDeaccDto1 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto1.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		AddPositionDto startPosDto1 = new AddPositionDto();
		startPosDto1.setNumber(34);
		addAccOrDeaccDto1.setStartPos(startPosDto1);
		AddPositionDto finalPosDto1 = new AddPositionDto();
		finalPosDto1.setNumber(13);
		addAccOrDeaccDto1.setFinalPosition(finalPosDto1);

		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		Position startPos = new Position();
		startPos.setNumber(34);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(13);
		accOrDeacc.setFinalPosition(finalPos);

		accOrDeaccDtos.add(addAccOrDeaccDto1);
		accOrDeaccs.add(accOrDeacc);

		Mockito.when(accOrDeaccRepo.findAll()).thenReturn(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());

		ConflictException thrown = assertThrows(ConflictException.class, () -> {
			accOrDeaccService.addAccOrDeaccelerator(accOrDeaccDtos);
		});
		assertEquals("SNAKE already exists", thrown.getMessage());
	}

	@Test
	void testAddAccOrDeacceleratorLadderSnakeSameStartPoint() {
		List<AddAccOrDeacceleratorDto> accOrDeaccDtos = new ArrayList<>();
		List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();

		AddAccOrDeacceleratorDto addAccOrDeaccDto1 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto1.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		AddPositionDto startPosDto1 = new AddPositionDto();
		startPosDto1.setNumber(34);
		addAccOrDeaccDto1.setStartPos(startPosDto1);
		AddPositionDto finalPosDto1 = new AddPositionDto();
		finalPosDto1.setNumber(55);
		addAccOrDeaccDto1.setFinalPosition(finalPosDto1);

		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		Position startPos = new Position();
		startPos.setNumber(34);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(13);
		accOrDeacc.setFinalPosition(finalPos);

		accOrDeaccDtos.add(addAccOrDeaccDto1);
		accOrDeaccs.add(accOrDeacc);

		Mockito.when(accOrDeaccRepo.findAll()).thenReturn(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());

		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			accOrDeaccService.addAccOrDeaccelerator(accOrDeaccDtos);
		});
		assertEquals("Ladder and snake can't have same start point", thrown.getMessage());
	}

	@Test
	void testAddAccOrDeacceleratorLadderFinalAtSnakeStartPoint() {
		List<AddAccOrDeacceleratorDto> accOrDeaccDtos = new ArrayList<>();
		List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();

		AddAccOrDeacceleratorDto addAccOrDeaccDto1 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto1.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		AddPositionDto startPosDto1 = new AddPositionDto();
		startPosDto1.setNumber(34);
		addAccOrDeaccDto1.setStartPos(startPosDto1);
		AddPositionDto finalPosDto1 = new AddPositionDto();
		finalPosDto1.setNumber(55);
		addAccOrDeaccDto1.setFinalPosition(finalPosDto1);

		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		Position startPos = new Position();
		startPos.setNumber(55);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(13);
		accOrDeacc.setFinalPosition(finalPos);

		accOrDeaccDtos.add(addAccOrDeaccDto1);
		accOrDeaccs.add(accOrDeacc);

		Mockito.when(accOrDeaccRepo.findAll()).thenReturn(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());

		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			accOrDeaccService.addAccOrDeaccelerator(accOrDeaccDtos);
		});
		assertEquals("Ladder can't have final position at snake start point", thrown.getMessage());
	}

	@Test
	void testAddAccOrDeacceleratorInvertedLadder() {
		List<AddAccOrDeacceleratorDto> accOrDeaccDtos = new ArrayList<>();
		List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();

		AddAccOrDeacceleratorDto addAccOrDeaccDto1 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto1.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		AddPositionDto startPosDto1 = new AddPositionDto();
		startPosDto1.setNumber(55);
		addAccOrDeaccDto1.setStartPos(startPosDto1);
		AddPositionDto finalPosDto1 = new AddPositionDto();
		finalPosDto1.setNumber(34);
		addAccOrDeaccDto1.setFinalPosition(finalPosDto1);

		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		Position startPos = new Position();
		startPos.setNumber(58);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(13);
		accOrDeacc.setFinalPosition(finalPos);

		accOrDeaccDtos.add(addAccOrDeaccDto1);
		accOrDeaccs.add(accOrDeacc);

		Mockito.when(accOrDeaccRepo.findAll()).thenReturn(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());

		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			accOrDeaccService.addAccOrDeaccelerator(accOrDeaccDtos);
		});
		assertEquals("Ladder start position should be less than the final position", thrown.getMessage());
	}

	@Test
	void testAddAccOrDeacceleratorInvertedSnake() {
		List<AddAccOrDeacceleratorDto> accOrDeaccDtos = new ArrayList<>();
		List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();

		AddAccOrDeacceleratorDto addAccOrDeaccDto1 = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto1.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		AddPositionDto startPosDto1 = new AddPositionDto();
		startPosDto1.setNumber(34);
		addAccOrDeaccDto1.setStartPos(startPosDto1);
		AddPositionDto finalPosDto1 = new AddPositionDto();
		finalPosDto1.setNumber(55);
		addAccOrDeaccDto1.setFinalPosition(finalPosDto1);

		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		Position startPos = new Position();
		startPos.setNumber(58);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(13);
		accOrDeacc.setFinalPosition(finalPos);

		accOrDeaccDtos.add(addAccOrDeaccDto1);
		accOrDeaccs.add(accOrDeacc);

		Mockito.when(accOrDeaccRepo.findAll()).thenReturn(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());

		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			accOrDeaccService.addAccOrDeaccelerator(accOrDeaccDtos);
		});
		assertEquals("Snake start position should be greater than the final position", thrown.getMessage());
	}

	@Test
	void testUpdateAccOrDeaccelerator() {
		AddAccOrDeacceleratorDto addAccOrDeaccDto = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		AddPositionDto startPosDto = new AddPositionDto();
		startPosDto.setNumber(34);
		addAccOrDeaccDto.setStartPos(startPosDto);
		AddPositionDto finalPosDto = new AddPositionDto();
		finalPosDto.setNumber(13);
		addAccOrDeaccDto.setFinalPosition(finalPosDto);
		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setId(123);
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		Position startPos = new Position();
		startPos.setNumber(4);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(25);
		accOrDeacc.setFinalPosition(finalPos);

		Mockito.when(accOrDeaccRepo.findById(123)).thenReturn(Optional.of(accOrDeacc));
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());
		accOrDeaccService.updateAccOrDeaccelerator("123", addAccOrDeaccDto);
		Mockito.verify(accOrDeaccRepo).findById(123);
	}

	@Test
	void testUpdateAccOrDeacceleratorResourceNotFound() {
		AddAccOrDeacceleratorDto addAccOrDeaccDto = new AddAccOrDeacceleratorDto();
		addAccOrDeaccDto.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		AddPositionDto startPosDto = new AddPositionDto();
		startPosDto.setNumber(34);
		addAccOrDeaccDto.setStartPos(startPosDto);
		AddPositionDto finalPosDto = new AddPositionDto();
		finalPosDto.setNumber(13);
		addAccOrDeaccDto.setFinalPosition(finalPosDto);

		Mockito.when(accOrDeaccRepo.findById(123)).thenReturn(Optional.empty());
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			accOrDeaccService.updateAccOrDeaccelerator("123", addAccOrDeaccDto);
		});
		assertEquals("No ladder or snake exist for given id", thrown.getMessage());
	}

	@Test
	void testGetAccOrDeacceleratorByBoardId() {

		List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();
		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setId(123);
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		Position startPos = new Position();
		startPos.setNumber(4);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(25);
		accOrDeacc.setFinalPosition(finalPos);
		accOrDeaccs.add(accOrDeacc);
		Board board = new Board();
		board.setId(123);
		Mockito.when(accOrDeaccRepo.findByBoard(board)).thenReturn(accOrDeaccs);
		Set<AccOrDeacceleratorDto> accOrDeaccDtos = accOrDeaccService.getAccOrDeaccelerator("123");
		assertFalse(accOrDeaccDtos.isEmpty());
	}

	@Test
	void testGetAllAccOrDeacceleratorByBoardId() {

		List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();
		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setId(123);
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		Position startPos = new Position();
		startPos.setNumber(4);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(25);
		accOrDeacc.setFinalPosition(finalPos);
		AccOrDeaccelerator accOrDeacc1 = new AccOrDeaccelerator();
		accOrDeacc1.setId(456);
		accOrDeacc1.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		Position startPos1 = new Position();
		startPos1.setNumber(6);
		accOrDeacc1.setStartPos(startPos);
		Position finalPos1 = new Position();
		finalPos1.setNumber(45);
		accOrDeacc1.setFinalPosition(finalPos);
		accOrDeaccs.add(accOrDeacc);
		accOrDeaccs.add(accOrDeacc1);

		Mockito.when(accOrDeaccRepo.findAll()).thenReturn(accOrDeaccs);
		Set<AccOrDeacceleratorDto> accOrDeaccDtos = accOrDeaccService.getAccOrDeaccelerator();
		assertFalse(accOrDeaccDtos.isEmpty());
	}

	@Test
	void testGetAccOrDeacceleratorByStartPosition() {

		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setId(123);
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		Position startPos = new Position();
		startPos.setId(4);
		startPos.setNumber(4);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(25);
		accOrDeacc.setFinalPosition(finalPos);

		Position startPos1 = new Position();
		startPos1.setId(6);
		startPos1.setNumber(6);

		Mockito.when(accOrDeaccRepo.findByStartPos(startPos1)).thenReturn(Optional.of(accOrDeacc));
		Optional<AccOrDeaccelerator> optAccOrDeacc = accOrDeaccService.getAccOrDeaccByStartPosition(startPos1);
		assertTrue(optAccOrDeacc.isPresent());
	}

}
