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

import ai.sahaj.snakeladder.controllers.BoardController;
import ai.sahaj.snakeladder.controllers.test.AccOrDeacceleratorControllerTest.ContextConfig;
import ai.sahaj.snakeladder.dto.frontend.AddBoardDto;
import ai.sahaj.snakeladder.dto.frontend.BoardDto;
import ai.sahaj.snakeladder.services.BoardService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContextConfig.class, loader = AnnotationConfigContextLoader.class)
class BoardControllerTest {

	@InjectMocks
	private BoardController boardController;

	@Mock
	private BoardService boardService;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;
	private AutoCloseable closeable;

	@BeforeEach
	public void init() {
		closeable = MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.boardController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@AfterEach
	public void close() throws Exception {
		closeable.close();
	}

	@Test
	void testAddBoard() throws JsonProcessingException, Exception {
		AddBoardDto boardDto = new AddBoardDto();
		Mockito.doNothing().when(boardService).addBoard(boardDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/board/add").content(mapper.writeValueAsString(boardDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testUpdateBoard() throws JsonProcessingException, Exception {
		AddBoardDto boardDto = new AddBoardDto();
		Mockito.doNothing().when(boardService).updateBoard("123", boardDto);
		mockMvc.perform(MockMvcRequestBuilders.put("/board/update/{id}", "123")
				.content(mapper.writeValueAsString(boardDto)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetBoard() throws JsonProcessingException, Exception {
		BoardDto boardDto = BoardDto.builder().build();
		Mockito.when(boardService.getBoard("123")).thenReturn(boardDto);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/board/get/{id}", "123").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetAllBoards() throws JsonProcessingException, Exception {
		Set<BoardDto> boards = new HashSet<>();
		Mockito.when(boardService.getBoard()).thenReturn(boards);
		mockMvc.perform(MockMvcRequestBuilders.get("/board/get").contentType(MediaType.APPLICATION_JSON_VALUE))
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
