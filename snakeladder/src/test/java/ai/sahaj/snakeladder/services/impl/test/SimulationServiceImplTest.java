package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.constants.SimulationMode;
import ai.sahaj.snakeladder.dto.backend.TrackGameMovement;
import ai.sahaj.snakeladder.dto.frontend.AddSimulationDto;
import ai.sahaj.snakeladder.dto.frontend.PlayerDto;
import ai.sahaj.snakeladder.dto.frontend.SimulationAPIBoardDto;
import ai.sahaj.snakeladder.dto.frontend.SimulationDto;
import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.entity.GameAccOrDeacclerator;
import ai.sahaj.snakeladder.entity.Simulation;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.exceptions.ConflictException;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.repositories.SimulationRepository;
import ai.sahaj.snakeladder.services.BoardService;
import ai.sahaj.snakeladder.services.DiceService;
import ai.sahaj.snakeladder.services.GameAccOrDeaccleratorService;
import ai.sahaj.snakeladder.services.GameService;
import ai.sahaj.snakeladder.services.PlayersService;
import ai.sahaj.snakeladder.services.RollService;
import ai.sahaj.snakeladder.services.impl.SimulationServiceImpl;
import ai.sahaj.snakeladder.util.DiceServiceFactory;
import ai.sahaj.snakeladder.util.GameServiceFactory;
import ai.sahaj.snakeladder.util.test.TestUtil;

@ExtendWith(SpringExtension.class)
class SimulationServiceImplTest {

	@InjectMocks
	private SimulationServiceImpl simulationServiceImpl;
	@Mock
	private SimulationRepository simulationRepo;
	@Mock
	private BoardService boardService;
	@Mock
	private PlayersService playerService;
	@Mock
	private RollService rollService;
	@Mock
	private GameAccOrDeaccleratorService gameAccOrDeaccService;
	@Mock
	private GameService gameService;
	@Mock
	private DiceService diceService;
	@Mock
	private GameServiceFactory gameServiceFactory;
	@Mock
	private DiceServiceFactory diceServiceFactory;

	@Test
	void testAddSimulation() {
		AddSimulationDto simulationDto = new AddSimulationDto();
		simulationDto.setName("Dry Simulation");
		SimulationAPIBoardDto boardDto = new SimulationAPIBoardDto();
		boardDto.setId(123);
		simulationDto.setBoard(boardDto);
		PlayerDto playerDto1 = new PlayerDto(1l, "abc", "abc@xyz.ai");
		PlayerDto playerDto2 = new PlayerDto(2l, "def", "def@xyz.ai");
		List<PlayerDto> playerDtoList = new ArrayList<>();
		playerDtoList.add(playerDto1);
		playerDtoList.add(playerDto2);
		simulationDto.setPlayers(playerDtoList);
		Simulation simulation = TestUtil.getSimulation();
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		Mockito.when(simulationRepo.findAll()).thenReturn(new ArrayList<>());
		Mockito.when(boardService.getBoard(Mockito.anyInt())).thenReturn(Optional.of(simulation.getBoard()));
		Mockito.when(simulationRepo.save(Mockito.any(Simulation.class))).thenReturn(simulation);
		Mockito.when(playerService.getPlayer(playersId)).thenReturn(TestUtil.getPlayers());
		simulationServiceImpl.addSimulation(simulationDto);
		Mockito.verify(simulationRepo).save(Mockito.any(Simulation.class));
	}

	@Test
	void testAddSimulationWithExisting() {
		AddSimulationDto simulationDto = new AddSimulationDto();
		simulationDto.setName("Dry Simulation");
		SimulationAPIBoardDto boardDto = new SimulationAPIBoardDto();
		boardDto.setId(123);
		simulationDto.setBoard(boardDto);
		PlayerDto playerDto1 = new PlayerDto(1l, "abc", "abc@xyz.ai");
		PlayerDto playerDto2 = new PlayerDto(2l, "def", "def@xyz.ai");
		List<PlayerDto> playerDtoList = new ArrayList<>();
		playerDtoList.add(playerDto1);
		playerDtoList.add(playerDto2);
		simulationDto.setPlayers(playerDtoList);
		Simulation simulation = TestUtil.getSimulation();
		List<Simulation> simulations = new ArrayList<>();
		simulations.add(simulation);
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		Mockito.when(simulationRepo.findAll()).thenReturn(simulations);
		Mockito.when(boardService.getBoard(Mockito.anyInt())).thenReturn(Optional.of(simulation.getBoard()));
		Mockito.when(simulationRepo.save(Mockito.any(Simulation.class))).thenReturn(simulation);
		Mockito.when(playerService.getPlayer(playersId)).thenReturn(TestUtil.getPlayers());
		ConflictException thrown = assertThrows(ConflictException.class, () -> {
			simulationServiceImpl.addSimulation(simulationDto);
		});
		assertEquals("Simulation name is already taken", thrown.getMessage());
	}

