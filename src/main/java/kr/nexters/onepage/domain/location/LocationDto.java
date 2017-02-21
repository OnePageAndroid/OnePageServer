package kr.nexters.onepage.domain.location;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
	private Long locationId;
	private Double latitude;
	private Double longitude;
	private String name;
	private String engName;
	private String address;

	public static LocationDto of(Location location) {
		return LocationDto.builder()
			.locationId(location.getId())
			.latitude(location.getLatitude())
			.longitude(location.getLongitude())
			.name(location.getName())
			.engName(location.getEngName())
			.address(location.getAddress())
			.build();
	}
}
