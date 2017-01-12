package kr.nexters.onepage.domain.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {
	private Double latitude;
	private Double longitude;
	private String name;
	private String address;
}
