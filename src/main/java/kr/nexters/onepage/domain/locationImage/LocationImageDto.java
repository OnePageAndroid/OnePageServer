package kr.nexters.onepage.domain.locationImage;

import java.util.Objects;

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
	private DayType dayType;
	private String englishName;

	public static LocationImageDto of(LocationImage locationImage){
		if(Objects.isNull(locationImage)) {
			return LocationImageDto.empty();
		}
		return LocationImageDto.builder()
				.locationId(locationImage.getLocation().getId())
				.objectkey(locationImage.getObjectkey())
				.url(locationImage.getUrl())
				.name(locationImage.getName())
				.englishName(locationImage.getEnglishName())
				.dayType(DayType.valueOf(locationImage.getDayType()))
				.build();
	}

	private static LocationImageDto empty() {
		return LocationImageDto.builder()
			.build();
	}
}