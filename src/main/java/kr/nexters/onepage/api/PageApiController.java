package kr.nexters.onepage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.page.PageService;
import kr.nexters.onepage.domain.page.PagesResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "페이지 API", description = "페이지 API", basePath = "/api/v1/page")
@RestController
@RequestMapping("/api/v1/page")
public class PageApiController {
	@Autowired
	private PageService pageService;

	@ApiOperation(value = "페이지 저장", notes = "페이지 저장")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseDto save(@RequestParam Long locationId, @RequestParam String email, @RequestParam String content) {
		try {
			pageService.savePage(locationId, email, content);
		} catch (Exception e) {
			log.error("page findBy : " + e.getMessage(), e);
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("페이지 저장 성공");
	}

	@ApiOperation(value = "장소 기반 페이지 조회", notes = "장소 기반 페이지 조회")
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public PagesResponseDto findByLocation(@RequestParam Long locationId,
		@ApiParam(value = "현재 페이지 넘버버0부터시작)") @RequestParam Integer pageNumber,
		@ApiParam(value = "가져올 페이지 사이즈") @RequestParam Integer perPageSize) {
		try {
			return pageService.findCircleByLocationId(locationId, pageNumber, perPageSize);
		} catch (Exception e) {
			log.error("findByLocation : " + e.getMessage());
			return PagesResponseDto.empty();
		}
	}

	@ApiOperation(value = "유저 기반 페이지 조회", notes = "유저 기반 페이지 조회")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public PagesResponseDto findByUser(@RequestParam String email,
		@ApiParam(value = "현재 페이지 넘버버0부터시작)") @RequestParam Integer pageNumber,
		@ApiParam(value = "가져올 페이지 사이즈") @RequestParam Integer perPageSize) {
		try {
			return pageService.findCircleByEmail(email, pageNumber, perPageSize);
		} catch (Exception e) {
			log.error("findByUser : " + e.getMessage());
			return PagesResponseDto.empty();
		}
	}

	@ApiOperation(value = "페이지 삭제", notes = "페이지 삭제")
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public ResponseDto remove(@RequestParam Long pageId){
		try{
			// TODO
		} catch(Exception e){
			log.error("page image remove : " + e.getMessage());
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("page 삭제 성공");
	}
}
