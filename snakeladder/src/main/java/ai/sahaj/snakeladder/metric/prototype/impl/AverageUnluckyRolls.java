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

@Component("avgUnluckyRolls")
public class AverageUnluckyRolls implements MetricGenerator<StatsData<Double>> {

	@Autowired
	private RollService rollService;

	@Override
	public MetricResponse<StatsData<Double>> generateMetric(String simulationId, MetricRequest statsReq) {

		StatsData<Double> statData = new StatsData<>();
		MetricResponse<StatsData<Double>> metricResponse = new MetricResponse<>();

		List<Roll> luckyRolls = rollService.getLuckyOrUnluckyRolls(simulationId, RollType.UNLUCKY);
		Map<Game, List<Roll>> gameToLuckyRollsMap = luckyRolls.stream()
				.collect(Collectors.groupingBy(Roll::getGame, Collectors.toCollection(ArrayList::new)));
		int total = 0;
		for (Entry<Game, List<Roll>> entry : gameToLuckyRollsMap.entrySet()) {
			total += entry.getValue().size();
		}
		statData.setMetricValue((double) total / gameToLuckyRollsMap.size());
		statData.setUnit(Unit.NUMBER);
		metricResponse.setData(statData);
		metricResponse.setMetricName(Stats.ULRAVG);
		metricResponse.setMetricDescription("Average unlucky rolls during the simulation");
		return metricResponse;
	}

}
