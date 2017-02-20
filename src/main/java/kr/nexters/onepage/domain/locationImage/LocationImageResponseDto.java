package kr.nexters.onepage.domain.locationImage;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationImageResponseDto {
	private Long locationId;
	private String url;
	private String name;
	private String englishName;

	public static LocationImageResponseDto of(LocationImageDto locationImage){
		return LocationImageResponseDto.builder()
				.locationId(locationImage.getLocationId())
				.url(locationImage.getUrl())
				.name(locationImage.getName())
				.build();
	}

	public static LocationImageResponseDto empty(){
		return LocationImageResponseDto.builder()
				.locationId(null)
				.url(null)
				.name(null)
				.englishName(null)
				.build();
	}

	public static LocationImageResponseDto of(Long locationId, String url, String name, String englishName){
		return LocationImageResponseDto.builder()
				.locationId(locationId)
				.url(url)
				.name(name)
				.englishName(englishName)
				.build();
	}
}
