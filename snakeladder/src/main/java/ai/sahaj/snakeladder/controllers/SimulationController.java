package ai.sahaj.snakeladder.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.sahaj.snakeladder.dto.frontend.AddSimulationDto;
import ai.sahaj.snakeladder.dto.frontend.SimulationDto;
import ai.sahaj.snakeladder.services.SimulationService;
import ai.sahaj.snakeladder.validators.AddSimulationDtoValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

	@Autowired
	private SimulationService simulationService;
	@Autowired
	private AddSimulationDtoValidator addSimulationDtoValidator;

	@InitBinder("addSimulationDto")
	public void simulationDtoBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(addSimulationDtoValidator);
	}

	@ApiOperation(value = "Add simulation.", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added"),
			@ApiResponse(code = 409, message = "Conflicts") })
	@PostMapping("/add")
	public ResponseEntity<Void> addSimulation(@RequestBody @Validated AddSimulationDto simulation) {
		simulationService.addSimulation(simulation);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update the given simulation.", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Not found") })
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateSimulation(@PathVariable("id") String simulationId,
			@RequestBody @Validated AddSimulationDto simulation) {
		simulationService.updateSimulation(simulationId, simulation);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get the given simulation.", response = SimulationDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found player") })
	@GetMapping("/get/{simulation-id}")
	public ResponseEntity<SimulationDto> getSimulation(@PathVariable("simulation-id") String simulationId) {
		return new ResponseEntity<>(simulationService.getSimulation(simulationId), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all simulations.", response = Set.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found Simulations") })
	@GetMapping("/get")
	public ResponseEntity<Set<SimulationDto>> getSimulation() {
		return new ResponseEntity<>(simulationService.getSimulation(), HttpStatus.OK);
	}

	@ApiOperation(value = "Start the given simulation.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully started"),
			@ApiResponse(code = 404, message = "Not found") })
	@PostMapping("/start/{simulation-id}")
	public ResponseEntity<String> startSimulation(@PathVariable("simulation-id") String simulationId) {
		String mesage = simulationService.startSimulation(simulationId);
		return new ResponseEntity<>(mesage, HttpStatus.OK);
	}
}
