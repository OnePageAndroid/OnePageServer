package kr.nexters.onepage.domain.page;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageResponseDto {
	private Long id;
	private String message;

	public static PageResponseDto empty(){
		return PageResponseDto.builder()
				.id(null)
				.message("실패")
				.build();
	}

	public static PageResponseDto of(PageDto pageDto){
		return PageResponseDto.builder()
				.id(pageDto.getPageId())
				.message("성공")
				.build();
	}
}
