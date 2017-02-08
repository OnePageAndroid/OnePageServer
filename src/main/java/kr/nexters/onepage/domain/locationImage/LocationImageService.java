package kr.nexters.onepage.domain.locationImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationImageService {

	@Autowired
	private LocationImageRepository locationImageRepository;

	public LocationImageResponseDto findByLocationIdAndWeather(Long locationId, DayType dayType) {
		LocationImageDto locationImageDto = LocationImageDto.of(locationImageRepository.findByLocationIdAndDayType(locationId, dayType));
		return LocationImageResponseDto.of(locationImageDto);
	}
}
