package ai.sahaj.snakeladder.dto.backend;

import ai.sahaj.snakeladder.constants.Stats;
import lombok.Data;

@Data
public class MetricResponse<T> {

	private Stats metricName;
	private String metricDescription;
	private T data;
}
