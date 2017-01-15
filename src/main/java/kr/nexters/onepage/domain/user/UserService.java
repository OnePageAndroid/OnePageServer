package kr.nexters.onepage.domain.user;

import kr.nexters.onepage.domain.common.OnePageServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void saveUser(String email) {
		userRepository.save(User.of(email));
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
