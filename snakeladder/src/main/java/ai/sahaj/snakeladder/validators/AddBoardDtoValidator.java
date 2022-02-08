package ai.sahaj.snakeladder.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ai.sahaj.snakeladder.dto.frontend.AddBoardDto;
import ai.sahaj.snakeladder.exceptions.BadRequestException;

@Component
public class AddBoardDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddBoardDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "invalid.name", "Board name should not be empty.");

		if (errors.hasErrors()) {
			throw new BadRequestException("Validation errors", errors);
		}

	}

}
