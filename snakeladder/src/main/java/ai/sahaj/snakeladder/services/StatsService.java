package ai.sahaj.snakeladder.services;

import java.util.List;

import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.dto.backend.StatsRequest;

public interface StatsService {

	List<StatsData> getStats(List<StatsRequest> statsRequests);
}
