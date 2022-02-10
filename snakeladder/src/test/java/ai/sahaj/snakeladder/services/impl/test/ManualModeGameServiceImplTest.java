package ai.sahaj.snakeladder.services.impl.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.services.impl.ManualModeGameServiceImpl;

@ExtendWith(SpringExtension.class)
class ManualModeGameServiceImplTest {

	@InjectMocks
	private ManualModeGameServiceImpl manualModeGameServiceImpl;

	@Test
	void testPlay() {

		/*
		 * BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
		 * manualModeGameServiceImpl.play(TestUtil.getSimulation()); });
		 * assertEquals("Manual mode will be supported soon", thrown.getMessage());
		 */
	}
}
