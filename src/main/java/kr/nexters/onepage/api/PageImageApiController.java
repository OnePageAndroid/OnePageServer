package kr.nexters.onepage.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.pageImage.PageImageDto;
import kr.nexters.onepage.domain.pageImage.PageImageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "페이지별 이미지 API", description = "페이지별 이미지 조회", basePath = "/api/v1/page/image")
@RestController
@RequestMapping("/api/v1/page/image")
public class PageImageApiController {
	@Autowired
	private PageImageService pageImageService;

	@ApiOperation(value = "이미지 저장", notes = "이미지 저장")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseDto save(@RequestParam Long pageId, @RequestParam MultipartFile multipartFile) {
		Preconditions.checkNotNull(pageId, "pageId Parameter가 넘어오지 않음");
		Preconditions.checkNotNull(multipartFile, "multipartFile Parameter가 넘어오지 않음");
		try {
			pageImageService.savePageImage(pageId, multipartFile);
		} catch (Exception e) {
			log.error("page image save : " + e.getMessage(), e);
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("이미지 저장 성공");
	}

	@ApiOperation(value = "페이지 기반 이미지 조회", notes = "페이지 기반 이미지 조회")
	@RequestMapping(value = "/page/{pageId}", method = RequestMethod.GET)
	public List<PageImageDto> findByPageId(@PathVariable(value = "pageId") Long pageId) {
		Preconditions.checkNotNull(pageId, "pageId Parameter가 넘어오지 않음");
		try {
			return pageImageService.findByPageId(pageId);
		} catch (Exception e) {
			log.error("page image page : " + e.getMessage());
			return Lists.newArrayList();
		}
	}

	@ApiOperation(value = "이미지 삭제", notes = "이미지 삭제")
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ResponseDto removeByPageId(@RequestParam Long pageId) {
		Preconditions.checkNotNull(pageId, "pageId Parameter가 넘어오지 않음");
		try {
			pageImageService.removeByPageId(pageId);
		} catch (Exception e) {
			log.error("page image remove : " + e.getMessage());
			return ResponseDto.ofFail(e.getMessage());
		}
		return ResponseDto.ofSuccess("하트 삭제 성공");
	}
}
