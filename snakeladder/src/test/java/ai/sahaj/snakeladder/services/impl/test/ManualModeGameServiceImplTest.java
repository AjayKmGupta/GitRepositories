package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.services.DiceService;
import ai.sahaj.snakeladder.services.impl.ManualModeGameServiceImpl;
import ai.sahaj.snakeladder.util.test.TestUtil;

@ExtendWith(SpringExtension.class)
class ManualModeGameServiceImplTest {

	@InjectMocks
	private ManualModeGameServiceImpl manualModeGameServiceImpl;

	@Mock
	private DiceService diceService;

	@Test
	void testPlay() {
		Game game = new Game();
		game.setId("123");
		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			manualModeGameServiceImpl.play(TestUtil.getSimulation(), game, diceService);
		});
		assertEquals("Manual mode will be supported soon", thrown.getMessage());

	}
}
