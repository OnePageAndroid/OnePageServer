package kr.nexters.onepage.domain.heart;

import kr.nexters.onepage.domain.common.OnePageServiceException;
import kr.nexters.onepage.domain.page.PageService;
import kr.nexters.onepage.domain.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class HeartService {
	@Autowired
	private HeartRepository heartRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PageService pageService;

	@Transactional(readOnly = false)
	public void saveHeart(Long pageId, String email) {
		Heart heart = Heart.of(pageService.findById(pageId), userService.findByEmail(email));
		if(existsByPageIdAndEmail(pageId, email)) {
			return;
		}
		heartRepository.save(heart);
	}

	private boolean existsByPageIdAndEmail(Long pageId, String email) {
		return Objects.nonNull(heartRepository.findByPageIdAndEmail(pageId, email));
	}

	@Transactional(readOnly = false)
	public void removeHeart(Long pageId, String email) {
		Heart heart = heartRepository.findByPageIdAndEmail(pageId, email);
		if (Objects.nonNull(heart)) {
			throw new OnePageServiceException("좋아요가 존재하지 않습니다.");
		}
		heart.deleted();
	}

	public Long countByPageId(Long pageId) {
		return heartRepository.countByPageId(pageId);
	}
}
