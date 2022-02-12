package ai.sahaj.snakeladder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.sahaj.snakeladder.dto.backend.MetricRequest;
import ai.sahaj.snakeladder.dto.backend.MetricResponse;
import ai.sahaj.snakeladder.services.MetricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Metrics Controller")
@RestController
@RequestMapping("/metrics")
public class MetricController {

	@Autowired
	private MetricService metricService;

	@ApiOperation(value = "Get all requested metrics.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added") })
	@PostMapping("/list/{simulation-id}")
	public ResponseEntity<List<MetricResponse<?>>> getMetrics(@PathVariable("simulation-id") String simulationId,
			@RequestBody List<MetricRequest> metricRequests) {
		return new ResponseEntity<>(metricService.getMetrics(simulationId, metricRequests), HttpStatus.OK);
	}
}
