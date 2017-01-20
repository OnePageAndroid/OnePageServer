package kr.nexters.onepage.domain.user;

import kr.nexters.onepage.domain.common.OnePageServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = false)
	public void saveUser(String email) {
		if(UserValidator.emailOverlapValidate(() -> userRepository.findFirstByEmail(email))) {
			return;
		}
		userRepository.save(User.of(email));
	}

	public User findByEmail(String email) {
		User byEmail = userRepository.findFirstByEmail(email);
		if (Objects.isNull(byEmail)) {
			throw new OnePageServiceException("계정 정보가 없습니다.");
		}
		return byEmail;
	}

	public UserDto findDtoByEmail(String email) {
		return UserDto.of(findByEmail(email));
	}

}
