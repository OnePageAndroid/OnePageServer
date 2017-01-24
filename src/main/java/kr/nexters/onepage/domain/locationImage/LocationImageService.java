package kr.nexters.onepage.domain.locationImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationImageService {

	@Autowired
	private LocationImageRepository locationImageRepository;

	public LocationImageResponseDto findByLocationIdAndWeather(Long locationId, WeatherType weather) {
		LocationImageDto locationImageDto = LocationImageDto.of(locationImageRepository.findByLocationIdAndWeatherType(locationId, weather));
		return LocationImageResponseDto.of(locationImageDto);
	}
}
