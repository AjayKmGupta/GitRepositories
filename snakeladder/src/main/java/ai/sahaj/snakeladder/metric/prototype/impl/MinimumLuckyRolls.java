package ai.sahaj.snakeladder.metric.prototype.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ai.sahaj.snakeladder.constants.RollType;
import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.constants.Unit;
import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.dto.backend.MetricRequest;
import ai.sahaj.snakeladder.entity.Game;
import ai.sahaj.snakeladder.entity.Roll;
import ai.sahaj.snakeladder.metric.prototype.MetricGenerator;
import ai.sahaj.snakeladder.services.RollService;

@Component("minLuckyRolls")
public class MinimumLuckyRolls implements MetricGenerator<StatsData<Integer>> {

	@Autowired
	private RollService rollService;

	@Override
	public MetricResponse<StatsData<Integer>> generateMetric(String simulationId, MetricRequest statsReq) {

		StatsData<Integer> statData = new StatsData<>();
		MetricResponse<StatsData<Integer>> metricResponse = new MetricResponse<>();

		List<Roll> luckyRolls = rollService.getLuckyOrUnluckyRolls(simulationId, RollType.LUCKY);
		Map<Game, List<Roll>> gameToLuckyRollsMap = luckyRolls.stream()
				.collect(Collectors.groupingBy(Roll::getGame, Collectors.toCollection(ArrayList::new)));
		int minLukyRolls = Integer.MAX_VALUE;
		for (Entry<Game, List<Roll>> entry : gameToLuckyRollsMap.entrySet()) {
			if (minLukyRolls > entry.getValue().size()) {
				minLukyRolls = entry.getValue().size();
			}
		}
		statData.setMetricValue(minLukyRolls);
		statData.setUnit(Unit.NUMBER);
		metricResponse.setData(statData);
		metricResponse.setMetricName(Stats.ULRMIN);
		metricResponse.setMetricDescription("Minimum lucky rolls during the simulation");
		return metricResponse;
	}

}
