package kr.nexters.onepage.domain.locationImage;

import org.springframework.stereotype.Repository;

import kr.nexters.onepage.domain.common.OnePageRepository;
import kr.nexters.onepage.domain.support.Weather;

@Repository
public interface LocationImageRepository extends OnePageRepository<LocationImage, Long>{

	LocationImageDto findByLocationIdAndWeather(Long locationId, Weather weather);
}
