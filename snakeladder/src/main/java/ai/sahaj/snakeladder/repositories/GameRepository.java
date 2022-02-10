package ai.sahaj.snakeladder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.sahaj.snakeladder.entity.Game;

public interface GameRepository extends JpaRepository<Game, String> {

}
