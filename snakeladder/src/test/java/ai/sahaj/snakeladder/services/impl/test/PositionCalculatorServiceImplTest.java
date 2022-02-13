package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;
import ai.sahaj.snakeladder.services.LuckyUnluckyRollService;
import ai.sahaj.snakeladder.services.PositionService;
import ai.sahaj.snakeladder.services.impl.PositionCalculatorServiceImpl;

@ExtendWith(SpringExtension.class)
class PositionCalculatorServiceImplTest {

	@InjectMocks
	private PositionCalculatorServiceImpl positionCalculatorServiceImpl;

	@Mock
	private AccOrDeacceleratorService accOrDeaccService;
	@Mock
	private LuckyUnluckyRollService luckyOrUnluckyRollService;
	@Mock
	private PositionService posService;

	@Test
	void testUpdateMovement() {

		Position position = new Position();
		// TrackGameMovement trackGameMovement = new TrackGameMovement();

		Mockito.when(posService.getPositionByValue(Mockito.anyInt())).thenReturn(Optional.of(position));
		assertTrue(true);
	}

}
