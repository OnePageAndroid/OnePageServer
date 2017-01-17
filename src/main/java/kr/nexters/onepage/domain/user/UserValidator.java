package kr.nexters.onepage.domain.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz){
		return (UserDto.class.isAssignableFrom(clazz));
	}
	@Override
	public void validate(Object target, Errors errors){
		UserDto userDto = (UserDto)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "이메일을 입력하세요.");
	}
}
