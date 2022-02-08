package ai.sahaj.snakeladder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.sahaj.snakeladder.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
