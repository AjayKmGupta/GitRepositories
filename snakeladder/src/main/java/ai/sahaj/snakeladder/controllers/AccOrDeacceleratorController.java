package ai.sahaj.snakeladder.controllers;

import java.util.List;
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

import ai.sahaj.snakeladder.dto.frontend.AccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.AddAccOrDeacceleratorDto;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;
import ai.sahaj.snakeladder.validators.AddAccOrDeacceleratorDtoListValidator;
import ai.sahaj.snakeladder.validators.AddAccOrDeacceleratorDtoValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Ladder snake operations")
@RestController
@RequestMapping("/acc-deacc")
public class AccOrDeacceleratorController {

	@Autowired
	private AccOrDeacceleratorService accOrDeaccService;
	@Autowired
	private AddAccOrDeacceleratorDtoValidator addAccOrDeaccValidator;
	@Autowired
	private AddAccOrDeacceleratorDtoListValidator addAccOrDeaccListValidator;

	@InitBinder("addAccOrDeacceleratorDto")
	public void accOrDeacceleratorDtoBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(addAccOrDeaccValidator);
	}

	@InitBinder("list")
	public void accOrDeacceleratorDtoListBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(addAccOrDeaccListValidator);
	}

	@ApiOperation(value = "Add ladder or snake to board.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added"),
			@ApiResponse(code = 409, message = "Conflicts") })
	@PostMapping("/add")
	public ResponseEntity<Void> addAccOrDeaccelerator(
			@RequestBody @Validated List<AddAccOrDeacceleratorDto> accOrDeaccs) {
		accOrDeaccService.addAccOrDeaccelerator(accOrDeaccs);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Updated ladder or snake.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Not found") })
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateAccOrDeaccelerator(@PathVariable("id") String accOrDeAccId,
			@RequestBody @Validated AddAccOrDeacceleratorDto accOrDeacc) {
		accOrDeaccService.updateAccOrDeaccelerator(accOrDeAccId, accOrDeacc);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get all snakes and ladder added to board.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found ladders or snakes") })
	@GetMapping("/get/{board-id}")
	public ResponseEntity<Set<AccOrDeacceleratorDto>> getAccOrDeaccelerator(@PathVariable("board-id") String boardId) {
		return new ResponseEntity<>(accOrDeaccService.getAccOrDeaccelerator(boardId), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all snakes and ladders.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found ladders or snakes") })
	@GetMapping("/get")
	public ResponseEntity<Set<AccOrDeacceleratorDto>> getAccOrDeaccelerator() {
		return new ResponseEntity<>(accOrDeaccService.getAccOrDeaccelerator(), HttpStatus.OK);
	}
}
