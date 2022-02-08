package ai.sahaj.snakeladder.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import ai.sahaj.snakeladder.dto.frontend.AccOrDeacceleratorDto;
import ai.sahaj.snakeladder.dto.frontend.AddAccOrDeacceleratorDto;
import ai.sahaj.snakeladder.entity.AccOrDeaccelerator;
import ai.sahaj.snakeladder.entity.Position;

public interface AccOrDeacceleratorService {

	void addAccOrDeaccelerator(List<AddAccOrDeacceleratorDto> accOrDeaccs);

	void updateAccOrDeaccelerator(String accOrDeAccId, AddAccOrDeacceleratorDto accOrDeacc);

	Set<AccOrDeacceleratorDto> getAccOrDeaccelerator(String boardId);

	Set<AccOrDeacceleratorDto> getAccOrDeaccelerator();

	Optional<AccOrDeaccelerator> getAccOrDeaccByStartPosition(Position position);

	List<AccOrDeaccelerator> getAllAccOrDeaccelerators();

	Set<AccOrDeacceleratorDto> getAccOrDeaccDtosFromAccOrDeacc(List<AccOrDeaccelerator> accOrDeaccs);

}
