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

import ai.sahaj.snakeladder.controllers.SimulationController;
import ai.sahaj.snakeladder.controllers.test.AccOrDeacceleratorControllerTest.ContextConfig;
import ai.sahaj.snakeladder.dto.frontend.AddSimulationDto;
import ai.sahaj.snakeladder.dto.frontend.SimulationDto;
import ai.sahaj.snakeladder.services.SimulationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContextConfig.class, loader = AnnotationConfigContextLoader.class)
class SimulationControllerTest {

	@InjectMocks
	private SimulationController simulationController;

	@Mock
	private SimulationService simulationService;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;
	private AutoCloseable closeable;

	@BeforeEach
	public void init() {
		closeable = MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.simulationController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@AfterEach
	public void close() throws Exception {
		closeable.close();
	}

	@Test
	void testAddSimulation() throws JsonProcessingException, Exception {
		AddSimulationDto simulationDto = new AddSimulationDto();
		Mockito.doNothing().when(simulationService).addSimulation(simulationDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/simulation/add").content(mapper.writeValueAsString(simulationDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testUpdateSimulation() throws JsonProcessingException, Exception {
		AddSimulationDto simulationDto = new AddSimulationDto();
		Mockito.doNothing().when(simulationService).updateSimulation("123", simulationDto);
		mockMvc.perform(MockMvcRequestBuilders.put("/simulation/update/{id}", "123")
				.content(mapper.writeValueAsString(simulationDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetSimulation() throws JsonProcessingException, Exception {
		SimulationDto simulationDto = SimulationDto.builder().build();
		Mockito.when(simulationService.getSimulation("123")).thenReturn(simulationDto);
		mockMvc.perform(MockMvcRequestBuilders.get("/simulation/get/{simulation-id}", "123")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetAllSimulations() throws JsonProcessingException, Exception {
		Set<SimulationDto> simulations = new HashSet<>();
		Mockito.when(simulationService.getSimulation()).thenReturn(simulations);
		mockMvc.perform(MockMvcRequestBuilders.get("/simulation/get").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testStartSimulation() throws JsonProcessingException, Exception {
		String message = "Simulation started";
		Mockito.when(simulationService.startSimulation("123")).thenReturn(message);
		mockMvc.perform(MockMvcRequestBuilders.post("/simulation/start/{simulation-id}", "123")
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
