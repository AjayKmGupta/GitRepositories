package ai.sahaj.snakeladder.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.entity.GameAccOrDeacclerator;
import ai.sahaj.snakeladder.repositories.GameAccOrDeaccleratorRepository;
import ai.sahaj.snakeladder.services.GameAccOrDeaccleratorService;

@Service
public class GameAccOrDeaccleratorServiceImpl implements GameAccOrDeaccleratorService {

	@Autowired
	private GameAccOrDeaccleratorRepository gameAccOrDeaccRepo;

	@Override
	public void saveAll(List<GameAccOrDeacclerator> gameAccsOrDeaccs) {
		gameAccOrDeaccRepo.saveAll(gameAccsOrDeaccs);
	}

}
