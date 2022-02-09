package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.dto.frontend.AddAccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.AddPositionDto;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
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

}
