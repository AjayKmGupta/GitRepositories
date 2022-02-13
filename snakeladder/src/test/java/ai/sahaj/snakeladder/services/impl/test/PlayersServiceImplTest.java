package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.dto.frontend.AddPlayerDto;
import ai.sahaj.snakeladder.dto.frontend.PlayerDto;
import ai.sahaj.snakeladder.entity.Player;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.repositories.PlayersRepository;
import ai.sahaj.snakeladder.services.impl.PlayersServiceImpl;

@ExtendWith(SpringExtension.class)
class PlayersServiceImplTest {

	@InjectMocks
	private PlayersServiceImpl playersServiceImpl;
	@Mock
	private PlayersRepository playerRepo;

	@Test
	void testAddPlayer() {
		Player player = new Player();
		AddPlayerDto playerDto = new AddPlayerDto();
		playerDto.setName("abc");
		playerDto.setEmailId("abc@xyz.ai");
		Mockito.when(playerRepo.save(player)).thenReturn(player);
		playersServiceImpl.addPlayer(playerDto);
		Mockito.verify(playerRepo).save(Mockito.any(Player.class));
	}

	@Test
	void testUpdatePlayer() {
		Player player = new Player();
		AddPlayerDto playerDto = new AddPlayerDto();
		playerDto.setName("abc");
		playerDto.setEmailId("abc@xyz.ai");
		Mockito.when(playerRepo.findById(123)).thenReturn(Optional.of(player));
		playersServiceImpl.updatePlayer("123", playerDto);
		Mockito.verify(playerRepo).save(player);
	}

	@Test
	void testUpdatePlayerWithEmpty() {
		AddPlayerDto playerDto = new AddPlayerDto();
		playerDto.setName("abc");
		playerDto.setEmailId("abc@xyz.ai");
		Mockito.when(playerRepo.findById(123)).thenReturn(Optional.empty());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			playersServiceImpl.updatePlayer("123", playerDto);
		});
		assertEquals("Player doesn't exists for given id", thrown.getMessage());
	}

	@Test
	void testGetPlayerDto() {
		Player player = new Player();
		player.setId(123l);
		player.setName("abc");
		player.setEmailId("abc@xyz.ai");
		Mockito.when(playerRepo.findById(123)).thenReturn(Optional.of(player));
		PlayerDto playersDto = playersServiceImpl.getPlayer("123");
		assertEquals(playersDto.getId(), player.getId());
	}

	@Test
	void testGetPlayerDtoWithMissingId() {
		Mockito.when(playerRepo.findById(123)).thenReturn(Optional.empty());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			playersServiceImpl.getPlayer("123");
		});
		assertEquals("Player doesn't exists for given id", thrown.getMessage());
	}

	@Test
	void testGetPlayer() {
		List<Player> players = new ArrayList<>();
		Player player = new Player();
		player.setId(123l);
		player.setName("abc");
		player.setEmailId("abc@xyz.ai");
		players.add(player);
		Mockito.when(playerRepo.findAll()).thenReturn(players);

		Set<PlayerDto> playersDto = playersServiceImpl.getPlayer();
		List<PlayerDto> playersList = new ArrayList<>(playersDto);
		assertEquals(playersList.get(0).getId(), player.getId());
	}

	@Test
	void testGetPlayerByIds() {
		List<Player> players = new ArrayList<>();
		Player player = new Player();
		player.setId(123l);
		player.setName("abc");
		player.setEmailId("abc@xyz.ai");
		players.add(player);
		List<Long> playerIds = new ArrayList<>();
		playerIds.add(123l);
		Set<Long> playerIdsArg = new HashSet<>();
		playerIdsArg.add(123l);
		Mockito.when(playerRepo.findByIds(playerIds)).thenReturn(players);

		List<Player> actPlayers = playersServiceImpl.getPlayer(playerIdsArg);
		assertEquals(players.get(0).getId(), actPlayers.get(0).getId());
	}

	@Test
	void testGetPlayerDtosFromPlayer() {
		List<Player> players = new ArrayList<>();
		Player player = new Player();
		player.setId(123l);
		player.setName("abc");
		player.setEmailId("abc@xyz.ai");
		players.add(player);

		Set<PlayerDto> actPlayers = playersServiceImpl.getPlayerDtosFromPlayer(players);
		List<PlayerDto> actPlayersList = new ArrayList<>(actPlayers);
		assertEquals(players.get(0).getId(), actPlayersList.get(0).getId());
	}

}
