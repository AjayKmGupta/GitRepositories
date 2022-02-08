package ai.sahaj.snakeladder.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ai.sahaj.snakeladder.constants.SnakeLadderConstants;
import ai.sahaj.snakeladder.dto.frontend.AddBoardDto;
import ai.sahaj.snakeladder.dto.frontend.BoardDto;
import ai.sahaj.snakeladder.dto.frontend.PositionDto;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.repositories.BoardRepository;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;
import ai.sahaj.snakeladder.services.BoardService;
import ai.sahaj.snakeladder.services.PositionService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private PositionService posService;
	@Autowired
	private AccOrDeacceleratorService accOrDeaccService;

	@Override
	public void addBoard(AddBoardDto boardDto) {

		List<Position> positions = posService.getPositions();
		if (CollectionUtils.isEmpty(positions)) {
			throw new BadRequestException("Positions must be setup before creating the board");
		}
		Board board = new Board();
		BeanUtils.copyProperties(boardDto, board);
		updateBoardEntity(board, boardDto, positions);
		boardRepo.save(board);
	}

	@Override
	public void updateBoard(String boardId, AddBoardDto boardDto) {
		Optional<Board> optBoard = boardRepo.findById(Integer.parseInt(boardId));
		if (optBoard.isEmpty()) {
			throw new ResourceNotFoundException("No board exists for given id");
		}
		List<Position> positions = posService.getPositions();
		if (CollectionUtils.isEmpty(positions)) {
			throw new BadRequestException("Positions must be setup before creating the board");
		}
		Board board = optBoard.get();
		BeanUtils.copyProperties(boardDto, board);
		updateBoardEntity(board, boardDto, positions);
		boardRepo.save(board);
	}

	private void updateBoardEntity(Board board, AddBoardDto boardDto, List<Position> positions) {
		Optional<Position> optStartPos = positions.stream()
				.filter(p -> p.getNumber() == SnakeLadderConstants.BOARD_START_POS).findFirst();
		Optional<Position> optFinalPos = positions.stream()
				.filter(p -> p.getNumber() == SnakeLadderConstants.BOARD_FINAL_POS).findFirst();
		if (optStartPos.isEmpty() || optFinalPos.isEmpty()) {
			throw new ResourceNotFoundException("Positions must be created before creating the board");
		}
		board.setStartPos(optStartPos.get());
		board.setFinalPos(optFinalPos.get());
		if (!CollectionUtils.isEmpty(boardDto.getAccOrDeaccs())) {
			List<AccOrDeaccelerator> accOrDeaccs = new ArrayList<>();
			boardDto.getAccOrDeaccs().forEach(accOrDeacc -> {
				List<AccOrDeaccelerator> accOrDeaccsDB = accOrDeaccService.getAllAccOrDeaccelerators();
				Optional<AccOrDeaccelerator> optAccOrDeacc = accOrDeaccsDB.stream()
						.filter(dbAccOrDeacc -> dbAccOrDeacc.getId() == accOrDeacc.getId()).findFirst();
				if (optAccOrDeacc.isEmpty()) {
					throw new ResourceNotFoundException("One or more snake or ladder doesn't exists");
				}
				accOrDeaccs.add(optAccOrDeacc.get());
			});
			board.setAccOrDeAccelerators(accOrDeaccs);
		}
	}

	@Override
	public BoardDto getBoard(String boardId) {
		Optional<Board> optBoard = boardRepo.findById(Integer.parseInt(boardId));
		if (optBoard.isEmpty()) {
			throw new ResourceNotFoundException("No board exists for given id");
		}
		BoardDto boardDto = BoardDto.builder().build();
		BeanUtils.copyProperties(optBoard.get(), boardDto);
		return boardDto;
	}

	@Override
	public Set<BoardDto> getBoard() {
		return getBoardDtosFromBoards(boardRepo.findAll());
	}

	@Override
	public BoardDto getBoardDtoFromBoard(Board board) {
		return BoardDto.builder().id(board.getId()).startPos(new PositionDto(board.getStartPos()))
				.finalPos(new PositionDto(board.getFinalPos())).name(board.getName())
				.accOrDeAccelerators(accOrDeaccService.getAccOrDeaccDtosFromAccOrDeacc(board.getAccOrDeAccelerators()))
				.build();
	}

	@Override
	public Set<BoardDto> getBoardDtosFromBoards(List<Board> boards) {
		return boards.stream()
				.map(board -> BoardDto.builder().id(board.getId()).startPos(new PositionDto(board.getStartPos()))
						.finalPos(new PositionDto(board.getFinalPos())).name(board.getName())
						.accOrDeAccelerators(
								accOrDeaccService.getAccOrDeaccDtosFromAccOrDeacc(board.getAccOrDeAccelerators()))
						.build())
				.collect(Collectors.toSet());
	}

	@Override
	public Optional<Board> getBoard(int id) {
		return boardRepo.findById(id);
	}

}
