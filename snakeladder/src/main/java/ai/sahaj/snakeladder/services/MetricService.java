package ai.sahaj.snakeladder.services;

import java.util.List;

import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.dto.backend.MetricRequest;

public interface MetricService {

	List<MetricResponse<?>> getMetrics(String simulationId, List<MetricRequest> statsRequests);
}
