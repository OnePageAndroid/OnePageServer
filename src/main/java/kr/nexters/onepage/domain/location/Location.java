package kr.nexters.onepage.domain.location;

import kr.nexters.onepage.domain.support.Created;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "onepage", name = "location")
@Where(clause = "deleted = 0")
public class Location extends Created {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locationId")
	private Long id;
	@Column
	private Double latitude;
	@Column
	private Double longitude;
	@Column
	private String name;
	@Column
	private String engName;
	@Column
	private String address;
	@Column
	private boolean deleted;

	public static Location of(LocationDto locationDto) {
		return Location.builder()
			.latitude(locationDto.getLatitude())
			.longitude(locationDto.getLongitude())
			.name(locationDto.getName())
			.engName(locationDto.getEngName())
			.address(locationDto.getAddress()).build();
	}

	public static Location of(Double latitude, Double longitude, String name, String engName, String address) {
		return Location.builder()
			.latitude(latitude)
			.longitude(longitude)
			.name(name)
			.engName(engName)
			.address(address).build();
	}
}
