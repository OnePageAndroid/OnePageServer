package kr.nexters.onepage.domain.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import kr.nexters.onepage.domain.support.Created;
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
@Entity
@Table(catalog = "onepage", name = "location")
@Where(clause = "deleted = 0")
public class Location extends Created {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locationId")
	private Long id;
	@Column
	@NotNull
	private Double latitude;
	@Column
	@NotNull
	private Double longitude;
	@Column
	@NotNull
	private String name;
	@Column
	@NotNull
	private String address;
	@Column
	private boolean deleted;

	public static Location of(LocationDto locationDto) {
		return Location.builder()
			.latitude(locationDto.getLatitude())
			.longitude(locationDto.getLongitude())
			.name(locationDto.getName())
			.address(locationDto.getAddress()).build();
	}

	public static Location of(Double latitude, Double longitude, String name, String address) {
		return Location.builder()
			.latitude(latitude)
			.longitude(longitude)
			.name(name)
			.address(address).build();
	}
}
