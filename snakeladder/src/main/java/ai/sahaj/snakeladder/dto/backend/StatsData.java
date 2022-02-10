package ai.sahaj.snakeladder.dto.backend;

import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.constants.Unit;
import lombok.Data;

@Data
public class StatsData {

	private Stats metricName;
	private String metricDescription;
	private double metricValue;
	private Unit unit;
}
