package ai.sahaj.snakeladder.util.test;

import static ai.sahaj.snakeladder.constants.SnakeLadderConstants.BOARD_FINAL_POS;
import static ai.sahaj.snakeladder.constants.SnakeLadderConstants.BOARD_START_POS;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.constants.SimulationMode;
import ai.sahaj.snakeladder.constants.SnakeLadderConstants;
import ai.sahaj.snakeladder.dto.backend.Dice;
import ai.sahaj.snakeladder.dto.backend.TrackMovement;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Player;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.entity.Simulation;

public class TestUtil {

	public static List<Position> getAllPositions() {
		List<Position> positions = new ArrayList<>();
		for (int i = BOARD_START_POS; i < BOARD_FINAL_POS; i++) {
			Position position = new Position();
			position.setId(i);
			position.setNumber(i);
			positions.add(position);
		}
		return positions;
	}

	public static Simulation getSimulation() {

		Simulation simulation = new Simulation();
		simulation.setId("123");
		simulation.setName("Dry Simulation");
		simulation.setSimulationMode(SimulationMode.AUTO);
		simulation.setSimulationCount(1);
		simulation.setBoard(getBoard());
		simulation.setPlayers(getPlayers());
		return simulation;
	}

	private static List<Player> getPlayers() {
		List<Player> players = new ArrayList<>();
		Player pl1 = new Player();
		pl1.setId(1l);
		pl1.setName("abc");
		pl1.setEmailId("abc@xyz.ai");
		players.add(pl1);

		Player pl2 = new Player();
		pl2.setId(2l);
		pl2.setName("def");
		pl2.setEmailId("def@xyz.ai");
		players.add(pl2);
		return players;
	}

	public static Board getBoard() {

		Board board = new Board();
		board.setId(1);
		board.setStartPos(new Position(SnakeLadderConstants.BOARD_START_POS));
		board.setFinalPos(new Position(SnakeLadderConstants.BOARD_FINAL_POS));
		board.setAccOrDeAccelerators(getAccOrDeaccs());
		return board;
	}

	private static List<AccOrDeaccelerator> getAccOrDeaccs() {

		List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();

		AccOrDeaccelerator accOrDeacc = new AccOrDeaccelerator();
		accOrDeacc.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		Position startPos = new Position();
		startPos.setNumber(34);
		accOrDeacc.setStartPos(startPos);
		Position finalPos = new Position();
		finalPos.setNumber(13);
		accOrDeacc.setFinalPosition(finalPos);

		AccOrDeaccelerator accOrDeacc1 = new AccOrDeaccelerator();
		accOrDeacc1.setAccOrDeAccType(AccOrDeacceleratorType.SNAKE);
		Position startPos1 = new Position();
		startPos1.setNumber(55);
		accOrDeacc1.setStartPos(startPos1);
		Position finalPos1 = new Position();
		finalPos1.setNumber(13);
		accOrDeacc1.setFinalPosition(finalPos1);

		accOrDeaccs.add(accOrDeacc);
		accOrDeaccs.add(accOrDeacc1);
		return accOrDeaccs;
	}

	public static Roll getRoll() {
		Dice dice = null;
		Roll roll = new Roll(UUID.randomUUID().toString());
		while (dice == null || dice.getFaceValue() == Dice.MAX_VAL) {
			dice = new Dice(ThreadLocalRandom.current().nextInt(Dice.MIN_VAL, Dice.MAX_VAL + 1));
			roll.setNoOfRolls(roll.getNoOfRolls() + 1);
			roll.setFaceValue(dice.getFaceValue());
		}
		return roll;
	}

	public static TrackMovement getTrackMovement() {

		TrackMovement trackMovement = new TrackMovement();
		trackMovement.setPosition(new Position(BOARD_FINAL_POS));
		trackMovement.setPlayer(getPlayers().get(0));
		return trackMovement;
	}

}
