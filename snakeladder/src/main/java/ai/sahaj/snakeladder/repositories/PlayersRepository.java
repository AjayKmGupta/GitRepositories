package ai.sahaj.snakeladder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ai.sahaj.snakeladder.entity.Player;

@Repository
public interface PlayersRepository extends JpaRepository<Player, Integer> {

	@Query( "select p from Player p where id in :ids" )
	List<Player> findByIds(@Param("ids") List<Long> playersId);

}
