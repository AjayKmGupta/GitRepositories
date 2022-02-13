package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.constants.RollType;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.services.impl.LuckyUnluckyRollServiceImpl;

@ExtendWith(SpringExtension.class)
class LuckyUnluckyRollServiceImplTest {

	@InjectMocks
	private LuckyUnluckyRollServiceImpl luckyunluckyRollServiceImpl;

	@Test
	void testSetLuckyOrUnluckyPositionThresholdCount() {
		TrackMovement trackMovement = new TrackMovement();
		trackMovement.setWinLuckyThrsldCount(0);
		luckyunluckyRollServiceImpl.setLuckyOrUnluckyPositionThresholdCount(94, trackMovement);
		assertEquals(1, trackMovement.getWinLuckyThrsldCount());
	}

	@Test
	void testSetLuckyOrUnluckyPositionThresholdCountWithInvalid() {
		TrackMovement trackMovement = new TrackMovement();
		trackMovement.setWinLuckyThrsldCount(0);
		luckyunluckyRollServiceImpl.setLuckyOrUnluckyPositionThresholdCount(59, trackMovement);
		assertEquals(0, trackMovement.getWinLuckyThrsldCount());
	}

	@Test
	void testSetLuckyOrUnluckyRollWithLadder() {
		AccOrDeaccelerator accOrAcc = new AccOrDeaccelerator();
		accOrAcc.setAccOrDeAccType(AccOrDeacceleratorType.LADDER);
		Roll roll = new Roll("123");
		luckyunluckyRollServiceImpl.setLuckyOrUnluckyRoll(accOrAcc, roll);
		assertEquals(RollType.LUCKY, roll.getRollType());
	}

	@Test
	void testSetLuckyOrUnluckyRollWithSnake() {
		AccOrDeaccelerator accOrAcc = new AccOrDeaccelerator();
		accOrAcc.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		Roll roll = new Roll("123");
		luckyunluckyRollServiceImpl.setLuckyOrUnluckyRoll(accOrAcc, roll);
		assertEquals(RollType.UNLUCKY, roll.getRollType());
	}

	@Test
	void testSetLuckyOrUnluckyRollForThreshold() {
		TrackMovement trackMovement = new TrackMovement();
		trackMovement.setWinLuckyThrsldCount(2);
		Roll roll = new Roll("123");
		luckyunluckyRollServiceImpl.setLuckyOrUnluckyRoll(trackMovement, roll);
		assertEquals(RollType.LUCKY, roll.getRollType());
	}

	@Test
	void testSetLuckyOrUnluckyRollForThresholdWithInvalid() {
		TrackMovement trackMovement = new TrackMovement();
		trackMovement.setWinLuckyThrsldCount(4);
		Roll roll = new Roll("123");
		luckyunluckyRollServiceImpl.setLuckyOrUnluckyRoll(trackMovement, roll);
		assertNull(roll.getRollType());
	}

}
