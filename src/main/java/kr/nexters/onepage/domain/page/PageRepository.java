package kr.nexters.onepage.domain.page;

import kr.nexters.onepage.domain.common.OnePageRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends OnePageRepository<Page, Long> {
}
