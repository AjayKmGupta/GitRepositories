package ai.sahaj.snakeladder.metric.prototype.impl;

import java.util.List;

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

@Component("avgSlideAmt")
public class AverageAmountOfSlide implements MetricGenerator<StatsData<Double>> {

	@Autowired
	private AccOrDeacceleratorService accOrDeaccService;

	@Override
	public MetricResponse<StatsData<Double>> generateMetric(String simulationId, MetricRequest statsReq) {

		StatsData<Double> statData = new StatsData<>();
		MetricResponse<StatsData<Double>> metricResponse = new MetricResponse<>();

		List<TakenAccOrDeAccDiff> takenAccOrDeaccsDiff = accOrDeaccService
				.getSimulationWiseSnakeOrLadderTaken(simulationId, AccOrDeacceleratorType.SNAKE);
		Integer totalSnakeSum = takenAccOrDeaccsDiff.stream().mapToInt(TakenAccOrDeAccDiff::getDiffAmount).reduce(0,
				Integer::sum);
		statData.setMetricValue((double) Math.abs(totalSnakeSum) / takenAccOrDeaccsDiff.size());
		statData.setUnit(Unit.NUMBER);
		metricResponse.setData(statData);
		metricResponse.setMetricName(Stats.AOSAVG);
		metricResponse.setMetricDescription("Average amount of slide during the simulation");
		return metricResponse;
	}

}
