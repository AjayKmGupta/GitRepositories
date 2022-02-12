package ai.sahaj.snakeladder.dto.backend;

import ai.sahaj.snakeladder.constants.Unit;
import lombok.Data;

@Data
public class StatsData<T> {

	private T metricValue;
	private Unit unit;
}
