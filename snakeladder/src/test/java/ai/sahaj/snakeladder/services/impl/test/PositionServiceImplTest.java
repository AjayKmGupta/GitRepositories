package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.dto.frontend.PositionDto;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.exceptions.ConflictException;
import ai.sahaj.snakeladder.repositories.PositionRepository;
import ai.sahaj.snakeladder.services.impl.PositionServiceImpl;
import ai.sahaj.snakeladder.util.test.TestUtil;

@ExtendWith(SpringExtension.class)
class PositionServiceImplTest {

	@InjectMocks
	private PositionServiceImpl positionServiceImpl;
	@Mock
	private PositionRepository positionRepo;

	@Test
	void testCreatePositions() {
		Mockito.when(positionRepo.findAll()).thenReturn(new ArrayList<>());
		positionServiceImpl.createPositions();
		Mockito.verify(positionRepo).findAll();
	}

	@Test
	void testCreatePositionsWithExistingPositions() {
		Mockito.when(positionRepo.findAll()).thenReturn(TestUtil.getAllPositions());
		ConflictException thrown = assertThrows(ConflictException.class, () -> {
			positionServiceImpl.createPositions();
		});
		assertEquals("Position on the board is already created", thrown.getMessage());
	}

	@Test
	void testRemoveAllPositions() {
		Mockito.doNothing().when(positionRepo).deleteAll();
		positionServiceImpl.removeAllPositions();
		Mockito.verify(positionRepo).deleteAll();
	}

	@Test
	void testGetPositionDtos() {
		Mockito.when(positionRepo.findAll()).thenReturn(TestUtil.getAllPositions());
		List<PositionDto> positionsDto = positionServiceImpl.getPositionsDto();
		assertEquals(100, positionsDto.size());
	}

	@Test
	void testGetPositions() {
		Mockito.when(positionRepo.findAll()).thenReturn(TestUtil.getAllPositions());
		List<Position> positions = positionServiceImpl.getPositions();
		assertEquals(100, positions.size());
	}

	@Test
	void testGetPositionByValue() {
		Position position = new Position(1);
		Mockito.when(positionRepo.findByNumber(Mockito.anyInt())).thenReturn(Optional.of(position));
		Optional<Position> optPosition = positionServiceImpl.getPositionByValue(1);
		assertEquals(1, optPosition.get().getNumber());
	}
}
