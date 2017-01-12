package kr.nexters.onepage.domain.location;

import kr.nexters.onepage.domain.common.OnePageRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends OnePageRepository<Location, Long>, LocationRepositoryCustom {

}
