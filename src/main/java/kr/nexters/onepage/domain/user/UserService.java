package kr.nexters.onepage.domain.user;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import kr.nexters.onepage.domain.common.OnePageServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserValidator userValidator;

	@SuppressWarnings("null")
	public void saveUser(String email) {
		User user = User.of(email);
		BindingResult bindingResult = null;
		userValidator.validate(user,bindingResult);
		if(!bindingResult.hasErrors())
			userRepository.save(user);
		else
			return;
	}

	public User findByEmail(String email) {
		if(StringUtils.isEmpty(email)) {
			throw new OnePageServiceException("계정 정보가 없습니다.");
		}
		User byEmail = userRepository.findByEmail(email);
		if (Objects.isNull(byEmail)) {
			throw new OnePageServiceException("계정 정보가 없습니다.");
		}
		return byEmail;
	}

	public UserDto findDtoByEmail(String email) {
		return UserDto.of(findByEmail(email));
	}
}
