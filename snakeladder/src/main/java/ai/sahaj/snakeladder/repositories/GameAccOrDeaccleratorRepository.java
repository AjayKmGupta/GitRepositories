package ai.sahaj.snakeladder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.sahaj.snakeladder.entity.GameAccOrDeacclerator;

@Repository
public interface GameAccOrDeaccleratorRepository extends JpaRepository<GameAccOrDeacclerator, String> {

}
