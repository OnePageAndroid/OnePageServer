package kr.nexters.onepage.domain.user;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nexters.onepage.domain.common.OnePageServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("null")
	public void saveUser(String email) {
		if(UserValidator.emailOverlapValidate(() -> {
			return userRepository.findByEmail(email);
		})) {
			return ;
		}
		userRepository.save(User.of(email));
	}

	public User findByEmail(String email) {
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
