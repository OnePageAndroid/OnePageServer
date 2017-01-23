package kr.nexters.onepage.domain.locationImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nexters.onepage.domain.locationImage.LocationImage.Weather;

@Service
public class LocationImageService {

	@Autowired
	private LocationImageRepository locationImageRepository;

	public LocationImageDto findByLocationIdAndWeather(Long locationId, String weather){
		Weather weathers=Weather.valueOf(weather);
		return locationImageRepository.findByLocationIdAndWeather(locationId, weathers);
	}
}
