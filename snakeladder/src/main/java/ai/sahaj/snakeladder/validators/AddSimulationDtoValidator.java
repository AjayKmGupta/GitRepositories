package ai.sahaj.snakeladder.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ai.sahaj.snakeladder.dto.frontend.AddSimulationDto;
import ai.sahaj.snakeladder.exceptions.BadRequestException;

@Component
public class AddSimulationDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddSimulationDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "invalid.name", "Simulation name should be present.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "simulationMode", "invalid.simulationMode",
				"Simulation Mode should be present.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "simulationCount", "invalid.simulationCount",
				"Simulation Count should be present.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "board", "invalid.board", "Board should be present.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "players", "invalid.players", "Players should be present.");

		if (errors.hasErrors()) {
			throw new BadRequestException("Validation errors", errors);
		}
	}

}
