package kr.nexters.onepage.domain.heart;

import kr.nexters.onepage.domain.user.User;
import kr.nexters.onepage.domain.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HeartService {
	@Autowired
	private HeartRepository heartRepository;
	@Autowired
	private UserService userService;

	public void saveHeart(Long pageId, String email) {
		Heart heart = Heart.of(pageId, () -> {
			User user = userService.findByEmail(email);
			return user.getId();
		});
		heartRepository.save(heart);
	}

	public void removeHeart(Long pageId, String email) {
		Heart heart = heartRepository.findByPageIdAndEmail(pageId, email);
		heart.deleted();
	}
}
