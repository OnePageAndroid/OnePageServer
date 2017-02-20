package kr.nexters.onepage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.locationImage.DayType;
import kr.nexters.onepage.domain.locationImage.LocationImageResponseDto;
import kr.nexters.onepage.domain.locationImage.LocationImageService;
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
	public LocationImageResponseDto findByLocationIdAndDay(@RequestParam Long locationId, @RequestParam DayType dayType){
		Preconditions.checkNotNull(locationId, "장소 id없음");
		Preconditions.checkNotNull(dayType,"날씨정보 없음");
		try{
			return locationImageService.findByLocationIdAndDay(locationId, dayType);
		}catch(Exception e){
			log.error("findByLocationIdAndDay : " + e.getMessage(), e);
			return LocationImageResponseDto.empty();
		}
	}

	@ApiOperation(value = "장소 이미지 저장", notes = "장소 이미지 저장")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseDto locationImageSave(@RequestParam Long locationId, @RequestParam MultipartFile multipartFile, @RequestParam String name, @RequestParam String englishName, @RequestParam DayType dayType){
		Preconditions.checkNotNull(locationId,"장소 id없음");
		Preconditions.checkNotNull(multipartFile, "file 없음");
		Preconditions.checkNotNull(name, "이름 없음");
		Preconditions.checkNotNull(englishName, "영어 이름 없음");
		Preconditions.checkNotNull(dayType, "dayType 없음");
		try{
			locationImageService.locationImageSave(locationId, multipartFile, name, englishName, dayType);
		}catch(Exception e){
			log.error("locationImageSave fail : " + e.getMessage(), e);
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("이미지 저장 성공");
	}
}