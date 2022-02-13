package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.dto.backend.MetricRequest;
import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.entity.Simulation;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.metric.prototype.MetricGenerator;
import ai.sahaj.snakeladder.services.SimulationService;
import ai.sahaj.snakeladder.services.impl.MetricServiceImpl;
import ai.sahaj.snakeladder.util.MetricGeneratorFactory;

@ExtendWith(SpringExtension.class)
class MetricServiceImplTest {

	@InjectMocks
	private MetricServiceImpl metricServiceImpl;
	@Mock
	private SimulationService simulationService;
	@Mock
	private MetricGeneratorFactory metricGenFactory;
	@Mock
	private MetricGenerator<? extends StatsData<? extends Number>> metricGen;

	@Test
	void testGetMetrics() {
		Simulation simulation = new Simulation();
		List<MetricRequest> metricRequests = new ArrayList<>();
		MetricResponse<?> mResponse = new MetricResponse<>();
		mResponse.setMetricName(Stats.AOCAVG);
		MetricRequest mReq = new MetricRequest();
		mReq.setMetricName(Stats.AOCAVG);
		metricRequests.add(mReq);
		Mockito.when(simulationService.getSimulationEntity("123")).thenReturn(Optional.of(simulation));
		Mockito.doReturn(metricGen).when(metricGenFactory).generateMetricGenerator(Stats.AOCAVG);
		Mockito.doReturn(mResponse).when(metricGen).generateMetric("123", mReq);
		List<MetricResponse<?>> metricResponses = metricServiceImpl.getMetrics("123", metricRequests);
		assertEquals(Stats.AOCAVG, metricResponses.get(0).getMetricName());
	}

	@Test
	void testGetMetricsWithoutSimulation() {
		// OptionaSimulation simulation = new Simulation();
		List<MetricRequest> metricRequests = new ArrayList<>();
		MetricResponse<?> mResponse = new MetricResponse<>();
		mResponse.setMetricName(Stats.AOCAVG);
		MetricRequest mReq = new MetricRequest();
		mReq.setMetricName(Stats.AOCAVG);
		metricRequests.add(mReq);
		Mockito.when(simulationService.getSimulationEntity("123")).thenReturn(Optional.empty());
		Mockito.doReturn(metricGen).when(metricGenFactory).generateMetricGenerator(Stats.AOCAVG);
		Mockito.doReturn(mResponse).when(metricGen).generateMetric("123", mReq);
		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			metricServiceImpl.getMetrics("123", metricRequests);
		});
		assertEquals("Simulation doesn't exist for given id", thrown.getMessage());
	}

}
