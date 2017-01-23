package kr.nexters.onepage.domain.location;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationsResponseDto {
	List<LocationDto> locations;
	int resultCount;

	public static LocationsResponseDto empty() {
		return LocationsResponseDto.builder()
			.locations(Lists.newArrayList())
			.resultCount(0)
			.build();
	}

	public static LocationsResponseDto of(List<LocationDto> locationDtos) {
		return LocationsResponseDto.builder()
			.locations(locationDtos)
			.resultCount(locationDtos.size())
			.build();
	}
}
