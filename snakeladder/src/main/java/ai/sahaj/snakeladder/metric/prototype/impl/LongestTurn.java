package ai.sahaj.snakeladder.metric.prototype.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.constants.Unit;
import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.dto.backend.MetricRequest;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.metric.prototype.MetricGenerator;
import ai.sahaj.snakeladder.services.RollService;

@Component("longestTurn")
public class LongestTurn implements MetricGenerator<StatsData<Integer>> {

	@Autowired
	private RollService rollService;

	@Override
	public MetricResponse<StatsData<Integer>> generateMetric(String simulationId, MetricRequest statsReq) {
		StatsData<Integer> statData = new StatsData<>();
		MetricResponse<StatsData<Integer>> metricResponse = new MetricResponse<>();

		List<Roll> rolls = rollService.getLongestTurnRoll(simulationId);
		if (CollectionUtils.isEmpty(rolls)) {
		}
		statData.setMetricValue((rolls.get(0).getNoOfRolls() - 1) * 6 + rolls.get(0).getFaceValue());
		statData.setUnit(Unit.NUMBER);
		metricResponse.setData(statData);
		metricResponse.setMetricName(Stats.LONGTN);
		metricResponse.setMetricDescription("Longest turn");
		return metricResponse;
	}

}
