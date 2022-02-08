package ai.sahaj.snakeladder.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Position;

@Repository
public interface AccOrDeacceleratorRepository extends JpaRepository<AccOrDeaccelerator, Integer> {

	List<AccOrDeaccelerator> findByBoard(Board board);

	Optional<AccOrDeaccelerator> findByStartPos(Position position);

}
