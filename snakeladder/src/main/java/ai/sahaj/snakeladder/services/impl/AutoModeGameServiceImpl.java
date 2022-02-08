package ai.sahaj.snakeladder.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.constants.SnakeLadderConstants;
import ai.sahaj.snakeladder.dto.backend.SimulationDataDto;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.Player;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.entity.Simulation;
import ai.sahaj.snakeladder.services.GameService;
import ai.sahaj.snakeladder.services.PositionCalculatorService;
import ai.sahaj.snakeladder.services.RollService;

@Service("autoModeGameService")
public class AutoModeGameServiceImpl implements GameService {

	@Autowired
	private RollService rollService;
	@Autowired
	private PositionCalculatorService posCalService;

	@Override
	public SimulationDataDto play(Simulation simulation) {
		SimulationDataDto simulationDataDto = new SimulationDataDto();
		boolean anyWinner = false;
		List<Roll> rolls = new ArrayList<>();
		List<TrackMovement> trackMovements = initializePlayersPosition(simulation.getPlayers());
		Player winner = null;
		while (!anyWinner) {
			int count = 0;
			for (Player player : simulation.getPlayers()) {
				Roll roll = rollService.roll();
				TrackMovement trackMovement = trackMovements.get(count);
				posCalService.updateMovement(trackMovement, roll, simulation.getBoard());
				roll.setSimulation(simulation);
				rolls.add(roll);
				anyWinner = isWinner(trackMovement);
				if (anyWinner) {
					winner = player;
					break;
				}
				count++;
			}
		}
		simulationDataDto.setWinner(winner);
		simulationDataDto.setRolls(rolls);
		return simulationDataDto;
	}

	private boolean isWinner(TrackMovement trackMovement) {
		return trackMovement.getPosition().getNumber() == SnakeLadderConstants.BOARD_FINAL_POS;
	}

	private List<TrackMovement> initializePlayersPosition(List<Player> playersList) {
		List<TrackMovement> trackMovements = new ArrayList<>();

		for (Player player : playersList) {
			TrackMovement trackMovement = new TrackMovement();
			trackMovement.setPlayer(player);
			trackMovements.add(trackMovement);
		}
		return trackMovements;
	}

}
