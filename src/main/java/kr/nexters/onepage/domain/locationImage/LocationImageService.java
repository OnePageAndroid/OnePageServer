package kr.nexters.onepage.domain.locationImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationImageService {
	@Autowired
	private LocationImageRepository locationImageRepository;

	@Transactional(readOnly = true)
	public LocationImageResponseDto findByLocationIdAndDay(Long locationId, DayType dayType) {
		LocationImageDto locationImageDto = LocationImageDto.of(locationImageRepository.findByLocationIdAndDayType(locationId, dayType));
		return LocationImageResponseDto.of(locationImageDto);
	}
}
