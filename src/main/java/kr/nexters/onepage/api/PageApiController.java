package kr.nexters.onepage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	public PagesResponseDto findByLocation(@RequestParam Long locationId, @RequestParam Integer pageNumber, @RequestParam Integer perPageSize) {
		// TODO 논의후 적용.
		try {
			return pageService.findByLocationId(locationId, pageNumber, perPageSize);
		} catch (Exception e) {
			log.error("findByPageId : " + e.getMessage());
			return PagesResponseDto.empty();
		}
	}

	@ApiOperation(value = "유저 기반 페이지 조회", notes = "유저 기반 페이지 조회")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public PagesResponseDto findByUser(@RequestParam String email, @RequestParam Integer pageNumber, @RequestParam Integer perPageSize) {
		// TODO 논의후 적용.
		try {
			return pageService.findByEmail(email, pageNumber, perPageSize);
		} catch (Exception e) {
			log.error("findByPageId : " + e.getMessage());
			return PagesResponseDto.empty();
		}
	}
}
