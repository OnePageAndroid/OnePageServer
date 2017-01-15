package kr.nexters.onepage.domain.location;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationDto {
	private Long locationId;
	private Double latitude;
	private Double longitude;
	private String name;
	private String address;

	public static LocationDto of(Location location) {
		return LocationDto.builder()
			.locationId(location.getId())
			.latitude(location.getLatitude())
			.longitude(location.getLongitude())
			.name(location.getName())
			.address(location.getAddress())
			.build();
	}
}
