package kr.nexters.onepage.domain.location;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LocationValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz){
		return (Location.class.isAssignableFrom(clazz));
	}


	@Override
	public void validate(Object target, Errors errors){
		Location location = (Location)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address","주소를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "latitude","위도를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longitude","경도를 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","장소 이름을 입력하세요.");
	}
}
