package ai.sahaj.snakeladder.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ai.sahaj.snakeladder.constants.SnakeLadderConstants;
import ai.sahaj.snakeladder.dto.backend.TrackGameMovement;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.entity.Player;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.entity.Simulation;
import ai.sahaj.snakeladder.services.DiceService;
import ai.sahaj.snakeladder.services.LuckyUnluckyRollService;
import ai.sahaj.snakeladder.services.PositionCalculatorService;
import ai.sahaj.snakeladder.services.RollService;

public class AutoModeGameServiceImpl extends GameServiceImpl {

	@Autowired
	private RollService rollService;
	@Autowired
	private PositionCalculatorService posCalService;
	@Autowired
	private LuckyUnluckyRollService luckyUnluckyRollService;

	@Override
	public TrackGameMovement play(Simulation simulation, Game game, DiceService diceService) {
		boolean anyWinner = false;
		List<Roll> rolls = new ArrayList<>();
		TrackGameMovement trackGameMovement = initializeGameMovements(simulation.getPlayers(), game);
		Player winner = null;
		while (!anyWinner) {
			int count = 0;
			for (Player player : simulation.getPlayers()) {
				Roll roll = rollService.roll(diceService);
				TrackMovement trackMovement = posCalService.updateMovement(trackGameMovement, count, roll);
				roll.setGame(game);
				rolls.add(roll);
				anyWinner = isWinner(trackMovement);
				if (anyWinner) {
					luckyUnluckyRollService.setLuckyOrUnluckyRoll(trackMovement, roll);
					winner = player;
					break;
				}
				count++;
			}
		}
		game.setWinner(winner);
		trackGameMovement.setRolls(rolls);
		return trackGameMovement;
	}

	private TrackGameMovement initializeGameMovements(List<Player> playersList, Game game) {

		TrackGameMovement trackGameMovement = new TrackGameMovement();
		List<TrackMovement> trackMovements = new ArrayList<>();

		for (Player player : playersList) {
			TrackMovement trackMovement = new TrackMovement();
			trackMovement.setPlayer(player);
			trackMovements.add(trackMovement);
		}
		trackGameMovement.setTrackMovement(trackMovements);
		trackGameMovement.setGameAccsOrDeaccs(new HashSet<>());
		trackGameMovement.setGame(game);
		return trackGameMovement;
	}

	private boolean isWinner(TrackMovement trackMovement) {
		return trackMovement.getPosition().getNumber() == SnakeLadderConstants.BOARD_FINAL_POS;
	}

}
