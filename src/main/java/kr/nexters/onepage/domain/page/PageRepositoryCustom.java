package kr.nexters.onepage.domain.page;

import java.util.List;

public interface PageRepositoryCustom {
	List<Page> findByLocationIdAndPageable(Long locationId, int offset, int perSize);

	List<Page> findByEmailAndPageable(String email, int offset, int perSize);

	List<Page> findByHeartAndPageable(String email, int offset, int perSize);
}
