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
	public void saveLocation(Double latitude, Double longitude, String name, String address) {
		locationRepository.save(Location.of(latitude, longitude, name, address));
	}

	public Location findById(Long locationId) {
		return locationRepository.findOne(locationId);
	}

	public List<LocationDto> findByLocationName(String locationName) {
		List<Location> byName = locationRepository.findByNameContaining(locationName);
		if (CollectionUtils.isEmpty(byName)) {
			return Lists.newArrayList();
		}
		return byName.stream().map(location -> LocationDto.of(location)).collect(Collectors.toList());
	}

	public List<LocationDto> findByLatAndLng(Double latitude, Double longitude) {
		// TODO
		return Lists.newArrayList();
	}
}
