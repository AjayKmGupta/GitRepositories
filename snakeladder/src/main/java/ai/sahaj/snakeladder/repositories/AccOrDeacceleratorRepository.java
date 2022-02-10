package ai.sahaj.snakeladder.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ai.sahaj.snakeladder.dto.backend.TakenAccOrDeAccDiff;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Position;

@Repository
public interface AccOrDeacceleratorRepository extends JpaRepository<AccOrDeaccelerator, Integer> {

	List<AccOrDeaccelerator> findByBoard(Board board);

	Optional<AccOrDeaccelerator> findByStartPos(Position position);

	@Query(value = "select (fipo.number - p.number) as diffAmount from acc_or_deaccelerator as aod inner join game_acc_or_deacclerator as gaod on aod.id = gaod.acc_or_deacc_id inner join game as g on gaod.game_id = g.id inner join position p on p.id = aod.start_pos_id inner join position fipo on fipo.id = aod.final_position_id where g.simulation_id=:simulationId and aod.acc_or_de_acc_type=:accOrDeaccType", nativeQuery = true)
	List<TakenAccOrDeAccDiff> getSimulationWiseSnakeOrLadderTaken(String simulationId, int accOrDeaccType);

}
