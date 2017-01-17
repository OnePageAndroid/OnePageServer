package kr.nexters.onepage.domain.pageImage;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.nexters.onepage.domain.common.OnePageRepository;

@Repository
public interface PageImageRepository extends OnePageRepository<PageImage, Long> {
	List<PageImage> findByPageId(Long pageId);

	void deleteByPageId(Long pageId);
}
