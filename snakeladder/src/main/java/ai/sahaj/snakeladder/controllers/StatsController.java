package ai.sahaj.snakeladder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.dto.backend.StatsRequest;
import ai.sahaj.snakeladder.services.StatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Stats Controller")
@RestController
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private StatsService statsService;

	@ApiOperation(value = "Get all requested stats.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added") })
	@PostMapping("/list")
	public ResponseEntity<List<StatsData>> getStats(@RequestBody List<StatsRequest> statsRequests) {
		return new ResponseEntity<>(statsService.getStats(statsRequests), HttpStatus.OK);
	}
}
