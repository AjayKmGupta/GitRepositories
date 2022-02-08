package ai.sahaj.snakeladder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.sahaj.snakeladder.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

	Optional<Position> findByNumber(int boardStartPos);

}
