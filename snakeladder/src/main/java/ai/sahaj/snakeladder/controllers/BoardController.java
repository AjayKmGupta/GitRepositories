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

import ai.sahaj.snakeladder.dto.frontend.AddBoardDto;
import ai.sahaj.snakeladder.dto.frontend.BoardDto;
import ai.sahaj.snakeladder.services.BoardService;
import ai.sahaj.snakeladder.validators.AddBoardDtoValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private AddBoardDtoValidator addBoardDtoValidator;

	@InitBinder("addBoardDto")
	public void boardDtoBinder(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(addBoardDtoValidator);
	}

	@ApiOperation(value = "Add board.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added"),
			@ApiResponse(code = 409, message = "Conflicts") })
	@PostMapping("/add")
	public ResponseEntity<Void> addBoard(@RequestBody @Validated AddBoardDto board) {
		boardService.addBoard(board);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update the given board.", response = HttpStatus.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"),
			@ApiResponse(code = 404, message = "Not found") })
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateBoard(@PathVariable("id") String boardId,
			@RequestBody @Validated AddBoardDto board) {
		boardService.updateBoard(boardId, board);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Get the given board.", response = BoardDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found board") })
	@GetMapping("/get/{board-id}")
	public ResponseEntity<BoardDto> getBoard(@PathVariable("board-id") String boardId) {
		return new ResponseEntity<>(boardService.getBoard(boardId), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all boards.", response = Set.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found board") })
	@GetMapping("/get")
	public ResponseEntity<Set<BoardDto>> getBoard() {
		return new ResponseEntity<>(boardService.getBoard(), HttpStatus.OK);
	}

}
