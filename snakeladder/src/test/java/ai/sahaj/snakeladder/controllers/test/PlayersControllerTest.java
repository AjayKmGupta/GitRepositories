package ai.sahaj.snakeladder.controllers.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.sahaj.snakeladder.controllers.PlayersController;
import ai.sahaj.snakeladder.controllers.test.AccOrDeacceleratorControllerTest.ContextConfig;
import ai.sahaj.snakeladder.dto.frontend.AddPlayerDto;
import ai.sahaj.snakeladder.dto.frontend.PlayerDto;
import ai.sahaj.snakeladder.services.PlayersService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContextConfig.class, loader = AnnotationConfigContextLoader.class)
class PlayersControllerTest {

	@InjectMocks
	private PlayersController playersController;

	@Mock
	private PlayersService playersService;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;
	private AutoCloseable closeable;

	@BeforeEach
	public void init() {
		closeable = MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.playersController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@AfterEach
	public void close() throws Exception {
		closeable.close();
	}

	@Test
	void testAddPlayer() throws JsonProcessingException, Exception {
		AddPlayerDto playerDto = new AddPlayerDto();
		Mockito.doNothing().when(playersService).addPlayer(playerDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/players/add").content(mapper.writeValueAsString(playerDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testUpdatePlayer() throws JsonProcessingException, Exception {
		AddPlayerDto playerDto = new AddPlayerDto();
		Mockito.doNothing().when(playersService).updatePlayer("123", playerDto);
		mockMvc.perform(MockMvcRequestBuilders.put("/players/update/{id}", "123")
				.content(mapper.writeValueAsString(playerDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetPlayer() throws JsonProcessingException, Exception {
		PlayerDto player = new PlayerDto();
		Mockito.when(playersService.getPlayer("123")).thenReturn(player);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/players/get/{id}", "123").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetAllPlayers() throws JsonProcessingException, Exception {
		Set<PlayerDto> players = new HashSet<>();
		Mockito.when(playersService.getPlayer()).thenReturn(players);
		mockMvc.perform(MockMvcRequestBuilders.get("/players/get").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	public static class ContextConfig {
		@Bean
		public ObjectMapper objectMapper() {
			ObjectMapper mapper = new ObjectMapper();
			return mapper;
		}
	}

}
