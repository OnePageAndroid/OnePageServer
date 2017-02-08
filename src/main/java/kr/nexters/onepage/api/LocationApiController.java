package kr.nexters.onepage.api;

import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.location.LocationService;
import kr.nexters.onepage.domain.location.LocationsResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "장소 API", description = "장소 API", basePath = "/api/v1/location")
@RestController
@RequestMapping("/api/v1/location")
public class LocationApiController {
	@Autowired
	private LocationService locationService;

	@ApiOperation(value = "장소 저장하기", notes = "특정 장소 저장.")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseDto save(@RequestParam Double latitude, @RequestParam Double longitude,
		@RequestParam String name, @RequestParam String address) {
		Preconditions.checkNotNull(latitude, "latitude Parameter가 존재하지 않음");
		Preconditions.checkNotNull(longitude, "longitude Parameter가 존재하지 않음");
		Preconditions.checkNotNull(name, "name Parameter가 존재하지 않음");
		Preconditions.checkNotNull(address, "address Parameter가 존재하지 않음");
		try {
			locationService.saveLocation(latitude, longitude, name, address);
		} catch (Exception e) {
			log.error("location save : " + e.getMessage(), e);
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("장소 저장 성공");
	}

	@ApiOperation(value = "장소이름으로 장소정보 얻어오기", notes = "장소이름으로 장소정보 얻어오기")
	@RequestMapping(value = "/search/name", method = RequestMethod.GET)
	public LocationsResponseDto searchName(@RequestParam String locationName) {
		Preconditions.checkNotNull(locationName, "locationName parameter가 존재하지 않음");
		try {
			return LocationsResponseDto.of(locationService.findByLocationName(locationName));
		} catch (Exception e) {
			log.error("location search name : " + e.getMessage(), e);
			return LocationsResponseDto.empty();
		}
	}

	@ApiOperation(value = "모든 장소 리스트 가져오기", notes = "모든 장소 리스트 가져오기")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public LocationsResponseDto locationAll() {
		try {
			return LocationsResponseDto.of(locationService.findAll());
		} catch (Exception e) {
			log.error("location search name : " + e.getMessage(), e);
			return LocationsResponseDto.empty();
		}
	}

	@ApiOperation(value = "좌표로 근처 장소정보 얻어오기 - 단위 : 미터(디폴트 1km)", notes = "좌표로 장소정보 얻어오기")
	@RequestMapping(value = "/search/coordinates", method = RequestMethod.GET)
	public LocationsResponseDto searchLatLng(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam(required = false, defaultValue = "1000") Double meter) {
		try {
			locationService.saveGoogleLocation(latitude, longitude);
			return LocationsResponseDto.of(locationService.findByLatAndLngAndMeter(latitude, longitude, meter));
		} catch (Exception e) {
			log.error("location search name : " + e.getMessage(), e);
			return LocationsResponseDto.empty();
		}
	}
}