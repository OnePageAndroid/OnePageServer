package kr.nexters.onepage.domain.pageImage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class PageImageDto {
	private Long pageId;
	private Long locationId;
	private String url;
	private String name;

	public static PageImageDto of(PageImage image) {
		return PageImageDto.builder()
			.pageId(image.getPage().getId())
			.locationId(image.getLocation().getId())
			.url(image.getUrl())
			.name(image.getName())
			.build();
	}
}
