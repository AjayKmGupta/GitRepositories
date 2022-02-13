package ai.sahaj.snakeladder.services.impl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.entity.GameAccOrDeacclerator;
import ai.sahaj.snakeladder.repositories.GameAccOrDeaccleratorRepository;
import ai.sahaj.snakeladder.services.impl.GameAccOrDeaccleratorServiceImpl;

@ExtendWith(SpringExtension.class)
class GameAccOrDeaccleratorServiceImplTest {

	@InjectMocks
	private GameAccOrDeaccleratorServiceImpl gameAccOrDeaccServiceImpl;
	@Mock
	private GameAccOrDeaccleratorRepository gameAccOrDeaccRepo;

	@Test
	void testSaveAll() {
		List<GameAccOrDeacclerator> gameAccOrDeaccs = new ArrayList<>();
		GameAccOrDeacclerator gameAccOrDeacc = new GameAccOrDeacclerator("123", new Game(), new AccOrDeaccelerator());
		gameAccOrDeaccs.add(gameAccOrDeacc);
		Mockito.when(gameAccOrDeaccRepo.saveAll(gameAccOrDeaccs)).thenReturn(gameAccOrDeaccs);
		gameAccOrDeaccServiceImpl.saveAll(gameAccOrDeaccs);
		Mockito.verify(gameAccOrDeaccRepo).saveAll(gameAccOrDeaccs);
	}
}
