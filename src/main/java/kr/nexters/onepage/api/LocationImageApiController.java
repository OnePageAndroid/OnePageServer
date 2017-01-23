package kr.nexters.onepage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.domain.locationImage.LocationImageResponseDto;
import kr.nexters.onepage.domain.locationImage.LocationImageService;
import kr.nexters.onepage.domain.support.Weather;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "장소이미지 API", description = "장소이미지 API", basePath = "/api/v1/locationImage")
@RestController
@RequestMapping("/api/v1/locationImage")
public class LocationImageApiController {

	@Autowired
	private LocationImageService locationImageService;

	@ApiOperation(value = "장소이미지 조회", notes = "특정 장소이미지 조회.")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public LocationImageResponseDto findByLocationIdAndWeather(@RequestParam Long locationId, @RequestParam Weather weather){
		Preconditions.checkNotNull(locationId, "장소 id없음");
		Preconditions.checkNotNull(weather,"날씨정보 없음");
		try{
			return locationImageService.findByLocationIdAndWeather(locationId, weather);
		}catch(Exception e){
			return LocationImageResponseDto.empty();
		}
	}
}
