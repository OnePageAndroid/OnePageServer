package kr.nexters.onepage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.heart.HeartService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "좋아요 API", description = "좋아요 API", basePath = "/api/v1/heart")
@RestController
@RequestMapping("/api/v1/heart")
public class HeartApiController {
	@Autowired
	private HeartService heartService;

	@ApiOperation(value = "좋아요 저장 or 삭제", notes = "좋아요 저장 or 삭제")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseDto saveOrRemove(@RequestParam Long pageId, @RequestParam String email) {
	Preconditions.checkNotNull(pageId, "pageId parameter가 존재하지 않음");
	Preconditions.checkNotNull(email , "email parameter가 존재하지 않음");
		try {
			heartService.saveOrRemoveHeart(pageId, email);
		} catch (Exception e) {
			log.error("heart save : " + e.getMessage(), e);
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("하트 저장 성공");
	}

	@ApiOperation(value = "좋아요 상태", notes = "좋아요 상태")
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public boolean status(@RequestParam Long pageId, @RequestParam String email) {
		Preconditions.checkNotNull(pageId, "pageId parameter가 존재하지 않음");
		Preconditions.checkNotNull(email, "email paramter가 존재하지 않음");
		try {
			return heartService.existsByPageIdAndEmail(pageId, email);
		} catch (Exception e) {
			log.error("heart save : " + e.getMessage(), e);
			return false;
		}
	}

	@ApiOperation(value = "좋아요 갯수 조회", notes = "좋아요 갯수 조회")
	@RequestMapping(value = "/{pageId}", method = RequestMethod.GET)
	public Long findByPageId(@PathVariable(name = "pageId") Long pageId) {
		Preconditions.checkNotNull(pageId ,"pageId parameter가 존재하지 않음");
		try {
			return heartService.countByPageId(pageId);
		} catch (Exception e) {
			log.error("heart findBy : " + e.getMessage(), e);
			return 0L;
		}
	}

}
