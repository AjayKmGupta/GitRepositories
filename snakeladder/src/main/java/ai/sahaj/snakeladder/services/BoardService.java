package ai.sahaj.snakeladder.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import ai.sahaj.snakeladder.dto.frontend.AddBoardDto;
import ai.sahaj.snakeladder.dto.frontend.BoardDto;
import ai.sahaj.snakeladder.entity.Board;

public interface BoardService {

	void addBoard(AddBoardDto board);

	void updateBoard(String boardId, AddBoardDto board);

	BoardDto getBoard(String boardId);

	Set<BoardDto> getBoard();

	Optional<Board> getBoard(int id);

	BoardDto getBoardDtoFromBoard(Board board);

	Set<BoardDto> getBoardDtosFromBoards(List<Board> boards);

}
