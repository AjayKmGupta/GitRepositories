package ai.sahaj.snakeladder.dto.backend;

import java.util.List;
import java.util.Set;

import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.entity.GameAccOrDeacclerator;
import ai.sahaj.snakeladder.entity.Roll;
import lombok.Data;

@Data
public class TrackGameMovement {

	private Game game;
	private List<TrackMovement> trackMovement;
	private Set<GameAccOrDeacclerator> gameAccsOrDeaccs;
	private List<Roll> rolls;
}
