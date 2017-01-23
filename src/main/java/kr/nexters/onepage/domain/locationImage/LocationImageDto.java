package kr.nexters.onepage.domain.locationImage;

import kr.nexters.onepage.domain.support.Weather;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationImageDto {
	private Long locationId;
	private String objectkey;
	private String url;
	private String name;
	private Weather weather;

	public static LocationImageDto of(LocationImage locationImage){
		return LocationImageDto.builder()
				.locationId(locationImage.getLocation().getId())
				.objectkey(locationImage.getObjectkey())
				.url(locationImage.getUrl())
				.name(locationImage.getName())
				.weather(locationImage.getWeather()).build();
	}
}