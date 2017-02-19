package kr.nexters.onepage.domain.locationImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.location.LocationRepository;
import kr.nexters.onepage.domain.util.DaumAPI;

@Service
public class LocationImageService {
	@Autowired
	private LocationImageRepository locationImageRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Transactional(readOnly = true)
	public LocationImageResponseDto findByLocationIdAndDay(Long locationId, DayType dayType) {
		LocationImageDto locationImageDto = LocationImageDto.of(locationImageRepository.findByLocationIdAndDayType(locationId, dayType));
		if (locationImageRepository.findByLocationIdAndDayType(locationId, dayType) != null) {
			return LocationImageResponseDto.of(locationImageDto);
		}
		return findByDaumLocation(locationId);
	}

	public LocationImageResponseDto findByDaumLocation(Long locationId) {
		Location location = locationRepository.findById(locationId);
		return DaumAPI.getImageUrl(location);
	}
}