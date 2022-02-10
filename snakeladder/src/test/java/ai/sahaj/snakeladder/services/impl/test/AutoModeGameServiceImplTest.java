package ai.sahaj.snakeladder.services.impl.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.services.PositionCalculatorService;
import ai.sahaj.snakeladder.services.RollService;
import ai.sahaj.snakeladder.services.impl.AutoModeGameServiceImpl;

@ExtendWith(SpringExtension.class)
class AutoModeGameServiceImplTest {

	@InjectMocks
	private AutoModeGameServiceImpl autoModeGameServiceImpl;

	@Mock
	private RollService rollService;
	@Mock
	private PositionCalculatorService posCalService;

	@Test
	void testPlay() {

		/*
		 * Mockito.when(rollService.roll()).thenReturn(TestUtil.getRoll());
		 * 
		 * Mockito.when(posCalService.updateMovement(Mockito.any(), Mockito.any()))
		 * .thenReturn(TestUtil.getTrackMovement());
		 * 
		 * SimulationDataDto simDataDto =
		 * autoModeGameServiceImpl.play(TestUtil.getSimulation());
		 * assertNotNull(simDataDto); assertNotNull(simDataDto.getWinner());
		 * assertFalse(simDataDto.getRolls().isEmpty());
		 */
	}

}
