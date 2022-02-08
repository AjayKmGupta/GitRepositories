package ai.sahaj.snakeladder.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.sahaj.snakeladder.dto.frontend.AddPlayerDto;
import ai.sahaj.snakeladder.dto.frontend.PlayerDto;
import ai.sahaj.snakeladder.entity.Player;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.repositories.PlayersRepository;
import ai.sahaj.snakeladder.services.PlayersService;

@Service
public class PlayersServiceImpl implements PlayersService {

	@Autowired
	private PlayersRepository playerRepo;

	@Override
	public void addPlayer(AddPlayerDto playerDto) {

		Player player = new Player();
		BeanUtils.copyProperties(playerDto, player);
		playerRepo.save(player);
	}

	@Override
	public void updatePlayer(String playerId, AddPlayerDto playerDto) {
		Optional<Player> optPlayer = playerRepo.findById(Integer.parseInt(playerId));
		if (optPlayer.isEmpty()) {
			throw new ResourceNotFoundException("Player doesn't exists for given id");
		}
		Player player = optPlayer.get();
		BeanUtils.copyProperties(playerDto, player);
		playerRepo.save(player);
	}

	@Override
	public PlayerDto getPlayer(String playerId) {
		Optional<Player> optPlayer = playerRepo.findById(Integer.parseInt(playerId));
		if (optPlayer.isEmpty()) {
			throw new ResourceNotFoundException("Player doesn't exists for given id");
		}
		PlayerDto playerDto = new PlayerDto();
		BeanUtils.copyProperties(optPlayer.get(), playerDto);
		return playerDto;
	}

	@Override
	public Set<PlayerDto> getPlayer() {
		return getPlayerDtosFromPlayer(playerRepo.findAll());
	}

	@Override
	public List<Player> getPlayer(Set<Long> playersId) {

		return playerRepo.findByIds(new ArrayList<>(playersId));
	}

	@Override
	public Set<PlayerDto> getPlayerDtosFromPlayer(List<Player> players) {
		return players.stream().map(p -> new PlayerDto(p.getId(), p.getName(), p.getEmailId()))
				.collect(Collectors.toSet());
	}

}
