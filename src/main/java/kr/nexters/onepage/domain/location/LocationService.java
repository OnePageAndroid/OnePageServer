package kr.nexters.onepage.domain.location;

import com.google.common.collect.Lists;
import kr.nexters.onepage.domain.calculate.LatLngCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static kr.nexters.onepage.domain.common.NumericConstant.HUNDRED;
import static kr.nexters.onepage.domain.common.NumericConstant.TEN;

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
		List<Location> locations = locationRepository.findByNameContaining(locationName);
		if (CollectionUtils.isEmpty(locations)) {
			return Lists.newArrayList();
		}
		return locations.stream().map(location -> LocationDto.of(location)).collect(Collectors.toList());
	}

	public List<LocationDto> findByLatAndLng(Double latitude, Double longitude) {
		return locationRepository.findAll().stream()
			.filter(location -> LatLngCalculator.meterDistance(latitude, longitude, location.getLatitude(), location.getLongitude()) <= HUNDRED) // 100m 이내 장소만
			.map(location -> LocationDto.of(location)) // 장소 DTO 변환
			.sorted((loc1, loc2) -> (int) Math.round( // 가장 가까운 장소 순으로 오름차순
				LatLngCalculator.meterDistance(latitude, longitude, loc1.getLatitude(), loc1.getLongitude()) - LatLngCalculator.meterDistance(
					latitude, longitude, loc2.getLatitude(), loc2.getLongitude())))
			.limit(TEN)
			.collect(Collectors.toList());
	}
}
