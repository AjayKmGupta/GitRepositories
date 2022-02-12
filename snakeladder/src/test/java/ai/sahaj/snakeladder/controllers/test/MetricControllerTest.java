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

import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.constants.Unit;
import ai.sahaj.snakeladder.controllers.MetricController;
import ai.sahaj.snakeladder.controllers.test.AccOrDeacceleratorControllerTest.ContextConfig;
import ai.sahaj.snakeladder.dto.backend.MetricRequest;
import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.services.MetricService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContextConfig.class, loader = AnnotationConfigContextLoader.class)
public class MetricControllerTest {

	@InjectMocks
	private MetricController metricController;

	@Mock
	private MetricService metricService;

	@Autowired
	private ObjectMapper mapper;

	private MockMvc mockMvc;
	private AutoCloseable closeable;

	@BeforeEach
	public void init() {
		closeable = MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.metricController)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@AfterEach
	public void close() throws Exception {
		closeable.close();
	}

	@Test
	void testGetMetrics() throws JsonProcessingException, Exception {
		List<MetricRequest> metricRequests = new ArrayList<>();
		MetricRequest mReq = new MetricRequest();
		mReq.setMetricName(Stats.AOCAVG);
		metricRequests.add(mReq);

		List<MetricResponse<?>> mResponses = new ArrayList<>();
		StatsData<Integer> statsData = new StatsData<>();
		statsData.setMetricValue(8);
		statsData.setUnit(Unit.NUMBER);
		MetricResponse<StatsData<?>> mResponse = new MetricResponse<>();
		mResponse.setData(statsData);
		mResponse.setMetricName(Stats.AOCAVG);
		mResponse.setMetricDescription("Average amount of climb during the simulation");
		mResponses.add(mResponse);

		Mockito.when(metricService.getMetrics("123", metricRequests)).thenReturn(mResponses);
		mockMvc.perform(MockMvcRequestBuilders.post("/metrics/list/{id}", "123")
				.content(mapper.writeValueAsString(metricRequests)).contentType(MediaType.APPLICATION_JSON_VALUE))
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
