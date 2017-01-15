package kr.nexters.onepage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.location.LocationDto;
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
	public ResponseDto save(@RequestParam LocationDto locationDto) {
		try {
			locationService.saveLocation(locationDto);
		} catch (Exception e) {
			log.error("location save : " + e.getMessage(), e);
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("장소 저장 성공");
	}

	@ApiOperation(value = "장소이름으로 장소정보 얻어오기", notes = "장소이름으로 장소정보 얻어오기")
	@RequestMapping(value = "/search/name", method = RequestMethod.GET)
	public LocationsResponseDto searchName(@RequestParam String locationName) {
		try {
			return LocationsResponseDto.of(locationService.findByLocationName(locationName));
		} catch (Exception e) {
			log.error("location search name : " + e.getMessage(), e);
			return LocationsResponseDto.empty();
		}
	}
}
