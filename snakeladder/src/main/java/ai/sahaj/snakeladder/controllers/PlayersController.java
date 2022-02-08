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

import ai.sahaj.snakeladder.dto.frontend.AddPlayerDto;
import ai.sahaj.snakeladder.dto.frontend.PlayerDto;
import ai.sahaj.snakeladder.services.PlayersService;
import ai.sahaj.snakeladder.validators.AddPlayerDtoValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/players")
public class PlayersController {

	@Autowired
	private PlayersService playerService;
	@Autowired
	private AddPlayerDtoValidator addPlayerDtoValidator;

	@InitBinder("addPlayerDto")
	public void playerDtoBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(addPlayerDtoValidator);
	}

	@ApiOperation(value = "Add player.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added"),
			@ApiResponse(code = 409, message = "Conflicts") })
	@PostMapping("/add")
	public ResponseEntity<Void> addBoard(@RequestBody @Validated AddPlayerDto player) {
		playerService.addPlayer(player);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update the given player.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Not found") })
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateBoard(@PathVariable("id") String playerId,
			@RequestBody @Validated AddPlayerDto player) {
		playerService.updatePlayer(playerId, player);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get the given player.", response = PlayerDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found player") })
	@GetMapping("/get/{player-id}")
	public ResponseEntity<PlayerDto> getPlayer(@PathVariable("player-id") String playerId) {
		return new ResponseEntity<>(playerService.getPlayer(playerId), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all players.", response = Set.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found players") })
	@GetMapping("/get")
	public ResponseEntity<Set<PlayerDto>> getPlayer() {
		return new ResponseEntity<>(playerService.getPlayer(), HttpStatus.OK);
	}

}
