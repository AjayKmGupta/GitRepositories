package ai.sahaj.snakeladder.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.repositories.GameRepository;
import ai.sahaj.snakeladder.services.GameService;

public abstract class GameServiceImpl implements GameService {

	private GameRepository gameRepository;

	@Autowired
	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public void saveGame(Game game) {
		gameRepository.save(game);
	}

}
