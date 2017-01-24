package kr.nexters.onepage.domain.page;

import java.util.List;

import kr.nexters.onepage.domain.util.LocalDateRange;

public interface PageRepositoryCustom {
	List<Page> findByLocationIdAndPageable(Long locationId, int offset, int perSize);

	List<Page> findByEmailAndPageable(String email, int offset, int perSize);

	List<Page> findByHeartAndPageable(String email, int offset, int perSize);

	long countByLocationIdAndRange(Long locationId, LocalDateRange range);
}
