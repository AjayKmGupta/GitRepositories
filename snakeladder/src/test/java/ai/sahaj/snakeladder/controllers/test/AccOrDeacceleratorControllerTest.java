package ai.sahaj.snakeladder.controllers.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import ai.sahaj.snakeladder.controllers.AccOrDeacceleratorController;
import ai.sahaj.snakeladder.controllers.test.AccOrDeacceleratorControllerTest.ContextConfig;
import ai.sahaj.snakeladder.dto.frontend.AccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.AddAccOrDeacceleratorDto;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContextConfig.class, loader = AnnotationConfigContextLoader.class)
class AccOrDeacceleratorControllerTest {

	@InjectMocks
	private AccOrDeacceleratorController accOrDeaccController;

	@Mock
	private AccOrDeacceleratorService accOrDeaccService;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;
	private AutoCloseable closeable;

	@BeforeEach
	public void init() {
		closeable = MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.accOrDeaccController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@AfterEach
	public void close() throws Exception {
		closeable.close();
	}

	@Test
	void testAddAccOrDeaccelerator() throws JsonProcessingException, Exception {
		List<AddAccOrDeacceleratorDto> accOrDeaccs = new ArrayList<>();
		Mockito.doNothing().when(accOrDeaccService).addAccOrDeaccelerator(accOrDeaccs);
		mockMvc.perform(MockMvcRequestBuilders.post("/acc-deacc/add").content(mapper.writeValueAsString(accOrDeaccs))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testUpdateAccOrDeaccelerator() throws JsonProcessingException, Exception {
		AddAccOrDeacceleratorDto accOrDeacc = new AddAccOrDeacceleratorDto();
		Mockito.doNothing().when(accOrDeaccService).updateAccOrDeaccelerator("123", accOrDeacc);
		mockMvc.perform(MockMvcRequestBuilders.put("/acc-deacc/update/{id}", "123")
				.content(mapper.writeValueAsString(accOrDeacc)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetAccOrDeaccelerator() throws JsonProcessingException, Exception {
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		Mockito.when(accOrDeaccService.getAccOrDeaccelerator("123")).thenReturn(accOrDeaccs);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/acc-deacc/get/{id}", "123").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetAllAccOrDeaccelerators() throws JsonProcessingException, Exception {
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		Mockito.when(accOrDeaccService.getAccOrDeaccelerator()).thenReturn(accOrDeaccs);
		mockMvc.perform(MockMvcRequestBuilders.get("/acc-deacc/get").contentType(MediaType.APPLICATION_JSON_VALUE))
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
	