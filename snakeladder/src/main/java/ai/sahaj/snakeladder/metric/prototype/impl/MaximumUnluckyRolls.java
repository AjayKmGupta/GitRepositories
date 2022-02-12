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

@Component("maxUnluckyRolls")
public class MaximumUnluckyRolls implements MetricGenerator<StatsData<Integer>> {

	@Autowired
	private RollService rollService;

	@Override
	public MetricResponse<StatsData<Integer>> generateMetric(String simulationId, MetricRequest statsReq) {

		StatsData<Integer> statData = new StatsData<>();
		MetricResponse<StatsData<Integer>> metricResponse = new MetricResponse<>();

		List<Roll> luckyRolls = rollService.getLuckyOrUnluckyRolls(simulationId, RollType.UNLUCKY);
		Map<Game, List<Roll>> gameToLuckyRollsMap = luckyRolls.stream()
				.collect(Collectors.groupingBy(Roll::getGame, Collectors.toCollection(ArrayList::new)));
		int maxUnLukyRolls = 0;
		for (Entry<Game, List<Roll>> entry : gameToLuckyRollsMap.entrySet()) {
			if (maxUnLukyRolls < entry.getValue().size()) {
				maxUnLukyRolls = entry.getValue().size();
			}
		}
		statData.setMetricValue(maxUnLukyRolls);
		statData.setUnit(Unit.NUMBER);
		metricResponse.setData(statData);
		metricResponse.setMetricName(Stats.ULRMAX);
		metricResponse.setMetricDescription("Maximum unlucky rolls during the simulation");
		return metricResponse;
	}

}
