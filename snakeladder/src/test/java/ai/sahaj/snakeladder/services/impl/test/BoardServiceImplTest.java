package ai.sahaj.snakeladder.services.impl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import ai.sahaj.snakeladder.dto.frontend.AccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.AddBoardDto;
import ai.sahaj.snakeladder.dto.frontend.BoardDto;
import ai.sahaj.snakeladder.dto.frontend.PositionDto;
import ai.sahaj.snakeladder.entity.Board;
import ai.sahaj.snakeladder.entity.Position;
import ai.sahaj.snakeladder.exceptions.BadRequestException;
import ai.sahaj.snakeladder.exceptions.ResourceNotFoundException;
import ai.sahaj.snakeladder.repositories.BoardRepository;
import ai.sahaj.snakeladder.services.AccOrDeacceleratorService;
import ai.sahaj.snakeladder.services.PositionService;
import ai.sahaj.snakeladder.services.impl.BoardServiceImpl;
import ai.sahaj.snakeladder.util.test.TestUtil;

@ExtendWith(SpringExtension.class)
class BoardServiceImplTest {

	@InjectMocks
	private BoardServiceImpl boardServiceImpl;
	@Mock
	private BoardRepository boardRepo;
	@Mock
	private PositionService posService;
	@Mock
	private AccOrDeacceleratorService accOrDeaccService;

	@Test
	void testAddBoard() {

		AddBoardDto boardDto = new AddBoardDto();
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		AccOrDeacceleratorDto accOrDeaccDto = new AccOrDeacceleratorDto(1, AccOrDeacceleratorType.SNAKE,
				new PositionDto(34, 34), new PositionDto(13, 13));
		accOrDeaccs.add(accOrDeaccDto);
		boardDto.setAccOrDeaccs(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		boardServiceImpl.addBoard(boardDto);
		Mockito.verify(boardRepo).save(Mockito.any());
	}

	@Test
	void testAddBoardWithEmptyPositions() {

		AddBoardDto boardDto = new AddBoardDto();
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		AccOrDeacceleratorDto accOrDeaccDto = new AccOrDeacceleratorDto(1, AccOrDeacceleratorType.SNAKE,
				new PositionDto(56, 56), new PositionDto(36, 36));
		accOrDeaccs.add(accOrDeaccDto);
		boardDto.setAccOrDeaccs(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(null);
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			boardServiceImpl.addBoard(boardDto);
		});
		assertEquals("Positions must be setup before creating the board", thrown.getMessage());
	}

