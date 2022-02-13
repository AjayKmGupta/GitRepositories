package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.constants.RollType;
import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.dto.backend.RollStats;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.repositories.RollRepository;
import ai.sahaj.snakeladder.services.DiceService;
import ai.sahaj.snakeladder.services.impl.RollServiceImpl;

@ExtendWith(SpringExtension.class)
class RollServiceImplTest {

	@InjectMocks
	private RollServiceImpl rollServiceImpl;
	@Mock
	private RollRepository rollRepo;
	@Mock
	private DiceService diceService;

	@Test
	void testRoll() {
		Dice dice = new Dice(5);
		Mockito.when(diceService.getDiceValue()).thenReturn(dice);
		Roll roll = rollServiceImpl.roll(diceService);
		assertEquals(5, roll.getFaceValue());
		assertEquals(1, roll.getNoOfRolls());
	}

	@Test
	void testSaveAllRolls() {
		List<Roll> rolls = new ArrayList<>();
		Mockito.when(rollRepo.saveAll(rolls)).thenReturn(rolls);
		rollServiceImpl.saveAllRolls(rolls);
		Mockito.verify(rollRepo).saveAll(rolls);
	}

	@Test
	void testGetLongestTurnRoll() {
		List<Roll> expectedRolls = new ArrayList<>();
		Roll roll = new Roll("123");
		roll.setNoOfRolls(3);
		roll.setFaceValue(4);
		expectedRolls.add(roll);
		Mockito.when(rollRepo.getLongestTurnRoll("123")).thenReturn(expectedRolls);
		List<Roll> actualRolls = rollServiceImpl.getLongestTurnRoll("123");
		assertEquals(expectedRolls, actualRolls);
	}

	void testGameWiseRoll() {
		RollStats rollStat = new RollStats() {
			@Override
			public String getSimulationId() {
				return "123";
			}

			@Override
			public Integer getRollCount() {
				return 2;
			}

			@Override
			public String getGameId() {
				return "1234";
			}
		};
		List<RollStats> rollStats = new ArrayList<>();
		rollStats.add(rollStat);
		Mockito.when(rollServiceImpl.getGameWiseRolls("123")).thenReturn(rollStats);
		List<RollStats> actRollStats = rollServiceImpl.getGameWiseRolls("123");
		assertEquals(rollStats, actRollStats);
	}

	@Test
	void testGetLuckyOrUnluckyRolls() {
		List<Roll> rolls = new ArrayList<>();
		Roll roll = new Roll("123");
		roll.setRollType(RollType.LUCKY);
		rolls.add(roll);
		Mockito.when(rollServiceImpl.getLuckyOrUnluckyRolls("123", RollType.LUCKY)).thenReturn(rolls);
		List<Roll> actRolls = rollServiceImpl.getLuckyOrUnluckyRolls("123", RollType.LUCKY);
		assertEquals(rolls, actRolls);

	}

}
