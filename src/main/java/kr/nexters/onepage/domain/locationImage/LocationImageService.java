package kr.nexters.onepage.domain.locationImage;

import kr.nexters.onepage.domain.location.Location;
import kr.nexters.onepage.domain.location.LocationService;
import kr.nexters.onepage.domain.util.DaumAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationImageService {
	@Autowired
	private LocationImageRepository locationImageRepository;
	@Autowired
	private LocationService locationService;

	@Transactional(readOnly = true)
	public LocationImageDto findByLocationIdAndDay(Long locationId, DayType dayType) {
		Location location = locationService.findById(locationId);
		LocationImage locationImage = locationImageRepository.findByLocationIdAndDayType(locationId, dayType);
		LocationImageDto locationImageDto = LocationImageDto.of(locationImage, location);
		if (locationImage != null) {
			return locationImageDto;
		}
		return DaumAPI.getImageUrl(location, dayType);
	}
}