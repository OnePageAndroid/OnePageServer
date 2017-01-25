package kr.nexters.onepage.domain.page;

import java.util.List;

import kr.nexters.onepage.domain.pageImage.PageImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageResponseDto {
	private String content;
	private List<PageImageDto> images;

	public static PageResponseDto empty(){
		return PageResponseDto.builder()
				.content(null)
				.images(null)
				.build();
	}

	public static PageResponseDto of(PageDto pageDto){
		return PageResponseDto.builder()
				.content(pageDto.getContent())
				.images(pageDto.getImages())
				.build();
	}
}
