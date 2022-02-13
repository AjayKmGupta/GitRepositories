package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.dto.backend.TrackGameMovement;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.services.DiceService;
import ai.sahaj.snakeladder.services.PositionCalculatorService;
import ai.sahaj.snakeladder.services.RollService;
import ai.sahaj.snakeladder.services.impl.AutoModeGameServiceImpl;
import ai.sahaj.snakeladder.util.test.TestUtil;

@ExtendWith(SpringExtension.class)
class AutoModeGameServiceImplTest {

	@InjectMocks
	private AutoModeGameServiceImpl autoModeGameServiceImpl;

	@Mock
	private RollService rollService;
	@Mock
	private PositionCalculatorService posCalService;
	@Mock
	private DiceService diceService;

	@Ignore
	//@Test
	void testPlay() {

		Game game = new Game();
		game.setId("1234");
		TrackMovement trackMovement = TestUtil.getTrackMovement();
		Mockito.when(rollService.roll(diceService)).thenReturn(TestUtil.getRoll());

		Mockito.when(posCalService.updateMovement(Mockito.mock(TrackGameMovement.class), Mockito.anyInt(),
				rollService.roll(diceService))).thenReturn(TestUtil.getTrackMovement());

		TrackGameMovement trackGameMovement = autoModeGameServiceImpl.play(TestUtil.getSimulation(), game,
				diceService);
		assertNotNull(trackGameMovement);
		assertNotNull(trackGameMovement.getGame().getWinner());
		assertFalse(trackGameMovement.getRolls().isEmpty());

	}

}
