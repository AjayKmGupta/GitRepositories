package ai.sahaj.snakeladder.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ai.sahaj.snakeladder.constants.SnakeLadderConstants;
import ai.sahaj.snakeladder.dto.frontend.AddAccOrDeacceleratorDto;
import ai.sahaj.snakeladder.exceptions.BadRequestException;

@Component
public class AddAccOrDeacceleratorDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddAccOrDeacceleratorDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accOrDeAccType", "invalid.accOrDeAccType",
				"Accelerator type should be present.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startPos", "invalid.startPos",
				"Start position should be present.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "finalPosition", "invalid.finalPosition",
				"Final position should be present.");
		
		AddAccOrDeacceleratorDto addAccOrDeaccDto = (AddAccOrDeacceleratorDto) target;
		if (addAccOrDeaccDto.getStartPos().getNumber() < SnakeLadderConstants.BOARD_START_POS
				&& addAccOrDeaccDto.getStartPos().getNumber() > SnakeLadderConstants.BOARD_FINAL_POS) {
			errors.rejectValue("startPos.number", "invalid.number",
					"Invalid start position number, start position should be between board range");
		}
		if (addAccOrDeaccDto.getFinalPosition().getNumber() < SnakeLadderConstants.BOARD_START_POS
				&& addAccOrDeaccDto.getFinalPosition().getNumber() > SnakeLadderConstants.BOARD_FINAL_POS) {
			errors.rejectValue("finalPos.number", "invalid.number",
					"Invalid final position number, final position should be between board range");
		}
		if (errors.hasErrors()) {
			throw new BadRequestException("Validation errors", errors);
		}

	}

}