	@Test
	void testAddBoardWithEmptyStartPosition() {

		AddBoardDto boardDto = new AddBoardDto();
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		AccOrDeacceleratorDto accOrDeaccDto = new AccOrDeacceleratorDto(1, AccOrDeacceleratorType.SNAKE,
				new PositionDto(56, 56), new PositionDto(36, 36));
		accOrDeaccs.add(accOrDeaccDto);
		boardDto.setAccOrDeaccs(accOrDeaccs);
		List<Position> positions = TestUtil.getAllPositions();
		Position position = new Position();
		position.setId(1);
		position.setNumber(1);
		positions.remove(position);
		Mockito.when(posService.getPositions()).thenReturn(positions);
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			boardServiceImpl.addBoard(boardDto);
		});
		assertEquals("Positions must be created before creating the board", thrown.getMessage());
	}

	@Test
	void testAddBoardWithEmptyFinalPosition() {

		AddBoardDto boardDto = new AddBoardDto();
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		AccOrDeacceleratorDto accOrDeaccDto = new AccOrDeacceleratorDto(1, AccOrDeacceleratorType.SNAKE,
				new PositionDto(56, 56), new PositionDto(36, 36));
		accOrDeaccs.add(accOrDeaccDto);
		boardDto.setAccOrDeaccs(accOrDeaccs);
		List<Position> positions = TestUtil.getAllPositions();
		Position position = new Position();
		position.setId(100);
		position.setNumber(100);
		positions.remove(position);
		Mockito.when(posService.getPositions()).thenReturn(positions);
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			boardServiceImpl.addBoard(boardDto);
		});
		assertEquals("Positions must be created before creating the board", thrown.getMessage());
	}

	@Test
	void testAddBoardWithMissingLadder() {

		AddBoardDto boardDto = new AddBoardDto();
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		AccOrDeacceleratorDto accOrDeaccDto = new AccOrDeacceleratorDto(3, AccOrDeacceleratorType.SNAKE,
				new PositionDto(34, 34), new PositionDto(13, 13));
		accOrDeaccs.add(accOrDeaccDto);
		boardDto.setAccOrDeaccs(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			boardServiceImpl.addBoard(boardDto);
		});
		assertEquals("One or more snake or ladder doesn't exists", thrown.getMessage());
	}

	@Test
	void testAddBoardWithEmptyLadder() {

		AddBoardDto boardDto = new AddBoardDto();
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		boardServiceImpl.addBoard(boardDto);
		Mockito.verify(boardRepo).save(Mockito.any());
	}

	@Test
	void testUpdateBoard() {

		Board board = new Board();
		AddBoardDto boardDto = new AddBoardDto();
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		AccOrDeacceleratorDto accOrDeaccDto = new AccOrDeacceleratorDto(1, AccOrDeacceleratorType.SNAKE,
				new PositionDto(34, 34), new PositionDto(13, 13));
		accOrDeaccs.add(accOrDeaccDto);
		boardDto.setAccOrDeaccs(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		Mockito.when(boardRepo.findById(123)).thenReturn(Optional.of(board));
		boardServiceImpl.updateBoard("123", boardDto);
		Mockito.verify(boardRepo).save(Mockito.any());
	}

	@Test
	void testUpdateBoardWithEmpty() {

		AddBoardDto boardDto = new AddBoardDto();
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		AccOrDeacceleratorDto accOrDeaccDto = new AccOrDeacceleratorDto(1, AccOrDeacceleratorType.SNAKE,
				new PositionDto(34, 34), new PositionDto(13, 13));
		accOrDeaccs.add(accOrDeaccDto);
		boardDto.setAccOrDeaccs(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(TestUtil.getAllPositions());
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		Mockito.when(boardRepo.findById(123)).thenReturn(Optional.empty());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			boardServiceImpl.updateBoard("123", boardDto);
		});
		assertEquals("No board exists for given id", thrown.getMessage());
	}

	@Test
	void testUpdateBoardWithEmptyPositions() {

		Board board = new Board();
		AddBoardDto boardDto = new AddBoardDto();
		Set<AccOrDeacceleratorDto> accOrDeaccs = new HashSet<>();
		AccOrDeacceleratorDto accOrDeaccDto = new AccOrDeacceleratorDto(1, AccOrDeacceleratorType.SNAKE,
				new PositionDto(56, 56), new PositionDto(36, 36));
		accOrDeaccs.add(accOrDeaccDto);
		boardDto.setAccOrDeaccs(accOrDeaccs);
		Mockito.when(posService.getPositions()).thenReturn(null);
		Mockito.when(accOrDeaccService.getAllAccOrDeaccelerators()).thenReturn(TestUtil.getAccOrDeaccs());
		Mockito.when(boardRepo.findById(123)).thenReturn(Optional.of(board));
		BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
			boardServiceImpl.updateBoard("123", boardDto);
		});
		assertEquals("Positions must be setup before creating the board", thrown.getMessage());
	}

	@Test
	void testGetBoardById() {
		Board board = new Board();
		board.setId(123);
		Mockito.when(boardRepo.findById(123)).thenReturn(Optional.of(board));
		BoardDto boardDto = boardServiceImpl.getBoard("123");
		assertEquals(board.getId(), boardDto.getId());
	}

	@Test
	void testGetBoardWithMissingId() {
		Mockito.when(boardRepo.findById(123)).thenReturn(Optional.empty());
		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
			boardServiceImpl.getBoard("123");
		});
		assertEquals("No board exists for given id", thrown.getMessage());
	}

	@Test
	void testGetAllBoards() {
		List<Board> boards = new ArrayList<>();
		Board board = new Board();
		board.setId(123);
		board.setName("First Board");
		boards.add(board);
		Mockito.when(boardRepo.findAll()).thenReturn(boards);
		Set<BoardDto> boardSet = boardServiceImpl.getBoard();
		List<BoardDto> boardList = new ArrayList<>(boardSet);
		assertEquals(boards.get(0).getId(), boardList.get(0).getId());
	}

	@Test
	void testGetBoardDtoFromBoard() {
		Board board = new Board();
		board.setId(123);
		board.setName("My Board");
		board.setAccOrDeAccelerators(TestUtil.getAccOrDeaccs());
		board.setStartPos(new Position(1));
		board.setFinalPos(new Position(100));
		BoardDto boardDto = boardServiceImpl.getBoardDtoFromBoard(board);
		assertEquals(board.getId(), boardDto.getId());
		assertEquals(board.getName(), boardDto.getName());

	}

	@Test
	void testGetBoardDtosFromBoards() {
		List<Board> boards = new ArrayList<>();
		Board board = new Board();
		board.setId(123);
		board.setName("My Board");
		board.setAccOrDeAccelerators(TestUtil.getAccOrDeaccs());
		board.setStartPos(new Position(1));
		board.setFinalPos(new Position(100));
		boards.add(board);
		Set<BoardDto> boardDtos = boardServiceImpl.getBoardDtosFromBoards(boards);
		List<BoardDto> boardDtosList = new ArrayList<>(boardDtos);
		assertEquals(boards.get(0).getId(), boardDtosList.get(0).getId());
		assertEquals(boards.get(0).getName(), boardDtosList.get(0).getName());
	}

	@Test
	void testGetBoardEntityById() {
		Board board = new Board();
		board.setId(123);
		board.setName("My Board");
		Mockito.when(boardRepo.findById(123)).thenReturn(Optional.of(board));
		Optional<Board> actBoard = boardServiceImpl.getBoard(123);
		assertEquals(board, actBoard.get());
	}

}
