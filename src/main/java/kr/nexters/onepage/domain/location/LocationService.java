package kr.nexters.onepage.domain.location;

import static kr.nexters.onepage.domain.common.NumericConstant.TEN;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import kr.nexters.onepage.domain.calculate.LatLngCalculator;
import kr.nexters.onepage.domain.util.DaumAPI;
import kr.nexters.onepage.domain.util.NaverAPI;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Transactional(readOnly = false)
	public void saveLocation(Double latitude, Double longitude, String name, String engName, String address) {
		if (latitude > 90.1 || longitude > 180.1)
			return;
		locationRepository.save(Location.of(latitude, longitude, name, engName, address));
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

	public List<LocationDto> findByLatAndLngAndMeter(Double latitude, Double longitude, Double meter) {
		List<Location> locations = locationRepository.findAll();
		if (CollectionUtils.isEmpty(locations)) {
			return Lists.newArrayList();
		}
		return locations.stream()
			.filter(location -> LatLngCalculator.meterDistance(latitude, longitude, location.getLatitude(), location.getLongitude())
				<= meter) // 1000m 이내 장소만
			.map(location -> LocationDto.of(location)) // 장소 DTO 변환
			.sorted((loc1, loc2) -> (int) Math.round( // 가장 가까운 장소 순으로 오름차순
				LatLngCalculator.meterDistance(latitude, longitude, loc1.getLatitude(), loc1.getLongitude()) - LatLngCalculator.meterDistance(
					latitude, longitude, loc2.getLatitude(), loc2.getLongitude())))
			.limit(TEN)
			.collect(Collectors.toList());
	}

	public List<LocationDto> findAll() {
		List<Location> locations = locationRepository.findAll();
		if (CollectionUtils.isEmpty(locations)) {
			return Lists.newArrayList();
		}
		return locations.stream().map(location -> LocationDto.of(location)).collect(Collectors.toList());
	}

	@Transactional(readOnly = false)
	public void saveDaumLocation(Double latitude, Double longitude) {
		Location location = DaumAPI.makeLocation(latitude, longitude);
		locationRepository.save(location);
	}

	public List<LocationDto> searchLatLng(Double latitude, Double longitude, Double meter) {
		List<LocationDto> list = findByLatAndLngAndMeter(latitude, longitude, meter);
		if (CollectionUtils.isEmpty(list)){
			List<LocationDto> list2 = findByLatAndLngAndMeter(latitude, longitude, 10000.0);
			if(CollectionUtils.isEmpty(list2)){
				saveDaumLocation(latitude, longitude);
				return findByLatAndLngAndMeter(latitude, longitude, 10000.0);
			}
			return list2;
		}
		return list;
	}

	public void translateMigration() {
		List<Location> locations = locationRepository.findAll();
		if(CollectionUtils.isEmpty(locations)) {
			return;
		}
		for(Location location : locations) {
			location.setEngName(NaverAPI.convertKorToEng(location.getName()));
		}
		locationRepository.save(locations);
	}
}
