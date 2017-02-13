package kr.nexters.onepage.domain.locationImage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
				.englishName(locationImage.getEnglishName())
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

	public static LocationImageResponseDto of(Long locationId, String url, String name){
		return LocationImageResponseDto.builder()
				.locationId(locationId)
				.url(url)
				.name(name)
				.englishName(name)
				.build();
	}
}
