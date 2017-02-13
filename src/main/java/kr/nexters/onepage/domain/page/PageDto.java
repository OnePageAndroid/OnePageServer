package kr.nexters.onepage.domain.page;

import kr.nexters.onepage.domain.pageImage.PageImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PageDto {
	private Long pageId;
	private String locationName;
	private String locationAddress;
	private String email;
	private String content;
	private List<PageImageDto> images;
	private int pageNum;
	private LocalDateTime createdAt;

	public static PageDto of(Page page, List<PageImageDto> imageDtos, int pageNum) {
		return PageDto.builder()
			.pageId(page.getId())
			.locationName(page.getLocation().getName())
			.locationAddress(page.getLocation().getAddress())
			.email(page.getUser().getEmail())
			.content(page.getContent())
			.images(imageDtos)
			.pageNum(pageNum)
			.createdAt(page.getCreatedAt())
			.build();
	}
}
