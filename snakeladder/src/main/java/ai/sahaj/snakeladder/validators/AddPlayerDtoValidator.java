package ai.sahaj.snakeladder.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ai.sahaj.snakeladder.dto.frontend.AddPlayerDto;
import ai.sahaj.snakeladder.exceptions.BadRequestException;

@Component
public class AddPlayerDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddPlayerDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "invalid.name", "Player name should be present.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "invalid.emailId",
				"Players emailId should be present.");

		if (errors.hasErrors()) {
			throw new BadRequestException("Validation errors", errors);
		}
	}

}
