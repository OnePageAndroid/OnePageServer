package kr.nexters.onepage.domain.locationImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationImageService {

	@Autowired
	private LocationImageRepository locationImageRepository;

	public LocationImageResponseDto findByLocationIdAndDayType(Long locationId, DayType dayType) {
		LocationImage list = locationImageRepository.findByLocationIdAndDayType(locationId, dayType);
		System.out.println(list.toString());
		LocationImageDto locationImageDto = LocationImageDto.of(locationImageRepository.findByLocationIdAndDayType(locationId, dayType));
		return LocationImageResponseDto.of(locationImageDto);
	}
}
