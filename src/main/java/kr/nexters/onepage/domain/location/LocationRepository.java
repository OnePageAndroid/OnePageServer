package kr.nexters.onepage.domain.location;

import kr.nexters.onepage.domain.common.OnePageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends OnePageRepository<Location, Long>, LocationRepositoryCustom {
	List<Location> findByName(String name);
}
