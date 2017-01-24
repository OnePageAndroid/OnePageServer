package kr.nexters.onepage.domain.locationImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationImageDto {
	private Long locationId;
	private String objectkey;
	private String url;
	private String name;
	private WeatherType weather;

	public static LocationImageDto of(LocationImage locationImage){
		return LocationImageDto.builder()
				.locationId(locationImage.getLocation().getId())
				.objectkey(locationImage.getObjectkey())
				.url(locationImage.getUrl())
				.name(locationImage.getName())
				.weather(locationImage.getWeatherType())
				.build();
	}

}