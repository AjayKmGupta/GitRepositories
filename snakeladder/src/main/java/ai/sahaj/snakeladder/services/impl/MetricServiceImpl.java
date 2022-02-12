package ai.sahaj.snakeladder.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.dto.backend.MetricRequest;
import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.services.MetricService;
import ai.sahaj.snakeladder.services.SimulationService;
import ai.sahaj.snakeladder.util.MetricGeneratorFactory;

@Service
public class MetricServiceImpl implements MetricService {

	@Autowired
	private SimulationService simulationService;
	@Autowired
	private MetricGeneratorFactory metricGenFactory;

	@Override
	public List<MetricResponse<?>> getMetrics(String simulationId, List<MetricRequest> metricRequests) {

		if (simulationService.getSimulationEntity(simulationId).isEmpty()) {
			throw new BadRequestException("Simulation doesn't exist for given id");
		}
		List<MetricResponse<?>> statsDatas = new ArrayList<>();
		for (MetricRequest metricReq : metricRequests) {
			statsDatas.add(metricGenFactory.generateMetricGenerator(metricReq.getMetricName())
					.generateMetric(simulationId, metricReq));
		}
		return statsDatas;
	}

}
