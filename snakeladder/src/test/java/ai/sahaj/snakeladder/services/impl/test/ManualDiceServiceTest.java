package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.services.impl.ManualDiceService;

@ExtendWith(SpringExtension.class)
class ManualDiceServiceTest {

	@InjectMocks
	private ManualDiceService manualDiceService;

	@Test
	void testGetDiceValue() {
		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			manualDiceService.getDiceValue();
		});
		assertEquals("Manual mode will be supported soon", thrown.getMessage());
	}

}
