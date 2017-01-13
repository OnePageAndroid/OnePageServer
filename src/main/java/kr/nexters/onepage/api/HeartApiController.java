package kr.nexters.onepage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.heart.HeartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "HeartApiController", description = "heart api", basePath = "/api/v1/heart")
@RestController
@RequestMapping("/api/v1/location")
public class HeartApiController {
	@Autowired
	private HeartService heartService;

	@ApiOperation(value = "save heart", notes = "save heart")
	@RequestMapping("/save")
	public ResponseDto save(Long pageId, String email) {
		try {
			heartService.saveHeart(pageId, email);
		} catch (Exception e) {
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("하트 저장 성공");
	}

	@ApiOperation(value = "remove heart", notes = "remove heart")
	@RequestMapping("/remove")
	public ResponseDto remove(Long pageId, String email) {
		try {
			heartService.removeHeart(pageId, email);
		} catch (Exception e) {
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("하트 삭제 성공");
	}
}
