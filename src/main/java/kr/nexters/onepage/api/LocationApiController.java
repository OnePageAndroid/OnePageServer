package kr.nexters.onepage.api;

import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.location.LocationDto;
import kr.nexters.onepage.domain.location.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/location")
public class LocationApiController {
	@Autowired
	private LocationService locationService;

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
