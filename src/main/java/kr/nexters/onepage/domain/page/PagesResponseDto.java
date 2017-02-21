package kr.nexters.onepage.domain.page;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PagesResponseDto {
	private List<PageDto> pages;
	private int pageIndex;
	private int perPageSize;
	private int resultCount;
	private int totalSize;

	public static PagesResponseDto of(List<PageDto> pageDtos, Integer pageNumber, Integer perPageSize, Integer totalSize) {
		return PagesResponseDto.builder()
			.pages(pageDtos)
			.pageIndex(pageNumber)
			.perPageSize(perPageSize)
			.resultCount(pageDtos.size())
			.totalSize(totalSize)
			.build();
	}

	public static PagesResponseDto empty() {
		return PagesResponseDto.builder()
			.pages(Lists.newArrayList())
			.pageIndex(0)
			.perPageSize(0)
			.resultCount(0)
			.totalSize(0)
			.build();
	}
}
