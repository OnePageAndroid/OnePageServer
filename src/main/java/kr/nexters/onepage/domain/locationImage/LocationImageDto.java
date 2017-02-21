package kr.nexters.onepage.domain.locationImage;

import kr.nexters.onepage.domain.location.Location;
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

	public static LocationImageDto of(LocationImage locationImage, Location location){
		if(Objects.isNull(locationImage)) {
			return LocationImageDto.empty();
		}
		return LocationImageDto.builder()
				.locationId(locationImage.getLocation().getId())
				.objectkey(locationImage.getObjectKey())
				.url(locationImage.getUrl())
				.name(locationImage.getName())
				.englishName(location.getEngName())
				.dayType(locationImage.getDayType())
				.build();
	}

	public static LocationImageDto of(Location location, String imageUrl, DayType dayType) {
		if (Objects.isNull(location)) {
			return LocationImageDto.empty();
		}
		return LocationImageDto.builder()
			.locationId(location.getId())
			.objectkey(null)
			.url(imageUrl)
			.name(location.getName())
			.englishName(location.getEngName())
			.dayType(dayType)
			.build();
	}

	public static LocationImageDto empty() {
		return LocationImageDto.builder()
			.build();
	}
}