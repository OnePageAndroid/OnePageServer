package kr.nexters.onepage.domain.page;

import com.google.common.collect.Lists;
import kr.nexters.onepage.domain.pageImage.PageImageDto;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;

public class PageDtoBuilder {
	private PageDtoBuilder() {
	}

	public static List<PageDto> transformPagesToDtos(List<Page> pages, Integer pageNumber, Function<Long, List<PageImageDto>> imageFunc) {
		if (CollectionUtils.isEmpty(pages)) {
			return Lists.newArrayList();
		}
		List<PageDto> pageDtos = Lists.newArrayList(); // TODO stream 으로 리팩
		int idxPageNumber = pageNumber;
		for(Page page : pages) {
			idxPageNumber += 1;
			List<PageImageDto> imageDtos = imageFunc.apply(page.getId());
			pageDtos.add(PageDto.of(page, imageDtos, idxPageNumber));
		}
		return pageDtos;
	}
}
