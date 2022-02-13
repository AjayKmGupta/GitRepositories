package ai.sahaj.snakeladder.services.impl.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.services.impl.AutomaticDiceService;

@ExtendWith(SpringExtension.class)
class AutomaticDiceServiceTest {

	@InjectMocks
	private AutomaticDiceService automaticDiceService;
	
	@Test
	void testGetDiceValue() {
		Dice dice = automaticDiceService.getDiceValue();
		System.out.println(dice.getFaceValue());
		assertThat(dice.getFaceValue()).isBetween(1, 6);
	}
}
