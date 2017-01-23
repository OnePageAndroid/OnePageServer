package kr.nexters.onepage.domain.locationImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nexters.onepage.domain.support.Weather;

@Service
public class LocationImageService {

	@Autowired
	private LocationImageRepository locationImageRepository;

	public LocationImageResponseDto findByLocationIdAndWeather(Long locationId, Weather weather){
		return LocationImageResponseDto.of(locationImageRepository.findByLocationIdAndWeather(locationId, weather));
	}
}
