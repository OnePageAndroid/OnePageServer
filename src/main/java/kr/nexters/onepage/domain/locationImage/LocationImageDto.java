package kr.nexters.onepage.domain.locationImage;

import lombok.*;

import java.util.Objects;

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
	private String englishName;
	private DayType dayType;

	public static LocationImageDto of(LocationImage locationImage){
		if(Objects.isNull(locationImage)) {
			return LocationImageDto.empty();
		}
		return LocationImageDto.builder()
				.locationId(locationImage.getLocation().getId())
				.objectkey(locationImage.getObjectKey())
				.url(locationImage.getUrl())
				.name(locationImage.getName())
				.englishName(locationImage.getEnglishName())
				.dayType(locationImage.getDayType())
				.build();
	}

	private static LocationImageDto empty() {
		return LocationImageDto.builder()
			.build();
	}
}