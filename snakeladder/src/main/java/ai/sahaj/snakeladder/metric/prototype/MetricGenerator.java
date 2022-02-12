package ai.sahaj.snakeladder.metric.prototype;

import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.dto.backend.MetricRequest;

public interface MetricGenerator<T> {

	MetricResponse<T> generateMetric(String simulationId, MetricRequest statsReq);
}
