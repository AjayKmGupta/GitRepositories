package ai.sahaj.snakeladder.metric.prototype.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.constants.Unit;
import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.dto.backend.RollStats;
import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.dto.backend.MetricRequest;
import ai.sahaj.snakeladder.metric.prototype.MetricGenerator;
import ai.sahaj.snakeladder.services.RollService;

@Component("maxRollsToWin")
public class MaximumRollsToWin implements MetricGenerator<StatsData<Integer>> {

	@Autowired
	private RollService rollService;

	@Override
	public MetricResponse<StatsData<Integer>> generateMetric(String simulationId,MetricRequest statsReq) {
		StatsData<Integer> statData = new StatsData<>();
		MetricResponse<StatsData<Integer>> metricResponse = new MetricResponse<>();

		List<RollStats> rollStats = rollService.getGameWiseRolls(simulationId);
		Optional<RollStats> optRollStats = rollStats.stream().max(Comparator.comparingInt(RollStats::getRollCount));
		if (optRollStats.isPresent()) {
			statData.setMetricValue(optRollStats.get().getRollCount());
		}
		statData.setUnit(Unit.NUMBER);
		metricResponse.setData(statData);
		metricResponse.setMetricName(Stats.RTWMAX);
		metricResponse.setMetricDescription("Maximum number of rolls needed to win");

		return metricResponse;
	}

}
