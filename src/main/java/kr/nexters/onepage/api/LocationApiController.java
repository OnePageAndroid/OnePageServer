package kr.nexters.onepage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.location.LocationDto;
import kr.nexters.onepage.domain.location.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "LocationApiController", description = "location api", basePath = "/api/v1/location")
@RestController
@RequestMapping("/api/v1/location")
public class LocationApiController {
	@Autowired
	private LocationService locationService;

	@ApiOperation(value = "save location", notes = "save location")
	@RequestMapping("/save")
	public ResponseDto save(LocationDto locationDto) {
		try {
			locationService.saveLocation(locationDto);
		} catch (Exception e) {
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("장소 저장 성공");
	}
}
