package ai.sahaj.snakeladder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ai.sahaj.snakeladder.dto.backend.RollStats;
import ai.sahaj.snakeladder.entity.Roll;

@Repository
public interface RollRepository extends JpaRepository<Roll, String> {

	@Query(value = "select g.simulation_id as simulationId, r.game_id as gameId, count(r.game_id) as rollCount from roll as r inner join game as g on r.game_id = g.id group by r.game_id having g.simulation_id=:simulationId", nativeQuery = true)
	List<RollStats> getGameWiseRolls(String simulationId);

	@Query(value = "select * from roll as r inner join game g on r.game_id = g.id where ((r.no_of_rolls - 1) * 6 + r.face_value) = (select max(((ro.no_of_rolls - 1) * 6 + ro.face_value )) from roll ro) and g.simulation_id=:simulationId", nativeQuery = true)
	List<Roll> getLongestTurnRoll(String simulationId);

}
