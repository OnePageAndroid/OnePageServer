package kr.nexters.onepage.domain.locationImage;

import org.springframework.stereotype.Repository;

import kr.nexters.onepage.domain.common.OnePageRepository;

@Repository
public interface LocationImageRepository extends OnePageRepository<LocationImage, Long>{

	LocationImage findByLocationIdAndDayType(Long locationId, DayType dayType);
}
