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
				.build();
	}
}
