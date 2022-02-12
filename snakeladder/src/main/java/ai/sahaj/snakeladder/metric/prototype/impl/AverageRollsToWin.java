package ai.sahaj.snakeladder.metric.prototype.impl;

import java.util.List;

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

@Component("avgRollsToWin")
public class AverageRollsToWin implements MetricGenerator<StatsData<Double>> {

	@Autowired
	private RollService rollService;

	@Override
	public MetricResponse<StatsData<Double>> generateMetric(String simulationId, MetricRequest statsReq) {
		StatsData<Double> statData = new StatsData<>();
		MetricResponse<StatsData<Double>> metricResponse = new MetricResponse<>();

		List<RollStats> rollStats = rollService.getGameWiseRolls(simulationId);
		int totalRolls = rollStats.stream().mapToInt(RollStats::getRollCount).reduce(0, Integer::sum);
		statData.setMetricValue((double) totalRolls / rollStats.size());
		statData.setUnit(Unit.NUMBER);
		metricResponse.setData(statData);
		metricResponse.setMetricName(Stats.RTWAVG);
		metricResponse.setMetricDescription("Average number of rolls needed to win");

		return metricResponse;
	}

}