	@Test
	void testAddSimulationWithMissingBoard() {
		AddSimulationDto simulationDto = new AddSimulationDto();
		simulationDto.setName("Dry Simulation");
		SimulationAPIBoardDto boardDto = new SimulationAPIBoardDto();
		boardDto.setId(123);
		simulationDto.setBoard(boardDto);
		PlayerDto playerDto1 = new PlayerDto(1l, "abc", "abc@xyz.ai");
		PlayerDto playerDto2 = new PlayerDto(2l, "def", "def@xyz.ai");
		PlayerDto playerDto3 = new PlayerDto(3l, "ghi", "ghi@xyz.ai");
		List<PlayerDto> playerDtoList = new ArrayList<>();
		playerDtoList.add(playerDto1);
		playerDtoList.add(playerDto2);
		playerDtoList.add(playerDto3);
		simulationDto.setPlayers(playerDtoList);
		Simulation simulation = TestUtil.getSimulation();
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		Mockito.when(simulationRepo.findAll()).thenReturn(new ArrayList<>());
		Mockito.when(boardService.getBoard(Mockito.anyInt())).thenReturn(Optional.of(simulation.getBoard()));
		Mockito.when(simulationRepo.save(Mockito.any(Simulation.class))).thenReturn(simulation);
		Mockito.when(playerService.getPlayer(playersId)).thenReturn(TestUtil.getPlayers());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			simulationServiceImpl.addSimulation(simulationDto);
		});
		assertEquals("One or more player doesn't exists", thrown.getMessage());
	}

	@Test
	void testUpdateSimulation() {
		AddSimulationDto simulationDto = new AddSimulationDto();
		simulationDto.setName("Dry Simulation");
		SimulationAPIBoardDto boardDto = new SimulationAPIBoardDto();
		boardDto.setId(123);
		simulationDto.setBoard(boardDto);
		PlayerDto playerDto1 = new PlayerDto(1l, "abc", "abc@xyz.ai");
		PlayerDto playerDto2 = new PlayerDto(2l, "def", "def@xyz.ai");
		List<PlayerDto> playerDtoList = new ArrayList<>();
		playerDtoList.add(playerDto1);
		playerDtoList.add(playerDto2);
		simulationDto.setPlayers(playerDtoList);
		Simulation simulation = TestUtil.getSimulation();
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.of(simulation));
		Mockito.when(boardService.getBoard(Mockito.anyInt())).thenReturn(Optional.of(simulation.getBoard()));
		Mockito.when(simulationRepo.save(Mockito.any(Simulation.class))).thenReturn(simulation);
		Mockito.when(playerService.getPlayer(playersId)).thenReturn(TestUtil.getPlayers());
		simulationServiceImpl.updateSimulation("123", simulationDto);
		Mockito.verify(simulationRepo).save(Mockito.any(Simulation.class));
	}

	@Test
	void testUpdateExistingSimulation() {
		AddSimulationDto simulationDto = new AddSimulationDto();
		simulationDto.setName("Dry Simulation");
		SimulationAPIBoardDto boardDto = new SimulationAPIBoardDto();
		boardDto.setId(123);
		simulationDto.setBoard(boardDto);
		PlayerDto playerDto1 = new PlayerDto(1l, "abc", "abc@xyz.ai");
		PlayerDto playerDto2 = new PlayerDto(2l, "def", "def@xyz.ai");
		List<PlayerDto> playerDtoList = new ArrayList<>();
		playerDtoList.add(playerDto1);
		playerDtoList.add(playerDto2);
		simulationDto.setPlayers(playerDtoList);
		Simulation simulation = TestUtil.getSimulation();
		List<Simulation> simulations = new ArrayList<>();
		simulations.add(simulation);
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		Mockito.when(simulationRepo.findById("12")).thenReturn(Optional.of(simulation));
		Mockito.when(boardService.getBoard(Mockito.anyInt())).thenReturn(Optional.of(simulation.getBoard()));
		Mockito.when(simulationRepo.save(Mockito.any(Simulation.class))).thenReturn(simulation);
		Mockito.when(simulationRepo.findAll()).thenReturn(simulations);
		Mockito.when(playerService.getPlayer(playersId)).thenReturn(TestUtil.getPlayers());
		ConflictException thrown = assertThrows(ConflictException.class, () -> {
			simulationServiceImpl.updateSimulation("12", simulationDto);
		});
		assertEquals("Simulation name is already taken", thrown.getMessage());
	}

	@Test
	void testUpdateSimulationWithMissing() {
		AddSimulationDto simulationDto = new AddSimulationDto();
		simulationDto.setName("Dry Simulation");
		SimulationAPIBoardDto boardDto = new SimulationAPIBoardDto();
		boardDto.setId(123);
		simulationDto.setBoard(boardDto);
		PlayerDto playerDto1 = new PlayerDto(1l, "abc", "abc@xyz.ai");
		PlayerDto playerDto2 = new PlayerDto(2l, "def", "def@xyz.ai");
		List<PlayerDto> playerDtoList = new ArrayList<>();
		playerDtoList.add(playerDto1);
		playerDtoList.add(playerDto2);
		simulationDto.setPlayers(playerDtoList);
		Simulation simulation = TestUtil.getSimulation();
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.empty());
		Mockito.when(boardService.getBoard(Mockito.anyInt())).thenReturn(Optional.of(simulation.getBoard()));
		Mockito.when(simulationRepo.save(Mockito.any(Simulation.class))).thenReturn(simulation);
		Mockito.when(playerService.getPlayer(playersId)).thenReturn(TestUtil.getPlayers());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			simulationServiceImpl.updateSimulation("123", simulationDto);
		});
		assertEquals("Simulation doesn't exists for given id", thrown.getMessage());
	}

	@Test
	void testUpdateSimulationAlreadyExecuted() {
		AddSimulationDto simulationDto = new AddSimulationDto();
		simulationDto.setName("Dry Simulation");
		SimulationAPIBoardDto boardDto = new SimulationAPIBoardDto();
		boardDto.setId(123);
		simulationDto.setBoard(boardDto);
		PlayerDto playerDto1 = new PlayerDto(1l, "abc", "abc@xyz.ai");
		PlayerDto playerDto2 = new PlayerDto(2l, "def", "def@xyz.ai");
		List<PlayerDto> playerDtoList = new ArrayList<>();
		playerDtoList.add(playerDto1);
		playerDtoList.add(playerDto2);
		simulationDto.setPlayers(playerDtoList);
		Simulation simulation = TestUtil.getSimulation();
		List<Game> games = new ArrayList<>();
		games.add(new Game());
		simulation.setGames(games);
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.of(simulation));
		Mockito.when(boardService.getBoard(Mockito.anyInt())).thenReturn(Optional.of(simulation.getBoard()));
		Mockito.when(simulationRepo.save(Mockito.any(Simulation.class))).thenReturn(simulation);
		Mockito.when(playerService.getPlayer(playersId)).thenReturn(TestUtil.getPlayers());

		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			simulationServiceImpl.updateSimulation("123", simulationDto);
		});
		assertEquals("Executed simulations can't be updated", thrown.getMessage());
	}

	@Test
	void testUpdateSimulationWithMissingBoard() {
		AddSimulationDto simulationDto = new AddSimulationDto();
		simulationDto.setName("Dry Simulation");
		SimulationAPIBoardDto boardDto = new SimulationAPIBoardDto();
		boardDto.setId(123);
		simulationDto.setBoard(boardDto);
		PlayerDto playerDto1 = new PlayerDto(1l, "abc", "abc@xyz.ai");
		PlayerDto playerDto2 = new PlayerDto(2l, "def", "def@xyz.ai");
		List<PlayerDto> playerDtoList = new ArrayList<>();
		playerDtoList.add(playerDto1);
		playerDtoList.add(playerDto2);
		simulationDto.setPlayers(playerDtoList);
		Simulation simulation = TestUtil.getSimulation();
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.of(simulation));
		Mockito.when(boardService.getBoard(Mockito.anyInt())).thenReturn(Optional.empty());
		Mockito.when(simulationRepo.save(Mockito.any(Simulation.class))).thenReturn(simulation);
		Mockito.when(playerService.getPlayer(playersId)).thenReturn(TestUtil.getPlayers());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			simulationServiceImpl.updateSimulation("123", simulationDto);
		});
		assertEquals("Given board doesn't exists", thrown.getMessage());
	}

	@Test
	void testGetSimulation() {
		Simulation simulation = TestUtil.getSimulation();
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.of(simulation));
		SimulationDto simualtionDto = simulationServiceImpl.getSimulation("123");
		assertEquals(simulation.getId(), simualtionDto.getId());
	}

	@Test
	void testGetSimulationWithMissing() {
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.empty());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			simulationServiceImpl.getSimulation("123");
		});
		assertEquals("Simulation doesn't exists for given id", thrown.getMessage());
	}

	@Test
	void testGetSimulationDto() {
		Simulation simulation = TestUtil.getSimulation();
		List<Simulation> simulations = new ArrayList<>();
		simulations.add(simulation);
		Mockito.when(simulationRepo.findAll()).thenReturn(simulations);
		Set<SimulationDto> simulationDtos = simulationServiceImpl.getSimulation();
		List<SimulationDto> simulationDtosList = new ArrayList<>(simulationDtos);
		assertEquals(simulations.get(0).getId(), simulationDtosList.get(0).getId());
	}

	@Test
	void testGetSimualtionEntity() {
		Simulation simulation = TestUtil.getSimulation();
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.of(simulation));
		Optional<Simulation> optSimulation = simulationServiceImpl.getSimulationEntity("123");
		assertEquals(simulation.getId(), optSimulation.get().getId());
	}

	@Test
	void testStartAutomatedSimulations() {
		TrackGameMovement trackGameMovement = new TrackGameMovement();
		Game game = new Game();
		game.setWinner(TestUtil.getPlayers().get(0));
		trackGameMovement.setGame(game);
		GameAccOrDeacclerator gameAccOrDeacc = new GameAccOrDeacclerator();
		gameAccOrDeacc.setAccOrDeacc(TestUtil.getAccOrDeaccs().get(0));
		GameAccOrDeacclerator gameAccOrDeacc1 = new GameAccOrDeacclerator();
		gameAccOrDeacc.setAccOrDeacc(TestUtil.getAccOrDeaccs().get(1));
		Set<GameAccOrDeacclerator> gameAccOrDeaccs = new HashSet<>();
		gameAccOrDeaccs.add(gameAccOrDeacc);
		gameAccOrDeaccs.add(gameAccOrDeacc1);
		trackGameMovement.setGameAccsOrDeaccs(gameAccOrDeaccs);
		Simulation simulation = TestUtil.getSimulation();
		Mockito.doNothing().when(gameService).saveGame(Mockito.any(Game.class));
		Mockito.doNothing().when(gameAccOrDeaccService).saveAll(Mockito.anyList());
		Mockito.doNothing().when(rollService).saveAllRolls(Mockito.anyList());
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.of(simulation));
		Mockito.when(diceServiceFactory.getDiceService(SimulationMode.AUTO)).thenReturn(diceService);
		Mockito.when(gameServiceFactory.getGameService(SimulationMode.AUTO)).thenReturn(gameService);
		Mockito.when(gameService.play(Mockito.any(Simulation.class), Mockito.any(Game.class),
				Mockito.any(DiceService.class))).thenReturn(trackGameMovement);
		String message = simulationServiceImpl.startAutomatedSimulation("123");
		assertEquals("Simulation completed successfully", message);
	}

	@Test
	void testStartAutomatedSimulationsMissingSimulation() {
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.empty());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			simulationServiceImpl.startAutomatedSimulation("123");
		});
		assertEquals("The given simulation doesn't exist", thrown.getMessage());
	}

	@Test
	void testStartAutomatedSimulationsAlreadyDone() {
		Simulation simulation = TestUtil.getSimulation();
		Game game = new Game();
		game.setId("1234");
		List<Game> games = new ArrayList<>();
		games.add(game);
		simulation.setGames(games);
		Mockito.when(simulationRepo.findById("123")).thenReturn(Optional.of(simulation));
		ConflictException thrown = assertThrows(ConflictException.class, () -> {
			simulationServiceImpl.startAutomatedSimulation("123");
		});
		assertEquals("Simulations are already done for it", thrown.getMessage());
	}
}
