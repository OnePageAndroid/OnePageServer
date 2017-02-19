package kr.nexters.onepage.domain.pageImage;

import kr.nexters.onepage.domain.common.OnePageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageImageRepository extends OnePageRepository<PageImage, Long> {
	List<PageImage> findByPageId(Long pageId);
}
