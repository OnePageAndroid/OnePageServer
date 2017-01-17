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

		if(userDto.getEmail().length()>40){
			errors.rejectValue("email", "메일길이 초과");
		}
		String[] email = userDto.getEmail().split("@");
		if(!email[1].equals("@gamil.com"))
			errors.rejectValue("email", "구글이메일을 입력하세요.");
	}
}
