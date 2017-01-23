package kr.nexters.onepage.domain.locationImage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.nexters.onepage.domain.common.OnePageRepository;
import kr.nexters.onepage.domain.locationImage.LocationImage.Weather;

@Repository
public interface LocationImageRepository extends OnePageRepository<LocationImage, Long>{
	@Query("SELECT * FROM LocationImage where locationId= ? AND weather =?")
	LocationImageDto findByLocationIdAndWeather(Long locationId, Weather weather);
}
