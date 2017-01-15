package kr.nexters.onepage.domain.location;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Transactional(readOnly = false)
	public void saveLocation(LocationDto locationDto) {
		locationRepository.save(Location.of(locationDto));
	}

	public Location findById(Long locationId) {
		return locationRepository.findOne(locationId);
	}

	public List<LocationDto> findByLocationName(String locationName) {
		List<Location> byName = locationRepository.findByName(locationName);
		if (CollectionUtils.isEmpty(byName)) {
			return Lists.newArrayList();
		}
		return byName.stream().map(location -> LocationDto.of(location)).collect(Collectors.toList());
	}
}
