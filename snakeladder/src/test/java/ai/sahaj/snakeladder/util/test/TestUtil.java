package ai.sahaj.snakeladder.util.test;

import static ai.sahaj.snakeladder.constants.SnakeLadderConstants.BOARD_FINAL_POS;
import static ai.sahaj.snakeladder.constants.SnakeLadderConstants.BOARD_START_POS;

import java.util.ArrayList;
import java.util.List;

import ai.sahaj.snakeladder.entity.Position;

public class TestUtil {

	public static List<Position> getAllPositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = BOARD_START_POS; i < BOARD_FINAL_POS; i++) {
			Position position = new Position();
			position.setId(i);
			position.setNumber(i);
			positions.add(position);
		}
		return positions;
	}
}
