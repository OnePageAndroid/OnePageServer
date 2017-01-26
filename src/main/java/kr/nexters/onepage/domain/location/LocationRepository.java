package kr.nexters.onepage.domain.location;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.nexters.onepage.domain.common.OnePageRepository;

@Repository
public interface LocationRepository extends OnePageRepository<Location, Long>, LocationRepositoryCustom {
	List<Location> findByName(String name);
	List<Location> findByNameContaining(String name);
}
