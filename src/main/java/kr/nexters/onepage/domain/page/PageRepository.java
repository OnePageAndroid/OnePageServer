package kr.nexters.onepage.domain.page;

import org.springframework.stereotype.Repository;

import kr.nexters.onepage.domain.common.OnePageRepository;

@Repository
public interface PageRepository extends OnePageRepository<Page, Long>, PageRepositoryCustom {
	int countByLocationId(Long locationId);
	int countByUserId(Long userId);
}
