package ai.sahaj.snakeladder.services;

import ai.sahaj.snakeladder.dto.backend.TrackGameMovement;
import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.entity.Simulation;

public interface GameService {

	TrackGameMovement play(Simulation simulation, Game game, DiceService diceServie);

	void saveGame(Game game);
}
