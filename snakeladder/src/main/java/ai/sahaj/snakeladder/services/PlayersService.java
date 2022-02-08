package ai.sahaj.snakeladder.services;

import java.util.List;
import java.util.Set;

import ai.sahaj.snakeladder.dto.frontend.AddPlayerDto;
import ai.sahaj.snakeladder.dto.frontend.PlayerDto;
import ai.sahaj.snakeladder.entity.Player;

public interface PlayersService {

	void addPlayer(AddPlayerDto player);

	void updatePlayer(String playerId, AddPlayerDto player);

	PlayerDto getPlayer(String playerId);

	List<Player> getPlayer(Set<Long> playersId);

	Set<PlayerDto> getPlayer();

	Set<PlayerDto> getPlayerDtosFromPlayer(List<Player> players);
}
