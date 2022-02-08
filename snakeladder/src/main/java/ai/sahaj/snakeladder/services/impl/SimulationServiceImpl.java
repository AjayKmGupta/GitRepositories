package ai.sahaj.snakeladder.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ai.sahaj.snakeladder.dto.backend.SimulationDataDto;
import ai.sahaj.snakeladder.dto.frontend.AddSimulationDto;
import ai.sahaj.snakeladder.dto.frontend.PlayerDto;
import ai.sahaj.snakeladder.dto.frontend.SimulationDto;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Player;
import ai.sahaj.snakeladder.entity.Simulation;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.exceptions.ConflictException;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.repositories.SimulationRepository;
import ai.sahaj.snakeladder.services.BoardService;
import ai.sahaj.snakeladder.services.PlayersService;
import ai.sahaj.snakeladder.services.RollService;
import ai.sahaj.snakeladder.services.SimulationService;
import ai.sahaj.snakeladder.util.GameServiceFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SimulationServiceImpl implements SimulationService {

	@Autowired
	private SimulationRepository simulationRepo;
	@Autowired
	private BoardService boardService;
	@Autowired
	private PlayersService playerService;
	@Autowired
	private RollService rollService;
	@Autowired
	private GameServiceFactory gameServiceFactory;

	@Override
	public void addSimulation(AddSimulationDto simulationDto) {
		checkIfSimulationAlreadyExists(simulationDto);
		Simulation simulation = new Simulation();
		BeanUtils.copyProperties(simulationDto, simulation);
		simulation.setId(UUID.randomUUID().toString());
		updateSimulatonData(simulation, simulationDto);
		simulationRepo.save(simulation);

	}

	@Override
	public void updateSimulation(String simulationId, AddSimulationDto simulationDto) {

		Optional<Simulation> optSimulation = simulationRepo.findById(simulationId);
		if (optSimulation.isEmpty()) {
			throw new ResourceNotFoundException("Player doesn't exists for given id");
		}
		checkIfSimulationAlreadyExists(simulationDto, simulationId);
		Simulation simulation = optSimulation.get();
		if (!CollectionUtils.isEmpty(simulation.getRolls())) {
			throw new BadRequestException("Executed simulations can't be updated");
		}
		BeanUtils.copyProperties(simulationDto, simulation);
		updateSimulatonData(simulation, simulationDto);
		simulationRepo.save(simulation);

	}

	private void checkIfSimulationAlreadyExists(AddSimulationDto simulationDto) {

		List<Simulation> simulations = simulationRepo.findAll();
		if (simulations.stream().anyMatch(s -> s.getName().equals(simulationDto.getName()))) {
			throw new ConflictException("Simulation name is already taken");
		}

	}

	private void checkIfSimulationAlreadyExists(AddSimulationDto simulationDto, String simulationId) {

		List<Simulation> simulations = simulationRepo.findAll();
		if (simulations.stream()
				.anyMatch(s -> s.getName().equals(simulationDto.getName()) && !s.getId().equals(simulationId))) {
			throw new ConflictException("Simulation name is already taken");
		}

	}

	private void updateSimulatonData(Simulation simulation, AddSimulationDto simulationDto) {
		Optional<Board> optBoard = boardService.getBoard(simulationDto.getBoard().getId());
		if (optBoard.isEmpty()) {
			throw new ResourceNotFoundException("Given board doesn't exists");
		}
		Set<Long> playersId = simulationDto.getPlayers().stream().map(PlayerDto::getId).collect(Collectors.toSet());
		List<Player> players = playerService.getPlayer(playersId);
		if (CollectionUtils.isEmpty(players) || players.size() < simulationDto.getPlayers().size()) {
			throw new ResourceNotFoundException("One or more player doesn't exists");
		}
		simulation.setBoard(optBoard.get());
		simulation.setPlayers(players);

	}

	@Override
	public SimulationDto getSimulation(String simulationId) {
		Optional<Simulation> optSimulation = simulationRepo.findById(simulationId);
		if (optSimulation.isEmpty()) {
			throw new ResourceNotFoundException("Player doesn't exists for given id");
		}
		SimulationDto simulationDto = SimulationDto.builder().build();
		BeanUtils.copyProperties(optSimulation.get(), simulationDto);
		return simulationDto;
	}

	@Override
	public Set<SimulationDto> getSimulation() {
		List<Simulation> simulations = simulationRepo.findAll();

		return simulations.stream()
				.map(s -> SimulationDto.builder().id(s.getId()).name(s.getName()).simulationMode(s.getSimulationMode())
						.simulationCount(s.getSimulationCount()).board(boardService.getBoardDtoFromBoard(s.getBoard()))
						.players(playerService.getPlayerDtosFromPlayer(s.getPlayers())).build())
				.collect(Collectors.toSet());
	}

	@Override
	public String startSimulation(String simulationId) {
		Optional<Simulation> optSimulation = simulationRepo.findById(simulationId);
		if (optSimulation.isEmpty()) {
			throw new ResourceNotFoundException("The given simulation doesn't exist");
		}
		if (!CollectionUtils.isEmpty(optSimulation.get().getRolls())) {
			throw new ConflictException("Simulations are already done for it");
		}
		for (int i = 0; i < optSimulation.get().getSimulationCount(); i++) {
			SimulationDataDto simulationDataDto = gameServiceFactory
					.getGameService(optSimulation.get().getSimulationMode()).play(optSimulation.get());
			log.info("Winner is: {}", simulationDataDto.getWinner().getName());
			rollService.saveAllRolls(simulationDataDto.getRolls());
		}
		return "Simulation completed successfully";
	}

}
