package ai.sahaj.snakeladder.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.constants.Unit;
import ai.sahaj.snakeladder.dto.backend.RollStats;
import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.dto.backend.StatsRequest;
import ai.sahaj.snakeladder.dto.backend.TakenAccOrDeAccDiff;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.repositories.AccOrDeacceleratorRepository;
import ai.sahaj.snakeladder.repositories.RollRepository;
import ai.sahaj.snakeladder.services.StatsService;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private RollRepository rollRepository;

	@Autowired
	private AccOrDeacceleratorRepository accOrDeaccRepo;

	@Override
	public List<StatsData> getStats(List<StatsRequest> statsRequests) {

		List<StatsData> statsDatas = new ArrayList<>();
		for (StatsRequest statsReq : statsRequests) {
			StatsData statData = getStatValue(statsReq);
			statsDatas.add(statData);
		}
		return statsDatas;
	}

	private StatsData getStatValue(StatsRequest statsReq) {
		StatsData statData = new StatsData();
		switch (statsReq.getMetricName()) {
		case AOCAVG:
			List<TakenAccOrDeAccDiff> takenAccOrDeaccsDiff = accOrDeaccRepo
					.getSimulationWiseSnakeOrLadderTaken(statsReq.getSimulationId(), 0);
			Integer totalLadderSum = takenAccOrDeaccsDiff.stream().mapToInt(TakenAccOrDeAccDiff::getDiffAmount)
					.reduce(0, Integer::sum);
			statData.setMetricValue((double) totalLadderSum / takenAccOrDeaccsDiff.size());
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.AOCAVG);
			statData.setMetricDescription("Average amount of climb during the game");
			break;
		case AOCMAX:
			takenAccOrDeaccsDiff = accOrDeaccRepo.getSimulationWiseSnakeOrLadderTaken(statsReq.getSimulationId(), 0);
			Optional<TakenAccOrDeAccDiff> takenAccOrDeaccDiff = takenAccOrDeaccsDiff.stream()
					.max(Comparator.comparingInt(TakenAccOrDeAccDiff::getDiffAmount));
			if (takenAccOrDeaccDiff.isPresent()) {
				statData.setMetricValue(takenAccOrDeaccDiff.get().getDiffAmount());
			}
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.AOCMAX);
			statData.setMetricDescription("Maximum amount of climb during the game");
			break;
		case AOCMIN:
			takenAccOrDeaccsDiff = accOrDeaccRepo.getSimulationWiseSnakeOrLadderTaken(statsReq.getSimulationId(), 0);
			takenAccOrDeaccDiff = takenAccOrDeaccsDiff.stream()
					.min(Comparator.comparingInt(TakenAccOrDeAccDiff::getDiffAmount));
			if (takenAccOrDeaccDiff.isPresent()) {
				statData.setMetricValue(takenAccOrDeaccDiff.get().getDiffAmount());
			}
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.AOCMIN);
			statData.setMetricDescription("Minimum amount of climb during the game");
			break;
		case AOSAVG:
			takenAccOrDeaccsDiff = accOrDeaccRepo.getSimulationWiseSnakeOrLadderTaken(statsReq.getSimulationId(), 1);
			Integer totalSnakeSum = takenAccOrDeaccsDiff.stream().mapToInt(TakenAccOrDeAccDiff::getDiffAmount).reduce(0,
					Integer::sum);
			statData.setMetricValue((double) Math.abs(totalSnakeSum) / takenAccOrDeaccsDiff.size());
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.AOSAVG);
			statData.setMetricDescription("Average amount of slide during the game");
			break;
		case AOSMAX:
			takenAccOrDeaccsDiff = accOrDeaccRepo.getSimulationWiseSnakeOrLadderTaken(statsReq.getSimulationId(), 1);
			takenAccOrDeaccDiff = takenAccOrDeaccsDiff.stream()
					.min(Comparator.comparingInt(TakenAccOrDeAccDiff::getDiffAmount));
			if (takenAccOrDeaccDiff.isPresent()) {
				statData.setMetricValue(Math.abs(takenAccOrDeaccDiff.get().getDiffAmount()));
			}
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.AOSMAX);
			statData.setMetricDescription("Maximum amount of slide during the game");
			break;
		case AOSMIN:
			takenAccOrDeaccsDiff = accOrDeaccRepo.getSimulationWiseSnakeOrLadderTaken(statsReq.getSimulationId(), 1);
			takenAccOrDeaccDiff = takenAccOrDeaccsDiff.stream()
					.max(Comparator.comparingInt(TakenAccOrDeAccDiff::getDiffAmount));
			if (takenAccOrDeaccDiff.isPresent()) {
				statData.setMetricValue(Math.abs(takenAccOrDeaccDiff.get().getDiffAmount()));
			}
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.AOSMIN);
			statData.setMetricDescription("Minimum amount of slide during the game");
			break;
		case BCINST:
			takenAccOrDeaccsDiff = accOrDeaccRepo.getSimulationWiseSnakeOrLadderTaken(statsReq.getSimulationId(), 0);
			takenAccOrDeaccDiff = takenAccOrDeaccsDiff.stream()
					.max(Comparator.comparingInt(TakenAccOrDeAccDiff::getDiffAmount));
			if (takenAccOrDeaccDiff.isPresent()) {
				statData.setMetricValue(takenAccOrDeaccDiff.get().getDiffAmount());
			}
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.BCINST);
			statData.setMetricDescription("Biggest climb in single turn");
			break;
		case BSINST:
			takenAccOrDeaccsDiff = accOrDeaccRepo.getSimulationWiseSnakeOrLadderTaken(statsReq.getSimulationId(), 1);
			takenAccOrDeaccDiff = takenAccOrDeaccsDiff.stream()
					.min(Comparator.comparingInt(TakenAccOrDeAccDiff::getDiffAmount));
			if (takenAccOrDeaccDiff.isPresent()) {
				statData.setMetricValue(Math.abs(takenAccOrDeaccDiff.get().getDiffAmount()));
			}
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.BSINST);
			statData.setMetricDescription("Biggest slide in single turn");
			break;
		case LKRAVG:
			break;
		case LKRMAX:
			break;
		case LKRMIN:
			break;
		case LONGTN:
			List<Roll> rolls = rollRepository.getLongestTurnRoll(statsReq.getSimulationId());
			statData.setMetricValue((rolls.get(0).getNoOfRolls() - 1) * 6 + rolls.get(0).getFaceValue());
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.LONGTN);
			statData.setMetricDescription("Longest turn");
			break;
		case RTWAVG:
			List<RollStats> rollStats = rollRepository.getGameWiseRolls(statsReq.getSimulationId());
			int totalRolls = rollStats.stream().mapToInt(RollStats::getRollCount).reduce(0, Integer::sum);
			statData.setMetricValue((double) totalRolls / rollStats.size());
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.RTWAVG);
			statData.setMetricDescription("Average number of rolls needed to win");
			break;
		case RTWMAX:
			rollStats = rollRepository.getGameWiseRolls(statsReq.getSimulationId());
			Optional<RollStats> optRollStats = rollStats.stream().max(Comparator.comparingInt(RollStats::getRollCount));
			if (optRollStats.isPresent()) {
				statData.setMetricValue(optRollStats.get().getRollCount());
			}
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.RTWAVG);
			statData.setMetricDescription("Maximum number of rolls needed to win");
			break;
		case RTWMIN:
			rollStats = rollRepository.getGameWiseRolls(statsReq.getSimulationId());
			optRollStats = rollStats.stream().min(Comparator.comparingInt(RollStats::getRollCount));
			if (optRollStats.isPresent()) {
				statData.setMetricValue(optRollStats.get().getRollCount());
			}
			statData.setUnit(Unit.NUMBER);
			statData.setMetricName(Stats.RTWAVG);
			statData.setMetricDescription("Minimum number of rolls needed to win");
			break;
		case ULRAVG:
			break;
		case ULRMAX:
			break;
		case ULRMIN:
			break;
		default:
			statData.setMetricValue(0);
			statData.setUnit(Unit.NUMBER);
			statData.setMetricDescription(null);
		}
		return statData;
	}

}
