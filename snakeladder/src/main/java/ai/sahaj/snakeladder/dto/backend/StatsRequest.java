package ai.sahaj.snakeladder.dto.backend;

import ai.sahaj.snakeladder.constants.Stats;
import lombok.Data;

@Data
public class StatsRequest {

	private Stats metricName;
	private String simulationId;
}
