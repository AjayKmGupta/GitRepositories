package ai.sahaj.snakeladder.metric.prototype.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.constants.Unit;
import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.dto.backend.MetricRequest;
import ai.sahaj.snakeladder.dto.backend.TakenAccOrDeAccDiff;
import ai.sahaj.snakeladder.metric.prototype.MetricGenerator;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;

@Component("minClimbAmt")
public class MinimumAmountOfClimb implements MetricGenerator<StatsData<Integer>> {

	@Autowired
	private AccOrDeacceleratorService accOrDeaccService;

	@Override
	public MetricResponse<StatsData<Integer>> generateMetric(String simulationId, MetricRequest statsReq) {

		StatsData<Integer> statData = new StatsData<>();
		MetricResponse<StatsData<Integer>> metricResponse = new MetricResponse<>();

		List<TakenAccOrDeAccDiff> takenAccOrDeaccsDiff = accOrDeaccService
				.getSimulationWiseSnakeOrLadderTaken(simulationId, AccOrDeacceleratorType.LADDER);
		Optional<TakenAccOrDeAccDiff> takenAccOrDeaccDiff = takenAccOrDeaccsDiff.stream()
				.min(Comparator.comparingInt(TakenAccOrDeAccDiff::getDiffAmount));
		if (takenAccOrDeaccDiff.isPresent()) {
			statData.setMetricValue(takenAccOrDeaccDiff.get().getDiffAmount());
		}
		statData.setUnit(Unit.NUMBER);
		metricResponse.setData(statData);
		metricResponse.setMetricName(Stats.AOCMIN);
		metricResponse.setMetricDescription("Minimum amount of climb during the simulation");
		return metricResponse;
	}

}
