package ai.sahaj.snakeladder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.sahaj.snakeladder.dto.frontend.PositionDto;
import ai.sahaj.snakeladder.services.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/position")
@Api(value = "Position operations")
public class PositionController {

	@Autowired
	private PositionService posService;

	@ApiOperation(value = "Add positions to be added to board.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added positions"),
			@ApiResponse(code = 409, message = "Conflicts") })
	@PostMapping("/add")
	public ResponseEntity<Void> createPositions() {
		posService.createPositions();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Remove all positions.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully removed positions") })
	@DeleteMapping("/remove-all")
	public ResponseEntity<Void> removePositions() {
		posService.removeAllPositions();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get all positions added to board.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found positions") })
	@GetMapping("/get")
	public ResponseEntity<List<PositionDto>> getPositions() {
		return new ResponseEntity<>(posService.getPositionsDto(), HttpStatus.OK);
	}

}
