package kr.nexters.onepage.domain.heart;

import kr.nexters.onepage.domain.common.OnePageRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends OnePageRepository<Heart, Long>, HeartRepositoryCustom {
	Long countByPageId(Long pageId);
}
