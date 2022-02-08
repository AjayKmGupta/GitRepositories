package ai.sahaj.snakeladder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.sahaj.snakeladder.entity.Roll;

@Repository
public interface RollRepository extends JpaRepository<Roll, String> {

}
