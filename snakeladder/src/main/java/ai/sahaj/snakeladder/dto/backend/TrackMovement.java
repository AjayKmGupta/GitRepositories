package ai.sahaj.snakeladder.dto.backend;

import ai.sahaj.snakeladder.entity.Player;
import ai.sahaj.snakeladder.entity.Position;
import lombok.Data;

@Data
public class TrackMovement {

	private Player player;
	private Position position;

}
