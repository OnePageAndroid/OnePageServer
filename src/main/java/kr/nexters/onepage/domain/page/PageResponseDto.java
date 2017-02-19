package kr.nexters.onepage.domain.page;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponseDto {
	private Long id;
	private String message;

	public static PageResponseDto empty(){
		return PageResponseDto.builder()
				.id(null)
				.message("FAIL")
				.build();
	}

	public static PageResponseDto of(PageDto pageDto){
		return PageResponseDto.builder()
				.id(pageDto.getPageId())
				.message("SUCCESS")
				.build();
	}
}
