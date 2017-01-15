package kr.nexters.onepage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.heart.HeartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "좋아요 API", description = "좋아요 API", basePath = "/api/v1/heart")
@RestController
@RequestMapping("/api/v1/heart")
public class HeartApiController {
	@Autowired
	private HeartService heartService;

	@ApiOperation(value = "좋아요 저장", notes = "좋아요 저장")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseDto save(@ApiParam(value = "페이지 id") Long pageId, @ApiParam(value = "이메일") String email) {
		try {
			heartService.saveHeart(pageId, email);
		} catch (Exception e) {
			log.error("heart save : " + e.getMessage(), e);
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("하트 저장 성공");
	}

	@ApiOperation(value = "좋아요 삭제", notes = "좋아요 삭제")
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ResponseDto remove(Long pageId, String email) {
		try {
			heartService.removeHeart(pageId, email);
		} catch (Exception e) {
			log.error("heart remove : " + e.getMessage(), e);
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("하트 삭제 성공");
	}

	@ApiOperation(value = "좋아요 갯수 조회", notes = "좋아요 갯수 조회")
	@RequestMapping(value = "/{pageId}", method = RequestMethod.GET)
	public Long findByPageId(@PathVariable(name = "pageId") Long pageId) {
		try {
			return heartService.countByPageId(pageId);
		} catch (Exception e) {
			log.error("heart findBy : " + e.getMessage(), e);
			return 0L;
		}
	}
}
