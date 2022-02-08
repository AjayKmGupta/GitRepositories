package ai.sahaj.snakeladder.controllers.test;

import java.util.ArrayList;
import java.util.List;

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

import ai.sahaj.snakeladder.controllers.PositionController;
import ai.sahaj.snakeladder.controllers.test.AccOrDeacceleratorControllerTest.ContextConfig;
import ai.sahaj.snakeladder.dto.frontend.PositionDto;
import ai.sahaj.snakeladder.services.PositionService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContextConfig.class, loader = AnnotationConfigContextLoader.class)
class PositionControllerTest {

	@InjectMocks
	private PositionController positionController;

	@Mock
	private PositionService positionService;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;
	private AutoCloseable closeable;

	@BeforeEach
	public void init() {
		closeable = MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.positionController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@AfterEach
	public void close() throws Exception {
		closeable.close();
	}

	@Test
	void testCreatePositions() throws JsonProcessingException, Exception {
		Mockito.doNothing().when(positionService).createPositions();
		mockMvc.perform(MockMvcRequestBuilders.post("/position/add").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testRemovePositions() throws JsonProcessingException, Exception {
		Mockito.doNothing().when(positionService).removeAllPositions();
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/position/remove-all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetPositions() throws JsonProcessingException, Exception {
		List<PositionDto> players = new ArrayList<>();
		Mockito.when(positionService.getPositionsDto()).thenReturn(players);
		mockMvc.perform(MockMvcRequestBuilders.get("/position/get").content(mapper.writeValueAsString(players))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	public static class ContextConfig {
		@Bean
		public ObjectMapper objectMapper() {
			ObjectMapper mapper = new ObjectMapper();
			return mapper;
		}
	}

}
